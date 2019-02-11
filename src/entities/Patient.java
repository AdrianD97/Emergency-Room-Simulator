package entities;

import entities.state.State;
import enums.InvestigationResult;
import enums.Urgency;

public final class Patient {
    private int id;
    private String name;
    private int age;
    private int time;
    private State state;
    private Urgency urgency;
    private InvestigationResult investigationResult;
    private int nrRounds;
    private String message;

    /**
     * getter.
     * @return id-ul pacientului
     */
    public int getId() {
        return id;
    }

    /**
     * setter.
     * @param id id-ul pacientului
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * getter.
     * @return numele pacientului
     */
    public String getName() {
        return name;
    }

    /**
     * setter.
     * @param name numele pacientului
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * getter.
     * @return varsta pacientului
     */
    public int getAge() {
        return age;
    }

    /**
     * setter.
     * @param age varsta pacientului
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * getter.
     * @return numarul rundei in care pacientul va intra in "joc"
     */
    public int getTime() {
        return time;
    }

    /**
     * setter.
     * @param time runda in care pacientul va intra in joc
     */
    public void setTime(int time) {
        this.time = time;
    }

    /**
     * getter.
     * @return starea pacientului(boala + severitatea bolii)
     */
    public State getState() {
        return state;
    }

    /**
     * setter.
     * @param state starea pacientului(boala + severitatea bolii)
     */
    public void setState(State state) {
        this.state = state;
    }

    /**
     * getter.
     * @return urgenta pacientului
     */
    public Urgency getUrgency() {
        return urgency;
    }

    /**
     * setter.
     * @param urgency urgenta pacientului
     */
    public void setUrgency(Urgency urgency) {
        this.urgency = urgency;
    }

    /**
     * getter.
     * @return rezultatul invetigatiei
     */
    public InvestigationResult getInvestigationResult() {
        return investigationResult;
    }

    /**
     * setter.
     * @param investigationResult rezultatul investigatiei
     */
    public void setInvestigationResult(InvestigationResult investigationResult) {
        this.investigationResult = investigationResult;
    }

    /**
     * getter.
     * @return cate runde mai are pacientul pana cand isi termina tratamentul
     */
    public int getNrRounds() {
        return nrRounds;
    }

    /**
     * setter.
     * @param nrRounds cate runde mai are pacientul pana cand isi termina tratamentul
     */
    public void setNrRounds(int nrRounds) {
        this.nrRounds = nrRounds;
    }

    /**
     * getter.
     * @return mesajul corespunzator starii pacientului
     */
    public String getMessage() {
        return message;
    }

    /**
     * setter.
     * @param message mesajul corespunzator starii pacientului
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
