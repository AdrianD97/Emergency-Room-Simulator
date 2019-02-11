package estimator;

import enums.IllnessType;
import enums.Urgency;
import severity.SeverityConstants;

import java.util.HashMap;
import java.util.Map;

public final class UrgencyEstimator {

    private static UrgencyEstimator instance;
    private Map<Urgency, HashMap<IllnessType, Integer>> algorithm;

    private UrgencyEstimator() {
        algorithm = new HashMap<Urgency, HashMap<IllnessType, Integer>>() {
            {
                put(Urgency.IMMEDIATE,
                        new HashMap<IllnessType, Integer>() {
                            {
                                put(IllnessType.ABDOMINAL_PAIN,
                                        SeverityConstants.IMMEDIATE_ABD_PAIN);
                                put(IllnessType.ALLERGIC_REACTION,
                                        SeverityConstants.IMMEDIATE_ALL_REAC);
                                put(IllnessType.BROKEN_BONES, SeverityConstants.IMMEDIATE_BROK_BON);
                                put(IllnessType.BURNS, SeverityConstants.IMMEDIATE_BURNS);
                                put(IllnessType.CAR_ACCIDENT, SeverityConstants.IMMEDIATE_CAR_ACC);
                                put(IllnessType.CUTS, SeverityConstants.IMMEDIATE_CUTS);
                                put(IllnessType.FOOD_POISONING,
                                        SeverityConstants.IMMEDIATE_FOO_POI);
                                put(IllnessType.HEART_ATTACK, SeverityConstants.IMMEDIATE_HEA_ATT);
                                put(IllnessType.HEART_DISEASE, SeverityConstants.IMMEDIATE_HEA_DIS);
                                put(IllnessType.HIGH_FEVER, SeverityConstants.IMMEDIATE_HIG_FEV);
                                put(IllnessType.PNEUMONIA, SeverityConstants.IMMEDIATE_PNEU);
                                put(IllnessType.SPORT_INJURIES,
                                        SeverityConstants.IMMEDIATE_SPO_INJ);
                                put(IllnessType.STROKE, SeverityConstants.IMMEDIATE_STROKE);

                            }
                        });

                put(Urgency.URGENT,
                        new HashMap<IllnessType, Integer>() {
                            {
                                put(IllnessType.ABDOMINAL_PAIN, SeverityConstants.URGENT_ABD_PAIN);
                                put(IllnessType.ALLERGIC_REACTION,
                                        SeverityConstants.URGENT_ALL_REAC);
                                put(IllnessType.BROKEN_BONES, SeverityConstants.URGENT_BROK_BON);
                                put(IllnessType.BURNS, SeverityConstants.URGENT_BURNS);
                                put(IllnessType.CAR_ACCIDENT, SeverityConstants.URGENT_CAR_ACC);
                                put(IllnessType.CUTS, SeverityConstants.URGENT_CUTS);
                                put(IllnessType.FOOD_POISONING, SeverityConstants.URGENT_FOO_POI);
                                put(IllnessType.HEART_ATTACK, SeverityConstants.URGENT_HEA_ATT);
                                put(IllnessType.HEART_DISEASE, SeverityConstants.URGENT_HEA_DIS);
                                put(IllnessType.HIGH_FEVER, SeverityConstants.URGENT_HIG_FEV);
                                put(IllnessType.PNEUMONIA, SeverityConstants.URGENT_PNEU);
                                put(IllnessType.SPORT_INJURIES, SeverityConstants.URGENT_SPO_INJ);
                                put(IllnessType.STROKE, SeverityConstants.URGENT_STROKE);
                            }
                        });

                put(Urgency.LESS_URGENT,
                        new HashMap<IllnessType, Integer>() {
                            {
                                put(IllnessType.ABDOMINAL_PAIN,
                                        SeverityConstants.LESS_URGENT_ABD_PAIN);
                                put(IllnessType.ALLERGIC_REACTION,
                                        SeverityConstants.LESS_URGENT_ALL_REAC);
                                put(IllnessType.BROKEN_BONES,
                                        SeverityConstants.LESS_URGENT_BROK_BON);
                                put(IllnessType.BURNS,
                                        SeverityConstants.LESS_URGENT_BURNS);
                                put(IllnessType.CAR_ACCIDENT,
                                        SeverityConstants.LESS_URGENT_CAR_ACC);
                                put(IllnessType.CUTS,
                                        SeverityConstants.LESS_URGENT_CUTS);
                                put(IllnessType.FOOD_POISONING,
                                        SeverityConstants.LESS_URGENT_FOO_POI);
                                put(IllnessType.HEART_ATTACK,
                                        SeverityConstants.LESS_URGENT_HEA_ATT);
                                put(IllnessType.HEART_DISEASE,
                                        SeverityConstants.LESS_URGENT_HEA_DIS);
                                put(IllnessType.HIGH_FEVER,
                                        SeverityConstants.LESS_URGENT_HIG_FEV);
                                put(IllnessType.PNEUMONIA,
                                        SeverityConstants.LESS_URGENT_PNEU);
                                put(IllnessType.SPORT_INJURIES,
                                        SeverityConstants.LESS_URGENT_SPO_INJ);
                                put(IllnessType.STROKE, SeverityConstants.LESS_URGENT_STROKE);
                            }
                        });

            }
        };
    }

    public static UrgencyEstimator getInstance() {
        if (instance == null) {
            instance = new UrgencyEstimator();
        }
        return instance;
    }

    public Urgency estimateUrgency(IllnessType illnessType, int severity) {
        if (severity >= algorithm.get(Urgency.IMMEDIATE).get(illnessType)) {
            return Urgency.IMMEDIATE;
        }
        if (severity >= algorithm.get(Urgency.URGENT).get(illnessType)) {
            return Urgency.URGENT;
        }
        if (severity >= algorithm.get(Urgency.LESS_URGENT).get(illnessType)) {
            return Urgency.LESS_URGENT;
        }
        return Urgency.NON_URGENT;
    }
}
