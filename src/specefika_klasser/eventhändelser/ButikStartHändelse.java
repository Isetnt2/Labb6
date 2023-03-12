package specefika_klasser.eventhändelser;

import general_classes.EventQueue;
import general_classes.StartHändelse;
import general_classes.State;
import specefika_klasser.ButiksState;

public class ButikStartHändelse extends StartHändelse {

    private ButiksState state;

    public ButikStartHändelse(ButiksState state, EventQueue queue) {
        super(state, queue);
        this.state = state;
    }

    public void runEvent(){
        super.runEvent();
        double tid = this.state.getAnkomstTid().finishTime(this.time);

        this.queue.insert(new AnkomstHändelse(this.state, this.queue, tid, this.state.getSkapaKund().getKund()));
    }
}
