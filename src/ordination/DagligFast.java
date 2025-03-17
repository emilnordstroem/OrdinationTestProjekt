package ordination;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class DagligFast extends Ordination {
    private final Dosis[] doser;

    public DagligFast(LocalDate startDato, LocalDate slutDato) {
        super(startDato, slutDato);
        this.doser = new Dosis[4];
    }

    public Dosis[] getDoser() {
        return doser;
    }

    public void opretDosis(LocalTime tid, double antal) {
        try {
            Dosis dosis = new Dosis(tid, antal);
            int hour = tid.getHour();
            int[] indexMapping = {3, 0, 1, 2}; // 0-6 -> 3, 7-11 -> 0, 12-17 -> 1, 18-23 -> 2

            int index = indexMapping[hour / 6];

            doser[index] = dosis;
        }
        catch (IllegalArgumentException illegalArgumentException) {
            System.out.println("Dividerer med 0");
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
        return "DagligFast";
    }


}
