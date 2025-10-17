package hu.kovaribe.masterdata.masterdata.enums;

public enum AddressStructure {
    ADDRESS_TYPE_PERMANENT("Állandó lakcím"),
    ADDRESS_TYPE_MAILING("Levelezési cím");

    private final String label;

    AddressStructure(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
