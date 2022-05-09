package either;

import io.vavr.control.Either;

class MyController {
    private final MyService service = new MyService();

    Either<ErrorTypes, Void> doImportantWork(String id, Integer value) {
        // Here we should have mapping to ResponseEntity?
        return this.service.doImportantWork(id, value);
    }
}
