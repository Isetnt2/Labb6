/*
 * Isak Ahlberg
 * Joline Hallberg
 * John Kågström
 * Oscar Bucht
 */
package general_classes;

public class StartHändelse extends Event{
    public StartHändelse(State state, EventQueue queue){
        super(state, queue, 0);
    }
    @Override
    public void runEvent(){
        super.runEvent();
    }
}