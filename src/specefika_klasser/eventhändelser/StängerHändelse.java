package specefika_klasser.eventhändelser;

import general_classes.Event;
import general_classes.EventQueue;
import general_classes.State

public class StängerHändelse extends Event{
    public StängerHändelse(EventQueue queue, double time, State state){
        super(state, queue, time);
    }

    public void runEvent(){
        //kallar runEvent för att notifiera att en händelse händer
        super.runEvent();
        //sätter shopen till stängd/false
        this.state.setShopStatus(false);
    }
}