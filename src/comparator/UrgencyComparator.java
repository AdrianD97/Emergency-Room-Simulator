package comparator;

import entities.Patient;
import enums.Urgency;

import java.util.Comparator;

public final class UrgencyComparator implements Comparator<Patient> {
    private static UrgencyComparator urgencyComparator = new UrgencyComparator();

    public static UrgencyComparator getUrgencyComparator() {
        return urgencyComparator;
    }

    private UrgencyComparator() {
    }

    /**
     * compara doi pacienti dupa urgenta acestora.
     * @param o1 primul pacient
     * @param o2 al doilea pacient
     * @return un integer necesar sortarii unor pacientii pe baza urgentei lor
     */
    @Override
    public int compare(Patient o1, Patient o2) {
        if (o1.getUrgency() != Urgency.IMMEDIATE && o2.getUrgency() == Urgency.IMMEDIATE) {
            return 1;
        }

        if (o1.getUrgency() == Urgency.IMMEDIATE && o2.getUrgency() == Urgency.IMMEDIATE) {
            if (o1.getState().getSeverity() == o2.getState().getSeverity()) {
                return (o1.getName().compareTo(o2.getName()) >= 0) ?  -1 : 1;
            } else {
                return (o1.getState().getSeverity() - o2.getState().getSeverity() >= 0 ? -1 : 1);
            }
        }

        if (o1.getUrgency() == Urgency.IMMEDIATE) {
            return -1;
        }

        if (o1.getUrgency() != Urgency.URGENT && o2.getUrgency() == Urgency.URGENT) {
            return 1;
        }

        if (o1.getUrgency() == Urgency.URGENT && o2.getUrgency() == Urgency.URGENT) {
            if (o1.getState().getSeverity() == o2.getState().getSeverity()) {
                return (o1.getName().compareTo(o2.getName()) >= 0) ?  -1 : 1;
            } else {
                return (o1.getState().getSeverity() - o2.getState().getSeverity() >= 0 ? -1 : 1);
            }
        }

        if (o1.getUrgency() == Urgency.URGENT) {
            return -1;
        }

        if (o1.getUrgency() != Urgency.LESS_URGENT && o2.getUrgency() == Urgency.LESS_URGENT) {
            return 1;
        }

        if (o1.getUrgency() == Urgency.LESS_URGENT && o2.getUrgency() == Urgency.LESS_URGENT) {
            if (o1.getState().getSeverity() == o2.getState().getSeverity()) {
                return (o1.getName().compareTo(o2.getName()) >= 0) ?  -1 : 1;
            } else {
                return (o1.getState().getSeverity() - o2.getState().getSeverity() >= 0 ? -1 : 1);
            }
        }

        if (o1.getUrgency() == Urgency.LESS_URGENT) {
            return -1;
        }

        if (o1.getUrgency() != Urgency.NON_URGENT && o2.getUrgency() == Urgency.NON_URGENT) {
            return 1;
        }

        if (o1.getUrgency() == Urgency.NON_URGENT && o2.getUrgency() == Urgency.NON_URGENT) {
            if (o1.getState().getSeverity() == o2.getState().getSeverity()) {
                return (o1.getName().compareTo(o2.getName()) >= 0) ?  -1 : 1;
            } else {
                return (o1.getState().getSeverity() - o2.getState().getSeverity() >= 0 ? -1 : 1);
            }
        }

        if (o1.getUrgency() == Urgency.NON_URGENT) {
            return -1;
        }

        if (o1.getState().getSeverity() == o2.getState().getSeverity()) {
            return (o1.getName().compareTo(o2.getName()) >= 0) ?  -1 : 1;
        } else {
            return (o1.getState().getSeverity() - o2.getState().getSeverity() >= 0 ? -1 : 1);
        }
    }
}
