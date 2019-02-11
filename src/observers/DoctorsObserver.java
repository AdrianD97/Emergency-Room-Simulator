package observers;

import entities.Doctor;
import entities.Patient;
import enums.StateMessage;
import info.DoctorsInfo;
import pair.Pair;
import subject.EmergencyRoom;

import java.util.Observer;
import java.util.Observable;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public final class DoctorsObserver implements Observer {

    /**
     * simuleaza verificarea de catre fiecare doctor,
     * daca pacientii sai au terminat tratamentul.
     * @param o subiectul nostru(cred ca te-ai plictisit de aceste cuvinte)
     * @param arg din nou ca la TriageObserver
     */
    @Override
    public void update(Observable o, Object arg) {
        EmergencyRoom emergencyRoom = (EmergencyRoom) o;

        HashMap<Doctor, List<Pair<Patient, String>>> messages;
        messages = new HashMap<>();

        for (Doctor d: EmergencyRoom.getEmergencyRoom().getDoctors()) {
            messages.put(d, new ArrayList<>());
        }

        for (Doctor doctor: emergencyRoom.getDoctors()) {
            for (int i = 0; i < doctor.getHospitalizedPatients().size(); ++i) {
                Patient patient = doctor.getHospitalizedPatients().get(i);
                if (patient.getState().getSeverity() <= 0 || patient.getNrRounds() <= 0) {
                    patient.setMessage(patient.getName() + " is "
                            + StateMessage.HOME_DONE_TREATMENT.getValue());
                    messages.get(doctor).add(new Pair<>(patient,
                            DoctorsInfo.getInstance().getName(doctor.getType())
                                    + " sent " + patient.getName() + " home"));
                    emergencyRoom.getEliminatedPatients().add(patient);
                    emergencyRoom.getHospitalizedPatients().remove(patient);
                    doctor.getHospitalizedPatients().remove(patient);
                    --i;
                } else {
                    messages.get(doctor).add(new Pair<>(patient,
                            DoctorsInfo.getInstance().getName(doctor.getType())
                                    + " says that " + patient.getName()
                                    + " should remain in hospital"));
                }
            }
        }

        System.out.println("~~~~ Doctors check their hospitalized patients and give verdicts ~~~~");
        for (Doctor d: EmergencyRoom.getEmergencyRoom().getDoctors()) {
            sort(messages.get(d));
            for (Pair<Patient, String> p: messages.get(d)) {
                System.out.println(p.getValue());
            }
        }

        System.out.println();
    }

    /**
     * sorteaza lista de mesaje alfabetic dupa numele pacientilor.
     * @param list lista de percehi : (pacient, mesaj)
     */
    private void sort(List<Pair<Patient, String>> list) {
        boolean sortate;

        do {
            sortate = true;

            for (int i = 0; i < list.size() - 1; ++i) {
                if (list.get(i).getKey().getName()
                        .compareTo(list.get(i + 1).getKey().getName()) > 0) {
                    Collections.swap(list, i, i + 1);
                    sortate = false;
                }
            }
        } while (!sortate);
    }
}
