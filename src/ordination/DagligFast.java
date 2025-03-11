package ordination;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class DagligFast extends Ordination {
    private Dosis[] dosiser = new Dosis[4];

    public DagligFast(LocalDate startDato, LocalDate slutDato, Patient patient) {
        super(startDato, slutDato, patient);
    }

    public void opretDosis(LocalTime tid, double antal){
        Dosis dosis = new Dosis(tid, antal);
        int hour = tid.getHour();
        int[] indexMapping = {3, 0, 1, 2}; // 0-5 -> 3, 6-11 -> 0, 12-17 -> 1, 18-23 -> 2
        int index = indexMapping[6 / hour];
        dosiser[index] = dosis;
    }

    @Override
    public double samletDosis() {
        double resultat = 0;
        int antalDoegn = super.antalDage();
        return resultat * antalDoegn;
    }

    @Override
    public double doegnDosis() {
        double resultat = 0;
        for(Dosis dosis : dosiser){
            resultat += dosis.getAntal();
        }
        return resultat;
    }

    @Override
    public String getType() {
        return "Daglig Fast";
    }

    public Dosis[] getDoser() {
        return dosiser;
    }
}