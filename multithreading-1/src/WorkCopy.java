public class WorkCopy {
    public static void main(String... args) {
        echo("Hi!");
   }


    static void echo(String text) { System.out.println(text); }
//    static void sleep(long sec) { try {SECONDS(sec); } catch (InterruptedException e) {throw new RuntimeException(e);}}
}

/*

Plan:

    1. Hard Example - How much threads
    2. I wanna wait - wait for a 10 seconds (google, sync, result)
    3. Time to sleep
    4. Where my thread
    5. Why it want to die?
    6. Thread options
    7. Let's notify (first, sync, blocked, why java, states, right)
    8. When I want to be smart.

 */
