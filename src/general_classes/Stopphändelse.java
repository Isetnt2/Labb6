/*
 * Isak Ahlberg
 * Joline Hallberg
 * John Kågström
 * Oscar Bucht
 */
package general_classes;

public class Stopphändelse extends Event{
    /**
     *
     * @param state
     * @param queue
     * @param time
     */
    public Stopphändelse(State state, EventQueue queue, int time){
        super(state, queue, time);
    }
    @Override
    public void runEvent(){
        super.runEvent();
        this.state.stop = true;
    }
}