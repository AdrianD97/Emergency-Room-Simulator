package entities.state;

import enums.IllnessType;

public final class State {
    private IllnessType illnessName;
    private int severity;

    /**
     * getter.
     * @return tipul bolli
     */
    public IllnessType getIllnessName() {
        return illnessName;
    }

    /**
     * setter.
     * @param illnessName tipul bolii
     */
    public void setIllnessName(IllnessType illnessName) {
        this.illnessName = illnessName;
    }

    /**
     * getter.
     * @return severitatea bolii
     */
    public int getSeverity() {
        return severity;
    }

    /**
     * setter.
     * @param severity severitatea bolii
     */
    public void setSeverity(int severity) {
        this.severity = severity;
    }
}
