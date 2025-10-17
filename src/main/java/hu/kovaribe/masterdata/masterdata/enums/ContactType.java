package hu.kovaribe.masterdata.masterdata.enums;

public enum ContactType {
    EMAIL("E-mail cím"),
    PHONE("Telefonszám");

    private final String label;

    ContactType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
