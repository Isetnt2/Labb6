package general_classes;

import java.util.Observable;

public class State extends Observable {

    public boolean stop;
    public double time;

    public State(){
        this.stop = false;
    }

    public void notify(Event source) {
        // Spara när eventet hände
        this.setTime(source.getTime());

        this.setChanged();
        this.notifyObservers(source);
    }

    public double getTime(){
        return this.time;
    }

    public void setTime(double time){
        if(this.time > time){
            throw new RuntimeException("Cannot decrease time");
        }
        this.time = time;
    }

}