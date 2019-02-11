package observers;

import comparator.UrgencyComparator;
import entities.Patient;
import enums.InvestigationResult;
import enums.StateMessage;
import info.ERTechniciansInfo;
import subject.EmergencyRoom;

import java.util.Observable;
import java.util.Observer;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public final class InvestigationObserver implements Observer {
    /**
     * analizeaza fiecare pacient din investigation queue(trebuie sa fie operati, spitalizati etc.).
     * @param o subiectul nostru(camera de urgenta)
     * @param arg la fel ca la TriageObserver
     */
    @Override
    public void update(Observable o, Object arg) {
        EmergencyRoom emergencyRoom = (EmergencyRoom) o;
        List<Patient> patients = new ArrayList<>();

        Collections.sort(emergencyRoom.getInvestigationQueue(),
                UrgencyComparator.getUrgencyComparator());
        int nrPatients = emergencyRoom.getInvestigationQueue().size();
        for (int  i = 1; i <= emergencyRoom.getNrInvestigators() && i <= nrPatients; ++i) {
            patients.add(emergencyRoom.getInvestigationQueue().remove(0));
        }

        for (int i = 0; i < patients.size(); ++i) {
            int severity = patients.get(i).getState().getSeverity();
            if (severity > ERTechniciansInfo.C1) {
                patients.get(i).setInvestigationResult(InvestigationResult.OPERATE);
            } else {
                if (severity > ERTechniciansInfo.C2 && severity <= ERTechniciansInfo.C1) {
                    patients.get(i).setInvestigationResult(InvestigationResult.HOSPITALIZE);
                } else {
                    if (severity <= ERTechniciansInfo.C2) {
                        patients.get(i).setInvestigationResult(InvestigationResult.TREATMENT);
                    }
                }
            }
            patients.get(i).setMessage(patients.get(i).getName()
                    + " is " + StateMessage.EXAMINATIONSQUEUE.getValue());
        }

        emergencyRoom.getExaminationQueue().addAll(patients);
    }
}
