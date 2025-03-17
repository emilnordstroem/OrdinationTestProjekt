package ordination;

import java.time.LocalDate;
import java.util.ArrayList;

public class PN extends Ordination{
    private double antalEnheder;
    private int antalGangeGivet;
    private ArrayList<LocalDate> anvendteDatoer = new ArrayList<>();

    public PN(LocalDate startDato, LocalDate slutDato, double antalEnheder) {
        super(startDato, slutDato);
        this.antalEnheder = antalEnheder;
    }

    /**
     * Registrerer at der er givet en dosis paa dagen givetDato
     * Returnerer true hvis givetDato er inden for ordinationens gyldighedsperiode og datoen huskes
     * Retrurner false ellers og datoen givetDato ignoreres
     * @param givetDato
     * @return
     */
    public boolean givDosis(LocalDate givetDato) {
        if(givetDato.isAfter(super.getStartDato()) && givetDato.isBefore(super.getSlutDato())){
            antalGangeGivet++;
            anvendteDatoer.add(givetDato);
            return true;
        }
        return false;
    }

    public double doegnDosis() {
        int antalOrdinationerAnvendt = super.getStartDato().compareTo(LocalDate.now());
        return (antalOrdinationerAnvendt * antalEnheder) / super.antalDage();
    }

    @Override
    public String getType() {
        return "PN";
    }


    public double samletDosis() {
        return antalEnheder * super.antalDage();
    }

    /**
     * Returnerer antal gange ordinationen er anvendt
     * @return
     */
    public int getAntalGangeGivet() {
        return super.getStartDato().compareTo(LocalDate.now());
    }

    public double getAntalEnheder() {
        return antalEnheder;
    }

}
