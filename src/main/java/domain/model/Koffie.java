package domain.model;

import domain.DomainException;

public class Koffie {

    private int id, mlWater, mlMelk;
    private String naam;
    private double gramKoffie;

    public Koffie(String naam, double gramKoffie, int mlWater, int mlMelk) {
        setNaam(naam);
        setGramKoffie(gramKoffie);
        setMlWater(mlWater);
        setMlMelk(mlMelk);
    }

    public Koffie(String naam, double gramKoffie, int mlWater) {
        this(naam, gramKoffie, mlWater, 0);
    }

    public Koffie() {}

    public void duplicate(Koffie koffie) {
        setNaam(koffie.getNaam());
        setGramKoffie(koffie.getGramKoffie());
        setMlWater(koffie.getMlWater());
        setMlMelk(koffie.getMlMelk());
    }

    //setters
    public void setNaam(String naam) {
        if (naam.isEmpty() || naam == null) throw new DomainException("Vul een geldige naam in.");
        this.naam = naam;
    }

    public void setGramKoffie(double gramKoffie) {
        if (gramKoffie<=0) throw new DomainException("Vul een geldige hoeveelheid koffie in.");
        this.gramKoffie = gramKoffie;
    }

    public void setMlWater(int mlWater) {
        if (mlWater<=0) throw new DomainException("Vul een geldige hoeveelheid water in.");
        this.mlWater = mlWater;
    }

    public void setMlMelk(int mlMelk) {
        if (mlMelk<0) throw new DomainException("Vul een geldige hoeveelheid melk in.");
        this.mlMelk = mlMelk;
    }

    public void setId(int id) {
        if (id<0) throw new DomainException("Er werd een verkeerde id meegegeven.");
        this.id = id;
    }

    //getters
    public String getNaam() {
        return this.naam;
    }

    public double getGramKoffie() {
        return this.gramKoffie;
    }

    public int getMlWater() {
        return this.mlWater;
    }

    public int getMlMelk() {
        return this.mlMelk;
    }

    public int getId() {
        return this.id;
    }

    //functies
    @Override
    public String toString() {
        return "Naam: " + this.naam + ", " +
                "koffie: " + this.gramKoffie + "g, " +
                "water: " + this.mlWater + "ml, " +
                "melk: " + this.mlMelk + "ml.";
    }

    @Override
    public boolean equals(Object o) {
        boolean result = false;
        if (o instanceof Koffie) {
            Koffie k = (Koffie) o;
            if (this.getNaam().equals(k.getNaam()) && this.getGramKoffie() == k.getGramKoffie() &&
                    this.getMlWater() == k.getMlWater() && this.getMlMelk() == k.getMlMelk()) result = true;
        } return result;
    }

}
