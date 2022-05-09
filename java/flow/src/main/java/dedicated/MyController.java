package dedicated;

class MyController {
    private final MyService service = new MyService();

    void doImportantWork(String id, Integer value) {
        this.service.doImportantWork(id, value);
    }
}
