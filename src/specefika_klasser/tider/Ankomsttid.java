/*
 * Isak Ahlberg
 * Joline Hallberg
 * John Kågström
 * Oscar Bucht
 */
package specefika_klasser.tider;

import java.util.Random;

public class Ankomsttid {
    private Random random;
    private double lambda;

    /**
     *
     * @param seed
     * @param lambda
     */
    public Ankomsttid(long seed, double lambda){
        this.random = new Random(seed);
        this.lambda = lambda;
    }

    /**
     *
     * @param currentTime
     * @return
     */
    public double finishTime(double currentTime){
    //Håkans vvv
        return currentTime + (Math.log(random.nextDouble())/(-lambda));
    }

    public double getLambda(){
        return lambda;
    }


}