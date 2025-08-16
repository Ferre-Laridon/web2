package domain.db;

import domain.DomainException;
import domain.model.Koffie;

import java.util.ArrayList;

public class KoffieDB {

    private int sequence = 0;
    private final ArrayList<Koffie> koffies = new ArrayList<>();

    public KoffieDB() {
        Koffie americano = new Koffie("Americano", 10.6, 170);
        Koffie cappuccino = new Koffie("Cappuccino", 7, 40, 40);
        Koffie espresso = new Koffie("Espresso", 7, 40);

        this.add(americano);
        this.add(cappuccino);
        this.add(espresso);
    }

    public ArrayList<Koffie> getAll() {
        return koffies;
    }

    public void add(Koffie koffie) {
        if (koffie==null) throw new DomainException();
        if (koffies.contains(koffie)) throw new DomainException("Deze koffie bevindt zich al in de lijst.");
        this.sequence++;
        koffie.setId(sequence);
        koffies.add(koffie);
    }

    public void vervang(Koffie teVervangen, Koffie vervanger) {
        if (vervanger==null) throw new DomainException("Geen geldige koffie.");
        if (teVervangen==null) throw new DomainException("Er werd geen koffie gevonden.");
        for (Koffie k : koffies) {
            if (k.getId() == teVervangen.getId()) {
                k.duplicate(vervanger);
            }
        }
    }

    public Koffie getKoffie(String naam) {
        if (naam.trim().isEmpty()) return null;
        for (Koffie k : koffies) {
            if (k.getNaam().equalsIgnoreCase(naam)) return k;
        } return null;
    }

    public Koffie getKoffieById(int id) {
        if (id<1) throw new DomainException("Geef een geldige id mee.");
        for (Koffie k : koffies) {
            if (k.getId() == id) return k;
        } return null;
    }

    public Koffie getHoogsteVerhoudingCaff() {
        if (this.koffies.size() == 0) throw new DomainException("Er zijn geen koffies.");
        Koffie hoogsteKoffie = koffies.get(0);
        double hoogsteVerhouding = 0;
        for (Koffie k : koffies) {
            double verhouding = k.getGramKoffie()/(k.getMlWater()+k.getMlMelk());
            if (verhouding>hoogsteVerhouding) {
                hoogsteVerhouding = verhouding;
                hoogsteKoffie = k;
            }
        } return hoogsteKoffie;
    }

    public void delete(Koffie koffie) {
        if (koffie == null) throw new DomainException();
        koffies.remove(koffie);
    }

    @Override
    public String toString() {
        String info = "";
        for (Koffie k : koffies) {
            info += k.toString() + "\n";
        } return info;
    }
}
