public enum Status {
    ZLOZONE("złożone"),
    PRZYGOTOWANE("przygotowane do wysyłki"),
    TRANSPORT("w transporcie"),
    ZREALIZOWANE("zrealizowane"),
    ANULOWANE("anulowane");

    private final String statusValue;

    Status(String statusValue) {
        this.statusValue =statusValue;
    }

    public String getStatusValue() {
        return statusValue;
    }
}
