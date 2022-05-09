package checked;

import java.util.Optional;

class MyRepository {
    Optional<MyAggregate> get(String id) {
        if (! "123".equals(id)) return Optional.empty();
        return Optional.of(new MyAggregate());
    }

    void save(MyAggregate aggregate) {

    }
}
