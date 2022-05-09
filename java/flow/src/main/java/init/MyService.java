package init;

class MyService {
    private final MyRepository repository = new MyRepository();

    void doImportantWork(String id, Integer value) {
        MyAggregate aggregate = this.repository.get(id)
                .orElseThrow(() -> new IllegalStateException());

        aggregate.doImportantWork(value);

        this.repository.save(aggregate);
    }
}
