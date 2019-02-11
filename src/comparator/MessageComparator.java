package comparator;

import entities.Patient;

import java.util.Comparator;

public final class MessageComparator implements Comparator<Patient> {
    private static MessageComparator instance = new MessageComparator();

    public static MessageComparator getInstance() {
        return instance;
    }

    private MessageComparator() {
    }

    /**
     * compara doi pacienti pe baza mesajului corespunzator starii lor.
     * @param o1 primul pacientul
     * @param o2 al doilea pacient
     * @return un int necesar in sortarea unor pacientii dupa mesajul corespunzator starii lor
     */
    @Override
    public int compare(Patient o1, Patient o2) {
        return (o1.getMessage().compareTo(o2.getMessage()) > 0 ? 1 : -1);
    }
}
