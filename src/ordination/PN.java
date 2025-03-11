package ordination;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class PN extends Ordination {

    private double antalEnheder;
    private int antalGangeGivet;
    private ArrayList<LocalDate> datoerGivet;

    public PN(LocalDate startDato, LocalDate slutDato, Patient patient, double antalEnheder) {
        super(startDato, slutDato, patient);
        this.antalEnheder = antalEnheder;
        this.antalGangeGivet = 0;
        this.datoerGivet = new ArrayList<>();
    }

    /**
     * Registrerer at der er givet en dosis paa dagen givetDato
     * Returnerer true hvis givetDato er inden for ordinationens gyldighedsperiode og datoen huskes
     * Retrurner false ellers og datoen givetDato ignoreres
     * @param givetDato
     * @return
     */
    public boolean givDosis(LocalDate givetDato) {
        if(!(givetDato.isBefore(super.getStartDato()) || givetDato.isAfter(super.getSlutDato()))) {
            datoerGivet.add(givetDato);
            antalGangeGivet++;
            return true;
        }
        return false;
    }

    public double doegnDosis() {
        return samletDosis() / ChronoUnit.DAYS.between(datoerGivet.getFirst(), datoerGivet.getLast()) + 1;
    }

    @Override
    public String getType() {
        return "PN";
    }


    public double samletDosis() {
        return antalGangeGivet * antalEnheder;
    }

    /**
     * Returnerer antal gange ordinationen er anvendt
     * @return
     */
    public int getAntalGangeGivet() {
        return antalGangeGivet;
    }

    public double getAntalEnheder() {
        return antalEnheder;
    }

}
