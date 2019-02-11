package observers;

import comparator.SeverityComparator;
import entities.Patient;
import estimator.UrgencyEstimator;
import subject.EmergencyRoom;

import java.util.Collections;
import java.util.Observer;
import java.util.Observable;
import java.util.List;
import java.util.ArrayList;

public final class TriageObserver implements Observer {
    private SeverityComparator severityComparator = new SeverityComparator();

    /**
     * atribuie fiecarui pacient din triage queue o urgenta pe baza severitatii.
     * @param o subiectul nostru(camera de urgenta)
     * @param arg un argument care are valoarea null (a fost generat de editor :) )
     */
    @Override
    public void update(Observable o, Object arg) {
        EmergencyRoom emergencyRoom = (EmergencyRoom) o;
        List<Patient> patients = new ArrayList<>();

        Collections.sort(emergencyRoom.getTriageQueue(), severityComparator);


        int nrPatients = emergencyRoom.getTriageQueue().size();

        for (int i = 1; i <= emergencyRoom.getNrNurses() && i <= nrPatients; ++i) {
            patients.add(emergencyRoom.getTriageQueue().remove(0));
        }

        for (int i = 0; i < patients.size(); ++i) {
            patients.get(i).setUrgency(UrgencyEstimator.getInstance()
                    .estimateUrgency(patients.get(i).getState().getIllnessName(),
                    patients.get(i).getState().getSeverity()));
        }

        emergencyRoom.getExaminationQueue().addAll(patients);
    }
}
