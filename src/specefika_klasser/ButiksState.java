
package specefika_klasser; // the class is part of the specefika_klasser package

import general_classes.State; // imports (general) State to be able to extend State.
import general_classes.Event;
import specefika_klasser.eventhändelser.AnkomstHändelse;
import specefika_klasser.eventhändelser.Betalningshändelse;
import specefika_klasser.eventhändelser.StängerHändelse;
import specefika_klasser.tider.Ankomsttid;
import specefika_klasser.tider.BetalningsTid;
import specefika_klasser.tider.PlockTid;

public class ButiksState extends State {

    // variables and etc
    
    // customer related variables
    private int MaxCustomerPopulation, CustomerPopulation, CustomersShopped, CustomersQueued, CustomersMissed;

    // registry related variables
    private int RegistersPopulation, CashierPopulation, FreeRegisters;

    // time related variables
    private double CustomerQueueTime, FreeRegistersTime, ClosingTime, LastPaymentTime;

    // others
    private boolean ÖppenButik = false;
    private int seed;
    private Ankomsttid ankomsttid;
    private BetalningsTid betalningsTid;
    private PlockTid plockTid;
    private FIFO kassaKö;
    private SkapaKund skapaKund;



    public ButiksState(int maxCustomerPopulation, int RegisterPopulation, double lambda,
                       double kmin, double kmax, double pmin, double pmax, int seed) {

        this.MaxCustomerPopulation = maxCustomerPopulation;
        this.RegistersPopulation = RegisterPopulation;
        this.ankomsttid = new Ankomsttid(seed, lambda);
        this.betalningsTid = new BetalningsTid(seed, kmin, kmax);
        this.plockTid = new PlockTid(seed, pmin, pmax);
        this.kassaKö = new FIFO();
        this.skapaKund = new SkapaKund();
        this.seed = seed;
        this.ÖppenButik = true;

    }

    public int getMaxCustomerPopulation(){
        return this.MaxCustomerPopulation;
    }

    public int getRegistersPopulation(){
        return this.RegistersPopulation;
    }

    public int getFreeRegisters(){
        return this.FreeRegisters;
    }

    public void increaseFreeRegisters(){
        this.FreeRegisters++;
    }

    public void decreaseFreeRegisters(){
        this.FreeRegisters--;
    }

    public int getCustomerPopulation(){
        return this.CustomerPopulation;
    }

    public void increaseCustomerPopulation(){
        if(this.CustomerPopulation + 1 > this.MaxCustomerPopulation){
            throw new RuntimeException("Too many customers");
        }
        this.CustomerPopulation++;
    }

    public void decreaseCustomerPopulation(){
        this.CustomerPopulation--;
    }

    public int getCustomersShopped(){
        return this.CustomersShopped;
    }

    public void increaseCustomersShopped(){
        this.CustomersShopped++;
    }

    public int getCustomersQueued(){
        return this.CustomersQueued;
    }

    public void increaseCustomersQueued(){
        this.CustomersQueued++;
    }

    public int getCustomersMissed(){
        return this.CustomersMissed;
    }
    public void increaseCustomersMissed(){
        this.CustomersMissed++;
    }

    public double getCustomerQueueTime(){
        return this.CustomerQueueTime;
    }

    public void increaseCustomerQueueTime(double time){
        this.CustomerQueueTime += time;
    }

    public double getFreeRegistersTime(){
        return this.FreeRegistersTime;
    }

    public void increaseFreeResgistersTime(double time){
        this.FreeRegistersTime += time;
    }

    public boolean StatusÖppenButik(){
        return this.ÖppenButik;
    }

    public void setShopStatus(boolean status){
        this.shopOpen = status;
        if(!status){
            this.ClosingTime = this.getTime();
        }
    }

    public Ankomsttid getAnkomsttid(){
        return this.ankomsttid;
    }

    public BetalningsTid getBetalningsTid(){
        return this.betalningsTid;
    }

    public PlockTid getPlockTid(){
        return this.plockTid;
    }

    public SkapaKund getSkapaKund(){
        return this.skapaKund;
    }

    public FIFO getRegisterQueue(){
        return this.kassaKö;
    }

    public int getSeed(){
        return this.seed;
    }

    @Override
    public void notify(Event source) {

        if (source instanceof Betalningshändelse) {
            this.LastPaymentTime = source.getTime();
        }

        if (!(source instanceof AnkomstHändelse && !this.shopOpen) && !(source instanceof StängerHändelse)) {
            // Beräkna tid
            double deltaTime = source.getTime() - this.getTime();

            // Antal kunder som köar * tiden
            double köTid = this.getRegisterQueue().size() * deltaTime;
            // Antal lediga kassor * tiden
            double ledigTid = this.getFreeRegisters() * deltaTime;

            // Lägg till i state
            this.increaseCustomerQueueTime(köTid);
            this.increaseFreeResgistersTime(ledigTid);
        }

        // Allmäna notify för att få view att skriva ut
        super.notify(source);
    }

    public double getCloseTime() {
        return this.ClosingTime;
    }

    public double getLastPaymentTime() {
        return this.LastPaymentTime;
    }

}
