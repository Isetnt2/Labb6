/*
 * Isak Ahlberg
 * Joline Hallberg
 * John Kågström
 * Oscar Bucht
 */
package specefika_klasser.eventhändelser;

import general_classes.Event;
import general_classes.EventQueue;
import general_classes.State;
import specefika_klasser.ButiksState;
import specefika_klasser.SkapaKund;
import specefika_klasser.SkapaKund.Kund;

public class Betalningshändelse extends Event{

    ButiksState state;
    Kund kund;
    public Betalningshändelse(ButiksState state, EventQueue queue, double time, Kund kund) {
        super(state, queue, time);
        this.state = state;
        this.kund = kund;
    }

    public void runEvent(){
        super.runEvent();

        // minska antalet kunder i snabbköp, öka antalet kunder som har handlat

        this.state.decreaseCustomerPopulation();
        this.state.increaseCustomersShopped();

        // kolla om det finns flera kunder i kassakö, och det finns tar den nästa
        // kunden, och sen skapa
        // en ny paymentEvent till kunden
        if (this.state.getRegisterQueue().hasNext()) {
            Kund c = this.state.getRegisterQueue().next();
            double paymentTime = this.state.getBetalningsTid().finishTime(this.time);
            Betalningshändelse paymentEvent = new Betalningshändelse(state, queue, paymentTime, c);
            this.queue.insert(paymentEvent);
        }
        // om det inte finns flera kunder ökas antalet ledigaKassor
        else {
            this.state.increaseFreeRegisters();
        }
    }

    public String toString(){
        return ("Betalning från : " + this.kund.id);
    }
}