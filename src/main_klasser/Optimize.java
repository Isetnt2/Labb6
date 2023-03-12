package main_klasser;

import general_classes.EventQueue;
import general_classes.Simulator;
import general_classes.Stopphändelse;
import specefika_klasser.ButiksState;
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


}
