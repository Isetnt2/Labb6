package general_classes;

import java.util.ArrayList;
public class EventQueue {
    ArrayList<Event> queue;

    public EventQueue(){
        this.queue = new ArrayList<Event>();
    }

    public void insert(Event e){
        for (int i = 0; i < queue.size(); i++) {
            if(e.getTime() < queue.get(i).getTime()){
                queue.add(i, e);
                continue;
            }
        }
        queue.add(e);
    }

    public Event next(){
        return queue.remove(0);
    }
    public int size(){
        return queue.size();
    }
}