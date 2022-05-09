package either;

import io.vavr.control.Either;

class MyService {
    private final MyRepository repository = new MyRepository();

    Either<ErrorTypes, Void> doImportantWork(String id, Integer value) {
        MyAggregate aggregate = this.repository.get(id)
                .orElseThrow(() -> new IllegalStateException());

        Either<ErrorTypes, Void> result = aggregate.doImportantWork(value);
        if (result.isRight()) this.repository.save(aggregate);

        return result;
    }
}
