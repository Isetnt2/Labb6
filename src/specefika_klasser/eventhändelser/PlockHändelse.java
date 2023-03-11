package specefika_klasser.eventhändelser;

import general_classes.Event;
import general_classes.EventQueue;
import general_classes.State;
import specefika_klasser.ButiksState;

import java.util.Random;

public class PlockHändelse extends Event {

    private Kund kund;
    private ButiksState state;

    public PlockHändelse(ButiksState state, EventQueue queue, double time, Kund kund) {
        super(state, queue, time);
        this.kund = kund;
        this.state = state;
    }

    public void runEvent(){
        super.runEvent();
        if(this.state.getLedigaKassor() > 0) {
            this.state.decreaseLedigaKassor();
            double betalingstid = this.state.getPaymentTime().finishTime(this.time);
            Betalningshändelse betalningshändelse = new Betalningshändelse(state, queue, betalingstid, this.kund);

            this.queue.insert(betalningshändelse);
        } else {
            this.state.getKassaKö().add(this.customer);
            this.state.increaseAntalKunderKöat();
        }
    }
    public String toString(){
        return String.format("Plock % 6d", this.kund.id);
    }
}