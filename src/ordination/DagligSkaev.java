package ordination;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;

public class DagligSkaev extends Ordination {
    private final ArrayList<Dosis> doser;
    public DagligSkaev(LocalDate startDato, LocalDate slutDato, Patient patient) {
        super(startDato, slutDato, patient);
        this.doser = new ArrayList<>();
    }

    public ArrayList<Dosis> getDoser() {
        return new ArrayList<>(doser);
    }

    public void opretDosis(LocalTime tid, double antal) {
        if(doser.size() != 6) {
            Dosis dosis = new Dosis(tid, antal);
            doser.add(dosis);
            Collections.sort(doser);
        }
    }

    @Override
    public double samletDosis() {
        int samletDosis = 0;
        for (Dosis dosis : doser) {
            samletDosis += dosis.getAntal() * super.antalDage();
        }
        return samletDosis;
    }

    @Override
    public double doegnDosis() {
        return samletDosis() / super.antalDage();
    }

    @Override
    public String getType() {
        return "DagligSkaev";
    }
}
