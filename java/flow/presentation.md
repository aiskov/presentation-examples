# Control of application flow

**Disclaimer:** 

I'm looking for solution how to manage error flow in my projects. 

For now, it seems that all approaches seams to not be ideal, and java have no effective 
mechanism to control error flow that feet all my requirements. 

## Example

Application that contains a domain layer - aggregate, service layer, repository and controller.

App class simulate usage and error handling. 

## What do we want

1. Manage errors visible and force to process them by client.

I want to force client code to process error flow.

2. I want to define context of error.

I want to transfer context of error that contains data values that cause that problem 
and trace where error occur.

3. I want to process errors without string parsing

When I write parser I would use enums or types to distinguish kind of errors.

4. Do not write too much code

I do not want to write too much code to keep approach.

5. Declare possible errors

I want to see list of possible problems that may occur while execution. 

6. Distinguish validation/verification errors with some unexpected states (coding mistakes like NPE)

I want to process coding TODO:

7. Be effective

I want to do not broke performance of code by usage of some approach.

## Solutions

### Reuse standard exceptions

1. **(BAD)** Error flow absolutely hidden, you may not be aware that you code is not safe.
2. **(BAD)** We describe only stack trace and message.
3. **(BAD)** I should parse text to understand what is wrong.
4. **(GOOD)** Minimal effort for coding.
5. **(BAD)** We don't see what could fail when we use some interfaces.
6. **(BAD)** All errors looks in same way
7. **(BAD)** Rumors say that exceptions are slow. 

### Use dedicated exceptions

1. **(BAD)** Error flow absolutely hidden, you may not be aware that you code is not safe.
2. **(GOOD)** We have stack trace, message and additional fields.
3. **(GOOD)** We may use types.
4. **(GOOD)** Minimal effort for coding.
5. **(BAD)** We don't see what could fail when we use some interfaces.
6. **(BAD)** All errors processed in same way, but potentially we may catch verification errors.
7. **(BAD)** Rumors say that exceptions are slow. 

### Use dedicated checked exceptions

1. **(GOOD)** Error flow is visible in method declaration.
2. **(GOOD)** We have stack trace, message and additional fields.
3. **(GOOD)** We may use types.
4. **(GOOD)** Moderate effort for coding.
5. **(GOOD)** We see what could fail when we use some interfaces.
6. **(GOOD)** Unexpected/coding errors are hidden as unchecked, we process mostly explicitly verification errors
7. **(BAD)** Rumors say that exceptions are slow. 

**Note:** Sometimes it may produce really strange definitions that contains many lines of 
possible errors. Some haters will hate you. 

### Single checked exception + Enum types (Not implemented)

1. **(GOOD)** Error flow is visible in method declaration.
2. **(GOOD)** We have stack trace, message and additional fields.
3. **(GOOD)** We may use enums.
4. **(GOOD)** Minimal effort for coding.
5. **(BAD)** We don't see what could fail when we use some interfaces.
6. **(GOOD)** Unexpected/coding errors are hidden as unchecked, we process mostly explicitly verification errors
7. **(BAD)** Rumors say that exceptions are slow.

### Either 

We may use standard mechanism of either, provided by some library. Currently, that approach used 
in functional languages and Go. 

* Right - response in case of success, it will be empty when fails.
* Left - response in case of error, we may pass type of error.

1. **(GOOD)** Error flow is visible in method declaration.
2. **(BAD)** We have no stack trace and additional fields.
3. **(GOOD)** We may use enums.
4. **(GOOD)** Minimal effort for coding.
5. **(BAD)** We don't see what could fail when we use some interfaces.
6. **(GOOD)** Unexpected/coding errors are hidden as unchecked, we process mostly explicitly verification errors
7. **(GOOD)** Rumors say that faster than exceptions.

### Either with context

We may extend either in order to transfer some additional info. 

1. **(GOOD)** Error flow is visible in method declaration.
2. **(GOOD)** We have no stack trace.
3. **(GOOD)** We may use enums.
4. **(BAD)** Moderate effort for coding.
5. **(BAD)** We don't see what could fail when we use some interfaces.
6. **(GOOD)** Unexpected/coding errors are hidden as unchecked, we process mostly explicitly verification errors
7. **(GOOD)** Rumors say that faster than exceptions.

## Summary

In general, we have two groups of solutions:

1. Based on exceptions 
   1. May describes all possible errors in declaration.
   2. Could be used in generated documentation, because it is visible in reflections.
   3. Contains stack trace.
   4. Processing may be forced by compiler (hard to skip error processing by mistake)
2. Based on response objects
   1. Describes in general that could be error. 
   3. Work faster.
   4. Processing suggested by access to correct result. (you may skipp processing just by ignoring output.)

In booth cases if yuo want transfer some additional context you will need to build some context type.

Missing stack trace may be replaced with some extended log mechanism that produce log record 
before & after method execution, but it produces massive logs that usually not needed.

Missing descriptions of concrete errors may be solved documentation. Does it possible to verify it 
with linter?

### Performance

**Disclaimer:** Measurement was done in the simplest way, so it may be wrong. 

Measurement for 1 000 000 000 executions. 

| #  | Exceptions         | Either             | Either clean      |
|----|--------------------|--------------------|-------------------|
| 1  | Σ 289s (3 mic sec) | Σ 229s (2 mic sec) | Σ 21s (0 mic sec) |
| 2  | Σ 294s (3 mic sec) | Σ 225s (2 mic sec) | Σ 24s (0 mic sec) |

What is important: 
1. Error handling get less than 1 millisecond.
2. So it may be good solution for high load application, because it may reduce costs. 
3. For small applications it probably does not relevant.   
