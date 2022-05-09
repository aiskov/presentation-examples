package checked;

class MyController {
    private final MyService service = new MyService();

    void doImportantWork(String id, Integer value) throws TooBigException, TooSmallException {
        this.service.doImportantWork(id, value);
    }
}
