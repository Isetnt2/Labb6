package main_klasser;

import general_classes.EventQueue;
import general_classes.Simulator;
import general_classes.Stopphändelse;
import specefika_klasser.ButiksState;
import specefika_klasser.eventhändelser.Betalningshändelse;
import specefika_klasser.eventhändelser.StängerHändelse;
import general_classes.StartHändelse;
import java.util.Random;
public class Optimize {

    public static void main(String[] args) {
        int value = 0;
        if(value == 1) {
            int resultat = hittaMinimumKassor(K.M, K.L, K.LOW_PAYMENT_TIME, K.HIGH_PAYMENT_TIME, K.LOW_COLLECTION_TIME,
                    K.HIGH_COLLECTION_TIME, K.SEED, K.END_TIME, K.STOP_TIME);
            System.out.println("Resultat -- " + resultat);
        } else {
            int resultat = hittaMinimumKassorFörSeed(K.M, K.L, K.LOW_PAYMENT_TIME, K.HIGH_PAYMENT_TIME,
                    K.LOW_COLLECTION_TIME, K.HIGH_COLLECTION_TIME, K.SEED, K.END_TIME, K.STOP_TIME);
            System.out.println("Resultat -- " + resultat);
        }
    }

    public static ButiksState runSimulationOnce(int maxKunder,int antalKassor, Double lambda,Double kmin,
                                                double kmax,double pmin, double pmax, int f, double endTime, double stopTime){
        ButiksState state = new ButiksState(maxKunder,antalKassor,lambda,kmin,kmax,pmin,pmax,f);
        EventQueue queue = new EventQueue();

        queue.insert(new StartHändelse(state, queue));
        queue.insert(new Stopphändelse(state, queue, 999));
        queue.insert(new StängerHändelse(queue, endTime, state));

        Simulator sim = new Simulator(queue, state);
        sim.run();
        return state;
    }

    public static int hittaMinimumKassorFörSeed(int maxKunder, double lambda, double kmin, double kmax,
                                                double pmin, double pmax, int seed, double endTime, double stopTime){

        int mål = runSimulationOnce(maxKunder, maxKunder, lambda, kmin, kmax, pmin, pmax, seed,
                endTime, stopTime).getKunderMissade();
        int L = 0;
        int R = maxKunder;
        int bästa = Integer.MAX_VALUE;
        int bästaJustNu = Integer.MAX_VALUE;

        while (L<R) {
            int medel = (L+R)/2;
            int missat = runSimulationOnce(maxKunder, medel, lambda, kmin, kmax,
                    pmin, pmax, seed, endTime, stopTime).getKunderMissade();

            if(missat < bästa || (missat == bästa) && medel < bästaJustNu){
                bästa = missat;
                bästaJustNu = medel;
            }
            if(missat > mål){
                L = medel + 1;
            } else {
                R = medel;
            }

        }
        return bästaJustNu;
    }
    public static int hittaMinimumKassor(int maxKunder, double lambda,double kmin,double kmax,double pmin,
                                         double pmax, int seed, double endTime, double stopTime){
        int räknare = 0;

        Random rnd = new Random(seed);

        int senastReturnerad = hittaMinimumKassorFörSeed(maxKunder, lambda, kmin, kmax,
                pmin, pmax, rnd.nextInt(), endTime, stopTime);

        while (räknare < 100){
            int nuvarandeReturneradMax = hittaMinimumKassorFörSeed(maxKunder, lambda, kmin, kmax,
                    pmin, pmax, rnd.nextInt(), endTime, stopTime);
            System.out.println(nuvarandeReturneradMax);

            if(nuvarandeReturneradMax <= senastReturnerad){
                System.out.println("Samma");
                räknare++;
            } else {
                System.out.println("Högre");
                senastReturnerad = nuvarandeReturneradMax;
                räknare = 0;
            }
        }
        return senastReturnerad;
    }


}
