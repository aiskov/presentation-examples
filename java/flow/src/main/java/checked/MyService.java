package checked;

class MyService {
    private final MyRepository repository = new MyRepository();

    void doImportantWork(String id, Integer value) throws TooBigException, TooSmallException {
        MyAggregate aggregate = this.repository.get(id)
                .orElseThrow(() -> new IllegalStateException());

        aggregate.doImportantWork(value);

        this.repository.save(aggregate);
    }
}
