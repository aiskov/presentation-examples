package either_context;

import io.vavr.control.Either;

class MyService {
    private final MyRepository repository = new MyRepository();

    Either<ErrorContext, Void> doImportantWork(String id, Integer value) {
        // Start transaction???
        MyAggregate aggregate = this.repository.get(id)
                .orElseThrow(() -> new IllegalStateException());

        Either<ErrorContext, Void> result = aggregate.doImportantWork(value);
        if (result.isLeft()) {
            // Manually rollback transaction
            return result;
        }

        this.repository.save(aggregate);
        // Manually commit transaction
        return result;
    }
}
