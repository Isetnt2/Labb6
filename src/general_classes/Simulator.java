package general_classes;
public class Simulator{
    EventQueue queue = new EventQueue();
    State state = new State();


    public Simulator(EventQueue queue, State state){
        this.queue = queue;
        this.state = state;
    }

    //körs sålänge kön inte är tom eller om stop i state returnerar true
    public void run(){
        while(queue.size() != 0){
            if(state.stop == true){
                break;
            }
            //kör igenom eventen i listan
            Event nyttEvent = queue.next();
            nyttEvent.runEvent();
        }
    }
}