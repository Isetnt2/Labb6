package main_klasser;

import general_classes.*;
import specefika_klasser.ButiksState;
import specefika_klasser.ButiksView;
import specefika_klasser.eventhändelser.ButikStartHändelse;
import specefika_klasser.eventhändelser.StängerHändelse;

public class RunSim {
    //skapar butiksstaten och skickar argumenten för att skapa allt som behövs i staten
    static ButiksState state = new ButiksState(5,2, 1.0, 2.0,3.0,0.5, 1.0, 1234);

    public void skapaHändelser(){
        //start, stopp och stänger händelse
        EventQueue queue = new EventQueue();
        queue.insert(new StartHändelse(state, queue));
        queue.insert(new StängerHändelse(queue, 10, state));
        queue.insert(new Stopphändelse(state, queue, 999));
    }

    public void skapaView(){
        ButiksView view = new ButiksView(state);
        state.addObserver(view);
    }
    public static void main(String[] args){
        int n = 0;

        if(n == 0) {
            // Ex 1
            ButiksState state = new ButiksState(5,2, 1.0,
                    2.0,3.0,0.5, 1.0, 1234);
            EventQueue queue = new EventQueue();
            queue.insert(new ButikStartHändelse(state, queue));
            queue.insert(new StängerHändelse(queue, 10, state));
            queue.insert(new Stopphändelse(state, queue, 999));

            ButiksView view = new ButiksView(state);
            state.addObserver(view);

            Simulator sim = new Simulator(queue, state);
            sim.run();
        } else {
            // Ex 2
            ButiksState state = new ButiksState(7,2,3.0,
                    0.35,0.6,0.6,0.9,13);
            EventQueue queue = new EventQueue();
            queue.insert(new ButikStartHändelse(state, queue));
            queue.insert(new StängerHändelse(queue, 8, state));
            queue.insert(new Stopphändelse(state, queue, 999));

            ButiksView view = new ButiksView(state);
            state.addObserver(view);

            Simulator sim = new Simulator(queue, state);
            sim.run();
        }
    }
}