package info;

import enums.DoctorType;
import enums.IllnessType;
import pair.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public final class DoctorsInfo {
    public static final int T = 22;
    private static final double CARDIO_C1 = 0.4;
    private static final double CARDIO_C2 = 0.1;
    private static final double ERPHY_C1 = 0.1;
    private static final double ERPHY_C2 = 0.3;
    private static final double GASTRO_C1 = 0.5;
    private static final double GASTRO_C2 = 0.0;
    private static final double GEN_SUR_C1 = 0.2;
    private static final double GEN_SUR_C2 = 0.2;
    private static final double INTER_C1 = 0.01;
    private static final double INTER_C2 = 0.0;
    private static final double NEURO_C1 = 0.5;
    private static final double NEURO_C2 = 0.1;

    private static DoctorsInfo instance = new DoctorsInfo();

    private HashMap<DoctorType, List<IllnessType>> cureIllnes;
    private HashMap<DoctorType, Pair<Double, Double>> constants;
    private HashMap<DoctorType, String> names;

    public static DoctorsInfo getInstance() {
        return instance;
    }

    private DoctorsInfo() {
        cureIllnes = new HashMap<DoctorType, List<IllnessType>>() {
            {
                put(DoctorType.CARDIOLOGIST, new ArrayList<>(Arrays.asList(IllnessType.HEART_ATTACK,
                        IllnessType.HEART_DISEASE)));
                put(DoctorType.ER_PHYSICIAN,
                        new ArrayList<>(Arrays.asList(IllnessType.ALLERGIC_REACTION,
                        IllnessType.BROKEN_BONES, IllnessType.BURNS, IllnessType.CAR_ACCIDENT,
                        IllnessType.CUTS, IllnessType.HIGH_FEVER, IllnessType.SPORT_INJURIES)));
                put(DoctorType.GASTROENTEROLOGIST,
                        new ArrayList<>(Arrays.asList(IllnessType.ABDOMINAL_PAIN,
                        IllnessType.ALLERGIC_REACTION, IllnessType.FOOD_POISONING)));
                put(DoctorType.GENERAL_SURGEON,
                        new ArrayList<>(Arrays.asList(IllnessType.ABDOMINAL_PAIN,
                        IllnessType.BURNS, IllnessType.CAR_ACCIDENT, IllnessType.CUTS,
                        IllnessType.SPORT_INJURIES)));
                put(DoctorType.INTERNIST, new ArrayList<>(Arrays.asList(IllnessType.ABDOMINAL_PAIN,
                        IllnessType.ALLERGIC_REACTION, IllnessType.FOOD_POISONING,
                        IllnessType.HEART_DISEASE, IllnessType.HIGH_FEVER, IllnessType.PNEUMONIA)));
                put(DoctorType.NEUROLOGIST, new ArrayList<>(Arrays.asList(IllnessType.STROKE)));
            }
        };

        constants = new HashMap<DoctorType, Pair<Double, Double>>() {
            {
                put(DoctorType.CARDIOLOGIST, new Pair<>(CARDIO_C1, CARDIO_C2));
                put(DoctorType.ER_PHYSICIAN, new Pair<>(ERPHY_C1, ERPHY_C2));
                put(DoctorType.GASTROENTEROLOGIST, new Pair<>(GASTRO_C1, GASTRO_C2));
                put(DoctorType.GENERAL_SURGEON, new Pair<>(GEN_SUR_C1, GEN_SUR_C2));
                put(DoctorType.INTERNIST, new Pair<>(INTER_C1, INTER_C2));
                put(DoctorType.NEUROLOGIST, new Pair<>(NEURO_C1, NEURO_C2));
            }
        };

        names = new HashMap<DoctorType, String>() {
            {
                put(DoctorType.CARDIOLOGIST, "Cardiologist");
                put(DoctorType.ER_PHYSICIAN, "ERPhysician");
                put(DoctorType.GASTROENTEROLOGIST, "Gastroenterologist");
                put(DoctorType.GENERAL_SURGEON, "General Surgeon");
                put(DoctorType.INTERNIST, "Internist");
                put(DoctorType.NEUROLOGIST, "Neurologist");
            }
        };
    }

    /**
     * verifica daca un anumit doctor poate trata o anumita boala.
     * @param doctorType tipul doctorului
     * @param illnessType boala
     * @return true daca doctorlul poate trata boala, false astfel
     */
    public boolean cureIllness(DoctorType doctorType, IllnessType illnessType) {
        for (int i = 0; i < cureIllnes.get(doctorType).size(); ++i) {
            if (illnessType.getValue().equals(cureIllnes.get(doctorType).get(i).getValue())) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param doctorType tipul doctorului
     * @return cele doua constante specifiece unui anumit doctor.
     */
    public Pair<Double, Double> getConstants(DoctorType doctorType) {
        return constants.get(doctorType);
    }

    /**
     * @param doctorType tipul doctorului
     * @return intoarce un string necesar pentru mesajele de output corespunzator tipului de doctor
     */
    public String getName(DoctorType doctorType) {
        return names.get(doctorType);
    }
}
