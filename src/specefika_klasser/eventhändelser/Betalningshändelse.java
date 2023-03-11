package specefika_klasser.eventhändelser;

import general_classes.Event;
import general_classes.EventQueue;
import general_classes.State;
import specefika_klasser.ButiksState;

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

        this.state.decreaseAntalKunder();
        this.state.increaseAntalKunderHandlat();

        // kolla om det finns flera kunder i kassakö, och det finns tar den nästa
        // kunden, och sen skapa
        // en ny paymentEvent till kunden
        if (this.state.getKassakö().hasNext()) {
            Customer c = this.state.getKassakö().next();
            double paymentTime = this.state.getPaymentTime().finishTime(this.time);
            PaymentEvent paymentEvent = new PaymentEvent(state, queue, paymentTime, c);
            this.queue.insert(paymentEvent);
        }
        // om det inte finns flera kunder ökas antalet ledigaKassor
        else {
            this.state.increaseLedigaKassor();
        }
    }

    public String toString(){
        return ("Betalning från : " + this.customer.id);
    }
}