package specefika_klasser.eventhändelser;

import general_classes.Event;
import general_classes.EventQueue;
import general_classes.State;
import specefika_klasser.ButiksState;

public class Ankomsthändelse{
    private Customer customer;
    private ButiksState state;

    public Ankomsthändelse(ButiksState state, EventQueue queue, double time, Cutomer customer){
        super(state, queue, time);
        this.cutomer = customer;
        this.state = state;
    }
    @Override
    public void runEvent(){
        super.runEvent();

        if (this.state.getShopOpen()){
            double ankomstTid = this.state.getAnkomsttid(),finishTime(time);
            Ankomsthändelse ankomsthändelse = new Ankomsthändelse(state, queue, ankomstTid, this.state.getSkapaKund().getKund());
            this.queue.insert(ankomsthändelse);

            if (this.state.getAntalKunder() < this.state.getMaxAntalKunder()){
                this.state.increaseAntalKunder();

                double plocktid = this.state.getPlockTid().finishTime(time);
                PlockHändelse plockhändelse = new PlockHändelse(this.state, this.queue, plocktid, this.kund);
                this.queue.insert(plocktid);
            }
            else{
                ((ButiksState).this.state).increaseMissadeKunder();
            }
        }
        else{

        }
    }
    @Override
    public String toString(){
        return String.format("Ankomst % 6d", this.customer.id);
    }
}