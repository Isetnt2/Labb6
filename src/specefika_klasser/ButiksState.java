
package specefika_klasser; // the class is part of the specefika_klasser package

import general_classes.State; // imports (general) State to be able to extend State.
import general_classes.Event; 
import general_classes.Stopphändelse;
import specefika_klasser.eventhändelser.Ankomsthändelse;
import specefika_klasser.eventhändelser.Betalningshändelse;

public class ButiksState extends State {

    // variables and etc
    
    // customer related variables
    private int MaxCustomerPopulation = 0;
    private int CustomerPopulation = 0;
    private int CustomersShoped = 0;
    private int CustomersQueued = 0;
    private int CustomersMissed = 0;

    // registry related variablse
    private int RegistersPopulation = 0;
    private int CashierPopulation = 0;
    private int FreeRegisters = 0;

    // time related variables
    private double CustomerQueueTime = 0.0;
    private double RegisterDownTime = 0.0;
    private double ClosingTime = 0.0;
    private double 

    // others



    public ButiksState {

    }

}
