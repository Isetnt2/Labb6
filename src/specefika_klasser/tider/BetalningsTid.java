/*
 * Isak Ahlberg
 * Joline Hallberg
 * John Kågström
 * Oscar Bucht
 */
package specefika_klasser.tider;

import java.util.Random;

public class BetalningsTid {
    private Random random;
    private double min, max;

    /**
     *
     * @param seed
     * @param min
     * @param max
     */
    public BetalningsTid(long seed, double min, double max){
        this.random = new Random(seed);
        this.min = min;
        this.max = max;
    }

    /**
     *
     * @param currentTime
     * @return
     */
    public double finishTime(double currentTime){
        return currentTime + min + random.nextDouble()*(max-min);
    }

    public double getMin(){
        return this.min;
    }
    public double getMax(){
        return this.max;
    }
}

