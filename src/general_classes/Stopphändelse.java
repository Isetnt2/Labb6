package general_classes;

public class Stopphändelse extends Event{
    public Stopphändelse(State state, EventQueue queue){
        super(state, queue, 0);
    }
    @Override
    public void runEvent(){
        super.runEvent();
        this.state.stop = true;
    }
}