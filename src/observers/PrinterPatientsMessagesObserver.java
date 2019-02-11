package observers;

import comparator.MessageComparator;
import entities.Patient;
import subject.EmergencyRoom;

import java.util.Observer;
import java.util.Observable;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public final class PrinterPatientsMessagesObserver implements Observer {
    private int round = 1;

    /**
     * afiseaza starea fiecarui pacient din camera de urgenta.
     * @param o subiectul nostru(camera de urgenta)
     * @param arg la fel ca la TriageObserver :))
     */
    @Override
    public void update(Observable o, Object arg) {
        EmergencyRoom emergencyRoom = (EmergencyRoom) o;
        List<Patient> patients = new ArrayList<>();

        patients.addAll(emergencyRoom.getTriageQueue());
        patients.addAll(emergencyRoom.getExaminationQueue());
        patients.addAll(emergencyRoom.getInvestigationQueue());
        patients.addAll(emergencyRoom.getEliminatedPatients());
        patients.addAll(emergencyRoom.getHospitalizedPatients());

        Collections.sort(patients, MessageComparator.getInstance());

        System.out.println("~~~~ Patients in round " + (round++) + " ~~~~");
        for (Patient patient: patients) {
            System.out.println(patient.getMessage());
        }

        System.out.println();
    }
}
