package main_klasser;

import general_classes.*;
import specefika_klasser.ButiksState;
import specefika_klasser.ButiksView;

public class RunSim {
    //skapar butiksstaten och skickar argumenten för att skapa allt som behövs i staten
    ButiksState state = new ButiksState(MaxCustomerPopulation, RegisterPopulation, ankomsttid, plocktid, kassakö, skapakund, seed);

    public void skapaHändelser(){
        //start, stopp och stänger händelse
        EventQueue queue = new EventQueue();
        EventQueue.insert(new StartHändelse(state, queue));
        EventQueue.insert(new StängerHändelse(state, queue));
        EventQueue.insert(new Stopphändelse(state, 1234, queue));
    }

    public void skapaView(boolean lll){
        ButiksView view = new ButiksView(state);
        state.addObserver(view);
    }
    public static void main(String[] args){
        skapaHändelser();
        skapaView();
        RunSim runsim = new RunSim();
    }
}