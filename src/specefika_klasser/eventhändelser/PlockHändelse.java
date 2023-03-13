/*
 * Isak Ahlberg
 * Joline Hallberg
 * John Kågström
 * Oscar Bucht
 */
package specefika_klasser.eventhändelser;

import general_classes.Event;
import general_classes.EventQueue;
import specefika_klasser.ButiksState;
import specefika_klasser.SkapaKund.*;

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
        if(this.state.getAntalLedigaKassor() > 0) {
            this.state.decreaseFreeRegisters();
            double betalingstid = this.state.getBetalningsTid().finishTime(this.time);
            Betalningshändelse betalningshändelse = new Betalningshändelse(state, queue, betalingstid, this.kund);

            this.queue.insert(betalningshändelse);
        } else {
            this.state.getRegisterQueue().add(this.kund);
            this.state.increaseCustomersQueued();
        }
    }
    public String toString(){
        return String.format("Plock % 6d", this.kund.id);
    }
}