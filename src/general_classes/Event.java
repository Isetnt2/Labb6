/*
 * Isak Ahlberg
 * Joline Hallberg
 * John Kågström
 * Oscar Bucht
 */
package general_classes;

public class Event{
    protected State state;
    protected EventQueue queue;
    protected double time;

    public Event(State state, EventQueue queue, double time){
        this.state = state;
        this.queue = queue;
        this.time = time;
    }

    public void runEvent(){
        this.state.notify(this);
    }

    public double getTime(){
        return time;
    }
}

