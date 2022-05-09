package checked;

import lombok.NonNull;

class MyAggregate {
    void doImportantWork(@NonNull Integer value) throws TooBigException, TooSmallException {
        if (value < 0)
            throw new TooSmallException(value, 0);

        if (value > 10)
            throw new TooBigException(value, 10);
    }
}
