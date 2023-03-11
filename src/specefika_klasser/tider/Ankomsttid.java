package specefika_klasser.tider;

import java.util.Random;

public class Ankomsttid {
    private Random random;
    private double lambda;

    public Ankomsttid(long seed, double lambda){
        this.random = new Random(seed);
        this.lambda = lambda;
    }

    public double finishTime(double currentTime){
    //Håkans vvv
        return currentTime + (Math.log(random.nextDouble())/(-lambda));
    }

    public double getLambda(){
        return lambda;
    }


}