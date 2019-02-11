package observers;

import comparator.NursesMessagesComparator;
import entities.Patient;
import info.DoctorsInfo;
import subject.EmergencyRoom;

import java.util.Observer;
import java.util.Observable;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public final class NursesObserver implements Observer {
    /**
     * simuleaza tratarea pacientilor de catre asistentele medicale.
     * @param o subiectul nostru (camera de urgenta).
     * @param arg la fel ca la TriageObserver :)
     */
    @Override
    public void update(Observable o, Object arg) {
        EmergencyRoom emergencyRoom = (EmergencyRoom) o;

        int nrPatients = emergencyRoom.getHospitalizedPatients().size();
        List<Patient> patients = new ArrayList<>();

        System.out.println("~~~~ Nurses treat patients ~~~~");
        for (int i = 0; i < nrPatients; ++i) {
            patients.add(emergencyRoom.getHospitalizedPatients().get(i));
        }

        Collections.sort(patients, NursesMessagesComparator.getInstance());

        int j = 0;
        for (int i = 0; i < patients.size(); ++i) {
            if (j >= EmergencyRoom.getEmergencyRoom().getNrNurses()) {
                j = 0;
            }

            patients.get(i).getState().setSeverity(patients.get(i)
                    .getState().getSeverity() - DoctorsInfo.T);
            patients.get(i).setNrRounds(patients.get(i).getNrRounds() - 1);

            if (patients.get(i).getNrRounds() == 1) {
                System.out.println("Nurse " + j + " treated " + patients.get(i).getName()
                        + " and patient has " + patients.get(i).getNrRounds() + " more round");
            } else {
                if (patients.get(i).getNrRounds() >= 0) {
                    System.out.println("Nurse " + j + " treated " + patients.get(i).getName()
                            + " and patient has " + patients.get(i).getNrRounds() + " more rounds");
                }
            }
            ++j;
        }

        System.out.println();
    }
}
