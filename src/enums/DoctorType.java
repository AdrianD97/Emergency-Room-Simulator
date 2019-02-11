package enums;

public enum DoctorType {
    CARDIOLOGIST("CARDIOLOGIST"),
    ER_PHYSICIAN("ER_PHYSICIAN"),
    GASTROENTEROLOGIST("GASTROENTEROLOGIST"),
    GENERAL_SURGEON("GENERAL_SURGEON"),
    INTERNIST("INTERNIST"),
    NEUROLOGIST("NEUROLOGIST");

    private String value;

    DoctorType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
