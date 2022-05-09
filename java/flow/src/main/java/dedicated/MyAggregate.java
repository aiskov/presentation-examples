package dedicated;

class MyAggregate {
    void doImportantWork(Integer value) {
        if (value == null)
            throw new NullPointerException();

        if (value < 0)
            throw new TooSmallException(value, 0);

        if (value > 10)
            throw new TooBigException(value, 10);
    }
}
