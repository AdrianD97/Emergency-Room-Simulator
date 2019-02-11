package subject;

import entities.Doctor;
import entities.Patient;
import enums.IllnessType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;

public final class EmergencyRoom extends Observable {
    private static EmergencyRoom emergencyRoom = new EmergencyRoom();
    private List<Patient> triageQueue;
    private List<Patient> examinationQueue;
    private List<Patient> investigationQueue;
    private List<Patient> hospitalizedPatients;
    private List<Doctor> doctors;
    private int nrNurses;
    private int nrInvestigators;
    private List<Patient> eliminatedPatients;
    private HashMap<IllnessType, List<Doctor>> doctorsLists;

    private EmergencyRoom() {
        triageQueue = new ArrayList<>();
        examinationQueue = new ArrayList<>();
        investigationQueue = new ArrayList<>();
        hospitalizedPatients = new ArrayList<>();
        doctors = new ArrayList<>();
        eliminatedPatients = new ArrayList<>();
        doctorsLists = new HashMap<IllnessType, List<Doctor>>() {
            {
                put(IllnessType.HEART_ATTACK, new ArrayList<>());
                put(IllnessType.HEART_DISEASE, new ArrayList<>());
                put(IllnessType.ALLERGIC_REACTION, new ArrayList<>());
                put(IllnessType.BROKEN_BONES, new ArrayList<>());
                put(IllnessType.BURNS, new ArrayList<>());
                put(IllnessType.CAR_ACCIDENT, new ArrayList<>());
                put(IllnessType.CUTS, new ArrayList<>());
                put(IllnessType.HIGH_FEVER, new ArrayList<>());
                put(IllnessType.SPORT_INJURIES, new ArrayList<>());
                put(IllnessType.ABDOMINAL_PAIN, new ArrayList<>());
                put(IllnessType.FOOD_POISONING, new ArrayList<>());
                put(IllnessType.PNEUMONIA, new ArrayList<>());
                put(IllnessType.STROKE, new ArrayList<>());
            }
        };
    }

    /**
     * @return intoarce singura instanta a subiectului nostru(camera de urgenta).
     */
    public static EmergencyRoom getEmergencyRoom() {
        return emergencyRoom;
    }

    /**
     * getter.
     * @return pacientii din triage queue
     */
    public List<Patient> getTriageQueue() {
        return triageQueue;
    }

    /**
     * setter.
     * @param triageQueue lista de pacientii care trebuie adaugati in triage queue
     */
    public void setTriageQueue(List<Patient> triageQueue) {
        this.triageQueue = triageQueue;
    }

    /**
     * getter.
     * @return pacientii din examination queue
     */
    public List<Patient> getExaminationQueue() {
        return examinationQueue;
    }

    /**
     * setter.
     * @param examinationQueue lista de pacientii care trebuie adaugati in examination queue
     */
    public void setExaminationQueue(List<Patient> examinationQueue) {
        this.examinationQueue = examinationQueue;
    }

    /**
     * getter.
     * @return pacientii din investigation queue
     */
    public List<Patient> getInvestigationQueue() {
        return investigationQueue;
    }

    /**
     * setter.
     * @param investigationQueue lista de pacientii care trebuie adaugati in investigation queue
     */
    public void setInvestigationQueue(List<Patient> investigationQueue) {
        this.investigationQueue = investigationQueue;
    }

    /**
     * getter.
     * @return pacientii spitalizati din camera de urgenta.
     */
    public List<Patient> getHospitalizedPatients() {
        return hospitalizedPatients;
    }

    /**
     * setter.
     * @param hospitalizedPatients lista de pacienti care sunt spitalizati
     */
    public void setHospitalizedPatients(List<Patient> hospitalizedPatients) {
        this.hospitalizedPatients = hospitalizedPatients;
    }

    /**
     * getter.
     * @return doctorii din camera de urgenta curenta
     */
    public List<Doctor> getDoctors() {
        return doctors;
    }

    /**
     * setter.
     * @param doctors lista de doctori care vor avea grija de pacientii din camera de urgenta
     */
    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    /**
     * getter.
     * @return numarul de asistente
     */
    public int getNrNurses() {
        return nrNurses;
    }

    /**
     * setter.
     * @param nrNurses numarul de asistente care vor aplica tratamentul pacientilor
     */
    public void setNrNurses(int nrNurses) {
        this.nrNurses = nrNurses;
    }

    /**
     * getter.
     * @return numarul de ER Tehnicieni din camera de urgenta
     */
    public int getNrInvestigators() {
        return nrInvestigators;
    }

    /**
     * setter.
     * @param nrInvestigators numarul de ER Tehnicieni care vor investiga pacientii.
     */
    public void setNrInvestigators(int nrInvestigators) {
        this.nrInvestigators = nrInvestigators;
    }

    /**
     * getter.
     * @return lista cu pacientii trimisi acasa sau transferati la alt spital
     */
    public List<Patient> getEliminatedPatients() {
        return eliminatedPatients;
    }

    /**
     * setter.
     * @param eliminatedPatients lista de pacienti trimisis acasa sau transferati la alt spital
     */
    public void setEliminatedPatients(List<Patient> eliminatedPatients) {
        this.eliminatedPatients = eliminatedPatients;
    }

    /**
     * getter.
     * @return listele de doctori pentru fiecare boala in parte
     */
    public HashMap<IllnessType, List<Doctor>> getDoctorsLists() {
        return doctorsLists;
    }

    /**
     * setter.
     * @param doctorsLists listele de doctori pentru fiecare boala
     */
    public void setDoctorsLists(HashMap<IllnessType, List<Doctor>> doctorsLists) {
        this.doctorsLists = doctorsLists;
    }

    /**
     * notifica observatorii subiectului nostru(camera de urgenta = obiectul curent),
     * despre schimbarea starii.
     */
    public void update()  {
        this.setChanged();
        this.notifyObservers();
    }
}
