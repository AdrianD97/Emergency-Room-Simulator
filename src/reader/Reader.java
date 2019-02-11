package reader;

import entities.Doctor;
import entities.Patient;

import java.util.ArrayList;

public final class Reader {
    private int simulationLength;
    private int nurses;
    private int investigators;
    private ArrayList<Doctor> doctors;
    private ArrayList<Patient> incomingPatients;

    /**
     * getter.
     * @return numarul de runde
     */
    public int getSimulationLength() {
        return simulationLength;
    }

    /**
     * setter.
     * @param simulationLength numarul de runde
     */
    public void setSimulationLength(int simulationLength) {
        this.simulationLength = simulationLength;
    }

    /**
     * getter.
     * @return numarul de asistente
     */
    public int getNurses() {
        return nurses;
    }

    /**
     * setter.
     * @param nurses numarul de asistente
     */
    public void setNurses(int nurses) {
        this.nurses = nurses;
    }

    /**
     * getter.
     * @return numarul de ER Tehnicieni
     */
    public int getInvestigators() {
        return investigators;
    }

    /**
     * setter.
     * @param investigators numarul de ER Tehnicieni
     */
    public void setInvestigators(int investigators) {
        this.investigators = investigators;
    }

    /**
     * getter.
     * @return lista de doctori
     */
    public ArrayList<Doctor> getDoctors() {
        return doctors;
    }

    /**
     * setter.
     * @param doctors lista de doctori
     */
    public void setDoctors(ArrayList<Doctor> doctors) {
        this.doctors = doctors;
    }

    /**
     * getter.
     * @return lista de pacienti
     */
    public ArrayList<Patient> getIncomingPatients() {
        return incomingPatients;
    }

    /**
     * setter.
     * @param incomingPatients lista de pacienti
     */
    public void setIncomingPatients(ArrayList<Patient> incomingPatients) {
        this.incomingPatients = incomingPatients;
    }
}
