package general_classes;
public class Simulator{
    EventQueue queue = new EventQueue();
    State state = new State();
    public void Simulator(){
    }
    public void run(){
        while(queue.size() != 0){
            if(State.stop == true){
                break;
            }
            
        }
    }
}