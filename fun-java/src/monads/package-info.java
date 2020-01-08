package monads;

/*
        String result;

        try {
            // TODO: Try to get value
            throw new NullPointerException();
        } catch (Exception exp) {
            try {
                // TODO: Try backup
                throw new RuntimeException();
            } catch (Exception exp1) {
                // Throw right exception
                // throw new IllegalStateException();
                // Or assign backup value
                result = "";
            }
        }
        ////////////////////////

        result = Try.toDo(() -> { throw new NullPointerException(); })
                .map(value -> value + "1")
                .tryToRestore(() -> { throw new RuntimeException(); })
                .map(value -> value + "2")
                .orElseGet(() -> "");
 */