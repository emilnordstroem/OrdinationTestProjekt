package ordination;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class DagligSkaev extends Ordination {
    private ArrayList<Dosis> dosiser = new ArrayList<>();

    public DagligSkaev(LocalDate startDato, LocalDate slutDato) {
        super(startDato, slutDato);
    }

    public void opretDosis(LocalTime tid, double antal) {
        Dosis nyDosis = new Dosis(tid, antal);
        for(Dosis nuvaerendeDosis : dosiser) {
            if (tid.equals(nuvaerendeDosis.getTid())) {
                nuvaerendeDosis.setAntal(antal);
                return;
            }
        }
        dosiser.add(nyDosis);
        Collections.sort(dosiser);
    }

    @Override
    public double samletDosis() {
        return doegnDosis() * (super.antalDage());
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
        return "Daglig Skæv";
    }

    public Dosis[] getDoser() {
        return dosiser.toArray(new Dosis[0]);
    }
}
