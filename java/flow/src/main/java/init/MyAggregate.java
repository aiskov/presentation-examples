package init;

class MyAggregate {
    void doImportantWork(Integer value) {
        if (value == null)
            throw new NullPointerException();

        if (value < 0)
            throw new IllegalStateException("Too small");

        if (value > 10)
            throw new IllegalStateException("Too big");
    }
}
