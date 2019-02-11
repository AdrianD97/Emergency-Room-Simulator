import entities.Doctor;
import entities.Patient;
import enums.DoctorType;
import enums.IllnessType;
import enums.InvestigationResult;
import enums.StateMessage;
import info.DoctorsInfo;
import observers.DoctorsObserver;
import observers.ExaminationObserver;
import observers.InvestigationObserver;
import observers.NursesObserver;
import observers.PrinterPatientsMessagesObserver;
import observers.TriageObserver;
import reader.Reader;
import subject.EmergencyRoom;

import java.util.ArrayList;
import java.util.List;

public final class Simulator {
    private Reader reader;
    private static Simulator simulator = new Simulator();

    private Simulator() {
    }

    /**
     * getter.
     * @return obiectul reader(continutul fisierului de input)
     */
    public Reader getReader() {
        return reader;
    }

    /**
     * setter.
     * @param reader continutul fisierului de input
     */

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    /**
     * @return singuta instanta a clasei Simulator
     */
    public static Simulator getSimulator() {
        return simulator;
    }

    /**
     * initializeaza "jocul" :
     *  - creeaza listele de medici pentru camera de urgenta(pentru ficare boala
     *  creeaza o lista cu medicii care sunt capabili sa trateze respectiva boala).
     *  - initializeaza lista de medici ai camerei de urgenta
     *  - initializeaza numarul de asistente si numarul de ER tehnicieni
     *  - initializeaza lista de observatori pentru subiectul nostru(camera de urgenta)
     */
    public void init() {
        for (Doctor doctor: reader.getDoctors()) {
            for (IllnessType illnessType: EmergencyRoom.getEmergencyRoom()
                    .getDoctorsLists().keySet()) {
                if (DoctorsInfo.getInstance().cureIllness(doctor.getType(), illnessType)) {
                    EmergencyRoom.getEmergencyRoom().getDoctorsLists().get(illnessType).add(doctor);
                }
            }

            if (doctor.getType().getValue().equals(DoctorType.GENERAL_SURGEON.getValue())) {
                doctor.setIsSurgeon(true);
            }
        }

        EmergencyRoom.getEmergencyRoom().setDoctors(reader.getDoctors());
        EmergencyRoom.getEmergencyRoom().setNrNurses(reader.getNurses());
        EmergencyRoom.getEmergencyRoom().setNrInvestigators(reader.getInvestigators());
        // adaug observatorii
        EmergencyRoom.getEmergencyRoom().addObserver(new DoctorsObserver());
        EmergencyRoom.getEmergencyRoom().addObserver(new NursesObserver());
        EmergencyRoom.getEmergencyRoom().addObserver(new PrinterPatientsMessagesObserver());
        EmergencyRoom.getEmergencyRoom().addObserver(new InvestigationObserver());
        EmergencyRoom.getEmergencyRoom().addObserver(new ExaminationObserver());
        EmergencyRoom.getEmergencyRoom().addObserver(new TriageObserver());
    }

    /**
     * simuleaza desfasurarea "jocului".
     *  - preia toti pacientii care trebuie sa intre in runda curenta si ii aduaga.
     * Triage Queue.
     *  - actualizeaza starea subiectului nostru(camera de urgenta)
     */
    public void simulate() {
        int time = 0;

        List<Patient> patients = new ArrayList<>();

        while (time < reader.getSimulationLength()) {
            while (reader.getIncomingPatients().size() != 0
                    && time == reader.getIncomingPatients().get(0).getTime()) {
                Patient patient = reader.getIncomingPatients().remove(0);
                patient.setInvestigationResult(InvestigationResult.NOT_DIAGNOSED);
                patient.setMessage(patient.getName() + " is "
                        + StateMessage.TRIAGEQUEUE.getValue());
                patients.add(patient);
            }

            EmergencyRoom.getEmergencyRoom().getTriageQueue().addAll(patients);
            patients.clear();

            EmergencyRoom.getEmergencyRoom().update();

            ++time;
        }
    }
}
