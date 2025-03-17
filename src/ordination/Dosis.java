package ordination;

import java.time.LocalTime;

public class Dosis implements Comparable<Dosis>{
    private LocalTime tid;
    private double antal;

    public Dosis(LocalTime tid, double antal) {
        this.tid = tid;
        this.antal = antal;
    }

    public double getAntal() {
        return antal;
    }

    public void setAntal(double antal) {
        this.antal = antal;
    }

    public LocalTime getTid() {
        return tid;
    }

    public void setTid(LocalTime tid) {
        this.tid = tid;
    }

    @Override
    public String toString(){
        return "Kl: " + tid + "   antal:  " + antal;
    }

    @Override
    public int compareTo(Dosis dosis) {
        return tid.compareTo(dosis.getTid());
    }
}
