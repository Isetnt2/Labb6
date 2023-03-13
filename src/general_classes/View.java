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

    /**
     *
     * @param state
     */
    public View(State state){
        this.state = state;
    }

    /**
     *
     * @param o     the observable object.
     * @param arg   an argument passed to the {@code notifyObservers}
     *                 method.
     */
    @Override
    public void update(Observable o, Object arg) {
        //Do Nothing
    }
}