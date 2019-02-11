package entities;

import enums.DoctorType;

import java.util.ArrayList;
import java.util.List;

public final class Doctor {
    private DoctorType type;
    private boolean isSurgeon;
    private int maxForTreatment;
    private List<Patient> hospitalizedPatients = new ArrayList<>();

    /**
     * getter.
     * @return tipul doctorului
     */
    public DoctorType getType() {
        return type;
    }

    /**
     * setter.
     * @param type tipul doctorului
     */
    public void setType(DoctorType type) {
        this.type = type;
    }

    /**
     * getter.
     * @return true daca doctorul este chirurg, false astfel
     */
    public boolean isSurgeon() {
        return isSurgeon;
    }

    /**
     * setter.
     * @param isSurgeon seteaza doctorul ca fiind chirurg sau nu
     */
    public void setIsSurgeon(boolean isSurgeon) {
        this.isSurgeon = isSurgeon;
    }

    /**
     * getter.
     * @return valoarea minima pentru care doctorul consulta un pacient
     */
    public int getMaxForTreatment() {
        return maxForTreatment;
    }

    /**
     * setter.
     * @param maxForTreatment valoarea minima pentru care doctorul consulta un pacient
     */
    public void setMaxForTreatment(int maxForTreatment) {
        this.maxForTreatment = maxForTreatment;
    }

    /**
     * getter.
     * @return pacientii spitalizati de doctorul curent
     */
    public List<Patient> getHospitalizedPatients() {
        return hospitalizedPatients;
    }

    /**
     * setter.
     * @param hospitalizedPatients pacientii spitalizati de doctorul curent
     */
    public void setHospitalizedPatients(List<Patient> hospitalizedPatients) {
        this.hospitalizedPatients = hospitalizedPatients;
    }
}
