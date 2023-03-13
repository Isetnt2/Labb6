/*
 * Isak Ahlberg
 * Joline Hallberg
 * John Kågström
 * Oscar Bucht
 */
package specefika_klasser; // the class is part of the specefika_klasser package

import general_classes.State; // imports (general) State to be able to extend State.
import general_classes.Event;
import specefika_klasser.eventhändelser.*;
import specefika_klasser.tider.*;

// creates necessary variables
public class ButiksState extends State {

    // customer related variables
    private int MaxAntalKunder, AntalKunder, AntalKunderHandlat, KunderKöat, KunderMissade;

    // registry related variables
    private int AntalKassor, AntalLedigaKassor;

    // time related variables
    private double KundKöTid, LedigaKassorTid, StängTid, SistaBetalningsTid;

    // others
    private boolean ButikÖppen = true;
    private int seed;
    private Ankomsttid ankomstTid;
    private BetalningsTid betalningsTid;
    private PlockTid plockTid;
    private FIFO kassaKö;
    private SkapaKund skapaKund;

    /**
     *
     * @param maxAntalKunder
     * @param RegisterPopulation
     * @param lambda
     * @param kmin
     * @param kmax
     * @param pmin
     * @param pmax
     * @param seed
     */
    // implements variables
    public ButiksState(int maxAntalKunder, int RegisterPopulation, double lambda,
                       double kmin, double kmax, double pmin, double pmax, int seed) {

        this.MaxAntalKunder = maxAntalKunder;
        this.AntalKassor = RegisterPopulation;
        this.ankomstTid = new Ankomsttid(seed, lambda);
        this.betalningsTid = new BetalningsTid(seed, kmin, kmax);
        this.plockTid = new PlockTid(seed, pmin, pmax);
        this.kassaKö = new FIFO();
        this.skapaKund = new SkapaKund();
        this.seed = seed;
        this.AntalLedigaKassor = this.AntalKassor;
        ÖppnaButiken();

    }

    public int getMaxAntalKunder(){
        return this.MaxAntalKunder;
    }

    public int getAntalKassor(){
        return this.AntalKassor;
    }

    public int getAntalLedigaKassor(){
        return this.AntalLedigaKassor;
    }

    public void increaseFreeRegisters(){
        this.AntalLedigaKassor++;
    }

    public void decreaseFreeRegisters(){
        this.AntalLedigaKassor--;
    }

    public int getAntalKunder(){
        return this.AntalKunder;
    }

    public void increaseCustomerPopulation(){
        if(this.AntalKunder + 1 > this.MaxAntalKunder){
            throw new RuntimeException("Too many customers");
        }
        this.AntalKunder++;
    }

    public void decreaseCustomerPopulation(){
        this.AntalKunder--;
    }

    public int getAntalKunderHandlat(){
        return this.AntalKunderHandlat;
    }

    public void increaseCustomersShopped(){
        this.AntalKunderHandlat++;
    }

    public int getKunderKöat(){
        return this.KunderKöat;
    }

    public void increaseCustomersQueued(){
        this.KunderKöat++;
    }

    public int getKunderMissade(){
        return this.KunderMissade;
    }
    public void increaseCustomersMissed(){
        this.KunderMissade++;
    }

    public double getKundKöTid(){
        return this.KundKöTid;
    }

    /**
     *
     * @param time
     */
    public void increaseCustomerQueueTime(double time){
        this.KundKöTid += time;
    }

    public double getLedigaKassorTid(){
        return this.LedigaKassorTid;
    }

    /**
     *
     * @param time
     */
    public void increaseFreeResgistersTime(double time){
        this.LedigaKassorTid += time;
    }


    public boolean StatusÖppenButik(){
        return this.ButikÖppen;
    }

    public void ÖppnaButiken() {
        this.ButikÖppen = true;
    }
    
    public void StängButiken() {
        this.ButikÖppen = false;
    }

    /**
     *
     * @param status
     */
    public void setShopStatus(boolean status){
        this.ButikÖppen = status;
        if(!status){
            this.StängTid = this.getTime();
        }
    }

    public Ankomsttid getAnkomstTid(){
        return this.ankomstTid;
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

    /**
     *
     * @param source
     */
    @Override
    public void notify(Event source) {

        if (source instanceof Betalningshändelse) {
            this.SistaBetalningsTid = source.getTime();
        }

        if (!(source instanceof AnkomstHändelse && !this.ButikÖppen) && !(source instanceof StängerHändelse)) {
            // Beräkna tid
            double deltaTime = source.getTime() - this.getTime();

            // Antal kunder som köar * tiden
            double köTid = this.getRegisterQueue().size() * deltaTime;
            // Antal lediga kassor * tiden
            double ledigTid = this.getAntalLedigaKassor() * deltaTime;

            // Lägg till i state
            this.increaseCustomerQueueTime(köTid);
            this.increaseFreeResgistersTime(ledigTid);
        }

        // Allmäna notify för att få view att skriva ut
        super.notify(source);
    }

    public double getCloseTime() {
        return this.StängTid;
    }

    public double getSistaBetalningsTid() {
        return this.SistaBetalningsTid;
    }

}
