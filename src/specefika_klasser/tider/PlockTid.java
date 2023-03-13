/*
 * Isak Ahlberg
 * Joline Hallberg
 * John Kågström
 * Oscar Bucht
 */
package specefika_klasser.tider;

import java.util.Random;

public class PlockTid{
    private Random random;
    private double min;
    private double max;

    public PlockTid(long f, double min, double max){
        this.random = new Random(f);
        this.min = min;
        this.max = max;
    }
    public double slutTid(double nuvarandeTid){
        return nuvarandeTid + random.nextDouble(min, max);
    }

    public double getMin() {
        return this.min;
    }

    public double getMax() {
        return this.max;
    }
}