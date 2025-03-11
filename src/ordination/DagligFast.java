package ordination;

import java.time.LocalDate;
import java.time.LocalTime;

public class DagligFast extends Ordination {
    private final Dosis[] doser;

    DagligFast(LocalDate startDato, LocalDate slutDato, Patient patient) {
        super(startDato, slutDato, patient);
        this.doser = new Dosis[4];
    }

    public void opretDosis(LocalTime tid, double antal) {
        Dosis dosis = new Dosis(tid, antal);
        int hour = tid.getHour();
        int[] indexMapping = {3, 0, 1, 2}; // 0-5 -> 3, 6-11 -> 0, 12-17 -> 1, 18-23 -> 2
        int index = indexMapping[6 / hour];
        doser[index] = dosis;
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
        return "DagligFast";
    }


}
