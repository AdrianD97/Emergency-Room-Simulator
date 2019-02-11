package observers;

import comparator.UrgencyComparator;
import entities.Doctor;
import entities.Patient;
import enums.DoctorType;
import enums.IllnessType;
import enums.InvestigationResult;
import enums.StateMessage;
import info.DoctorsInfo;
import subject.EmergencyRoom;

import java.util.Observer;
import java.util.Observable;
import java.util.Collections;
import java.util.List;

public final class ExaminationObserver implements Observer {
    private static final int CONSTANT = 3;
    /**
     * consulta/opereaza fiecare pacient din examination queue.
     * @param o subeiectul nostru(camera de urgenta)
     * @param arg la fel ca la TriageObserver :))
     */
    @Override
    public void update(Observable o, Object arg) {
        EmergencyRoom emergencyRoom = (EmergencyRoom) o;

        Collections.sort(emergencyRoom.getExaminationQueue(),
                UrgencyComparator.getUrgencyComparator());

        for (int i = 0; i < emergencyRoom.getExaminationQueue().size(); ++i) {
            Patient patient = emergencyRoom.getExaminationQueue().get(i);

            Doctor doctor = null;
            if (patient.getInvestigationResult().getValue()
                    .equals(InvestigationResult.OPERATE.getValue())) {
                doctor = getFirstSurgeon(emergencyRoom.getDoctorsLists()
                        .get(patient.getState().getIllnessName()));
                if (doctor == null) {
                    patient.setMessage(patient.getName() + " is "
                            + StateMessage.OTHERHOSPITAL.getValue());
                    emergencyRoom.getEliminatedPatients().add(patient);
                    emergencyRoom.getExaminationQueue().remove(patient);
                    --i;
                } else {
                    patient.setInvestigationResult(InvestigationResult.HOSPITALIZE);
                    patient.setMessage(patient.getName() + " is "
                            + messageOperate(doctor.getType()));
                    patient.getState().setSeverity((int) (patient.getState().getSeverity()
                            - Math.round(patient.getState().getSeverity() * DoctorsInfo
                            .getInstance().getConstants(doctor.getType()).getValue())));
                    patient.setNrRounds(Integer.max((int) Math.round(patient.getState()
                            .getSeverity() * DoctorsInfo.getInstance()
                            .getConstants(doctor.getType()).getKey()), CONSTANT));
                    emergencyRoom.getExaminationQueue().remove(patient);
                    doctor.getHospitalizedPatients().add(patient);
                    emergencyRoom.getHospitalizedPatients().add(patient);
                    --i;
                }
            } else {
                if (patient.getInvestigationResult().getValue()
                        .equals(InvestigationResult.HOSPITALIZE.getValue())) {
                    doctor = emergencyRoom.getDoctorsLists()
                            .get(patient.getState().getIllnessName()).get(0);
                    patient.setNrRounds(Integer.max((int) Math.round(patient.getState()
                            .getSeverity() * DoctorsInfo.getInstance()
                            .getConstants(doctor.getType()).getKey()), CONSTANT));
                    patient.setMessage(patient.getName() + " is "
                            + messageHospitalized(doctor.getType()));
                    doctor.getHospitalizedPatients().add(patient);
                    emergencyRoom.getHospitalizedPatients().add(patient);
                    emergencyRoom.getExaminationQueue().remove(patient);
                    --i;
                } else {
                    if (patient.getInvestigationResult().getValue()
                            .equals(InvestigationResult.TREATMENT.getValue())) {
                        doctor = emergencyRoom.getDoctorsLists()
                                .get(patient.getState().getIllnessName()).get(0);
                        patient.setMessage(patient.getName() + " is "
                                + messageSentHome(doctor.getType()));
                        emergencyRoom.getExaminationQueue().remove(patient);
                        emergencyRoom.getEliminatedPatients().add(patient);
                        --i;
                    } else {
                        if (patient.getInvestigationResult().getValue()
                                .equals(InvestigationResult.NOT_DIAGNOSED.getValue())) {
                            doctor = emergencyRoom.getDoctorsLists()
                                    .get(patient.getState().getIllnessName()).get(0);
                            if (patient.getState().getSeverity() <= doctor.getMaxForTreatment()) {
                                patient.setMessage(patient.getName() + " is "
                                        + messageSentHome(doctor.getType()));
                                emergencyRoom.getExaminationQueue().remove(patient);
                                emergencyRoom.getEliminatedPatients().add(patient);
                                --i;
                            } else {
                                emergencyRoom.getInvestigationQueue().add(patient);
                                patient.setMessage(patient.getName() + " is "
                                        + StateMessage.INVESTIGATIONSQUEUE.getValue());
                                emergencyRoom.getExaminationQueue().remove(patient);
                                --i;
                            }
                        }
                    }
                }
            }

            if (doctor != null) {
                for (IllnessType illnessType: EmergencyRoom.getEmergencyRoom()
                        .getDoctorsLists().keySet()) {
                    if (emergencyRoom.getDoctorsLists().get(illnessType).contains(doctor)) {
                        emergencyRoom.getDoctorsLists().get(illnessType).remove(doctor);
                        emergencyRoom.getDoctorsLists().get(illnessType).add(doctor);
                    }
                }
            }
        }
    }

    /**
     * @param doctors lista de doctori care pot trata o anumita boala
     * @return primul chirurg care poate trata o anumita boala
     */
    private Doctor getFirstSurgeon(List<Doctor> doctors) {
        for (int i = 0; i < doctors.size(); ++i) {
            if (doctors.get(i).isSurgeon()) {
                return doctors.get(i);
            }
        }

        return null;
    }

    /**
     * @param doctorType tipul doctorului
     * @return un mesaj corespunzator tipului de doctor
     */
    private String messageSentHome(DoctorType doctorType) {
        if (doctorType.getValue().equals(DoctorType.CARDIOLOGIST.getValue())) {
            return StateMessage.HOME_CARDIO.getValue();
        }

        if (doctorType.getValue().equals(DoctorType.ER_PHYSICIAN.getValue())) {
            return StateMessage.HOME_ERPHYSICIAN.getValue();
        }

        if (doctorType.getValue().equals(DoctorType.GASTROENTEROLOGIST.getValue())) {
            return StateMessage.HOME_GASTRO.getValue();
        }

        if (doctorType.getValue().equals(DoctorType.GENERAL_SURGEON.getValue())) {
            return StateMessage.HOME_SURGEON.getValue();
        }

        if (doctorType.getValue().equals(DoctorType.INTERNIST.getValue())) {
            return StateMessage.HOME_INTERNIST.getValue();
        }

        return StateMessage.HOME_NEURO.getValue();
    }

    /**
     * @param doctorType tipul doctorului
     * @return un mesaj corespunzator tipului de doctor
     */
    private String messageOperate(DoctorType doctorType) {
        if (doctorType.getValue().equals(DoctorType.CARDIOLOGIST.getValue())) {
            return StateMessage.OPERATED_CARDIO.getValue();
        }

        if (doctorType.getValue().equals(DoctorType.ER_PHYSICIAN.getValue())) {
            return StateMessage.OPERATED_ERPHYSICIAN.getValue();
        }

        if (doctorType.getValue().equals(DoctorType.GENERAL_SURGEON.getValue())) {
            return StateMessage.OPERATED_SURGEON.getValue();
        }

        return StateMessage.OPERATED_NEURO.getValue();
    }

    /**
     * @param doctorType tipul doctorului
     * @return un mesaj corespunzator tipului de doctor
     */
    private String messageHospitalized(DoctorType doctorType) {
        if (doctorType.getValue().equals(DoctorType.CARDIOLOGIST.getValue())) {
            return StateMessage.HOSPITALIZED_CARDIO.getValue();
        }

        if (doctorType.getValue().equals(DoctorType.ER_PHYSICIAN.getValue())) {
            return StateMessage.HOSPITALIZED_ERPHYSICIAN.getValue();
        }

        if (doctorType.getValue().equals(DoctorType.GASTROENTEROLOGIST.getValue())) {
            return StateMessage.HOSPITALIZED_GASTRO.getValue();
        }

        if (doctorType.getValue().equals(DoctorType.GENERAL_SURGEON.getValue())) {
            return StateMessage.HOSPITALIZED_SURGEON.getValue();
        }

        if (doctorType.getValue().equals(DoctorType.INTERNIST.getValue())) {
            return StateMessage.HOSPITALIZED_INTERNIST.getValue();
        }

        return StateMessage.HOSPITALIZED_NEURO.getValue();
    }
}
