package comparator;

import entities.Patient;

import java.util.Comparator;

public final class NursesMessagesComparator implements Comparator<Patient> {
    private static NursesMessagesComparator instance = new NursesMessagesComparator();

    public static NursesMessagesComparator getInstance() {
        return instance;
    }

    private NursesMessagesComparator() {
    }

    /**
     * compara doi pacienti pe baza numelui lor.
     * @param o1 primul pacient
     * @param o2 al doilea pacient
     * @return un integer necesar in sortarea unor pacienti dupa nume
     */
    @Override
    public int compare(Patient o1, Patient o2) {
        return ((o1.getName().compareTo(o2.getName()) > 0 ? 1 : -1));
    }
}
