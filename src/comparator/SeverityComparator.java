package comparator;

import entities.Patient;

import java.util.Comparator;

public final class SeverityComparator implements Comparator<Patient> {
    /**
     * compara severitatea a doi pacienti.
     * @param o1 primul pacient
     * @param o2 al doilea pacient
     * @return un integer necesar in sortarea unor pacienti dupa severitate
     */
    @Override
    public int compare(Patient o1, Patient o2) {
        int s1 = o1.getState().getSeverity();
        int s2 = o2.getState().getSeverity();

        if (s1 == s2) {
            return 0;
        }

        if (s1 < s2) {
            return 1;
        }

        return -1;
    }
}
