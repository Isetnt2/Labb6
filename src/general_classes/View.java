/*
 * Isak Ahlberg
 * Joline Hallberg
 * John Kågström
 * Oscar Bucht
 */
package general_classes;

import java.util.Observable;
import java.util.Observer;

public class View implements Observer {
    protected State state;

    public View(State state){
        this.state = state;
    }
    @Override
    public void update(Observable o, Object arg) {
        //Do Nothing
    }
}