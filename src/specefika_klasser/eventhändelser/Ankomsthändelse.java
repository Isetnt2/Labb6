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
import specefika_klasser.SkapaKund.Kund;

public class AnkomstHändelse extends Event{
    private Kund kund;
    private ButiksState state;

    /**
     *
     * @param state
     * @param queue
     * @param time
     * @param kund
     */
    public AnkomstHändelse(ButiksState state, EventQueue queue, double time, Kund kund){
        super(state, queue, time);
        this.kund = kund;
        this.state = state;
    }


    @Override
    public void runEvent(){
        super.runEvent();

        if (this.state.StatusÖppenButik()){
            double ankomstTid = this.state.getAnkomstTid().finishTime(time);
            AnkomstHändelse ankomsthändelse = new AnkomstHändelse(state, queue, ankomstTid, this.state.getSkapaKund().getKund());
            this.queue.insert(ankomsthändelse);

            if (this.state.getAntalKunder() < this.state.getMaxAntalKunder()){
                this.state.increaseCustomerPopulation();

                double plocktid = this.state.getPlockTid().slutTid(time);
                PlockHändelse plockhändelse = new PlockHändelse(this.state, this.queue, plocktid, this.kund);
                this.queue.insert(plockhändelse);
            }
            else{
                ((ButiksState)this.state).increaseCustomersMissed();
            }
        }
        else{

        }
    }
    @Override
    public String toString(){
        return String.format("Ankomst % 6d", this.kund.id);
    }
}