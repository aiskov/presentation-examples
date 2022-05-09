package either_context_clear;

import io.vavr.control.Either;

class MyService {
    private final MyRepository repository = new MyRepository();

    Either<ErrorContext, Void> doImportantWork(String id, Integer value) {
        return this.repository.get(id)
                .map(Either::<ErrorContext, MyAggregate>right)
                .orElseGet(() -> Either.left(new AggregateNotFoundErrorContext(MyAggregate.class.getName(), id)))
                .flatMap(aggregate -> aggregate.doImportantWork(value)
                        .peek($_IGNORED_$ -> this.repository.save(aggregate)));
    }
}
