package specefika_klasser;

import java.util.Observable;
import general_classes.*;
import specefika_klasser.ButiksState;
import java.util.*;

/**
 * ButiksView implements the general view and prints output when events occur.
 */
public class ButiksView extends View {

    private final ButiksState state;

    /**
     * Constructor for ButiksView.
     * Prints initial information about the simulation.
     *
     * @param state the ButiksState being observed
     */
    public ButiksView(ButiksState state) {
        super(state);
        this.state = state;
        printStart();
    }

    /**
     * Update method that is called every time an event occurs and prints the corresponding output.
     */
    @Override
    public void update(Observable o, Object arg) {
        Event event = (Event) arg;
        printUpdate(event);
        if (event instanceof Stopphändelse) {
            endPrint();
        }
    }

    private void printStart() {
        String format = """
            PARAMETRAR
            ==========
            Antal kassor, N..........: %s
            Max som ryms, M..........: %s
            Ankomshastighet, lambda..: %s
            Plocktider, [P_min..Pmax]: [%s..%s]
            Betaltider, [K_min..Kmax]: [%s..%s]
            Frö, f...................: %s
            FÖRLOPP
            =======
               Tid Händelse  Kund  ?  led    ledT    I    $    :-(    köat    köT    köar    [Kassakö..]
            """;
        System.out.printf(format, state.getRegistersPopulation(), state.getMaxCustomerPopulation(), state.getAnkomsttid().getLambda(),
                state.getPlockTid().getMin(), state.getPlockTid().getMax(), state.getBetalningsTid().getMin(),
                state.getBetalningsTid().getMax(), state.getSeed());
    }

    void printUpdate(Event event) {
        String format = "%6.2f %s  %s  %3d %7.2f % 4d % 4d  % 4d    % 4d  %6.2f    % 4d    %s";

        if (event instanceof StartHändelse || event instanceof Stopphändelse) {
            System.out.printf("%6.2f %s%n", state.getTime(), event.toString());
            return;
        }

        String shopStatus = state.getShopOpen() ? "Ö" : "S";
        int ledigaKassor = state.getFreeRegisters();
        double tidLedigaKassor = state.getFreeRegistersTime();
        int antalKunder = state.getCustomerPopulation();
        int antalKunderHandlat = state.getCustomersShopped();
        int antalKunderMissat = state.getCustomersMissed();
        int antalKunderKöat = state.getCustomersQueued();
        double tidKunderKöat = state.getCustomerQueueTime();
        int kassaköSize = state.getRegisterQueue().size();
        String kassaköString = state.getRegisterQueue().toString();

        System.out.printf(format, state.getTime(), event.toString(), shopStatus, ledigaKassor, tidLedigaKassor,
                antalKunder, antalKunderHandlat, antalKunderMissat, antalKunderKöat, tidKunderKöat, kassaköSize,
                kassaköString);
        System.out.println();
    }

    void endPrint() {
        int totalCustomers = state.getCustomersShopped() + state.getCustomersMissed();
        int servedCustomers = state.getCustomersShopped();
        int missedCustomers = state.getCustomersMissed();
        int totalRegisters = state.getRegistersPopulation();
        double totalIdleTime = state.getFreeRegistersTime();
        double avgIdleTimePerRegister = totalIdleTime / totalRegisters;
        double idlePercentage = (avgIdleTimePerRegister / state.getLastPaymentTime()) * 100;
        int queuedCustomers = state.getCustomersQueued();
        double totalQueueTime = state.getCustomerQueueTime();
        double avgQueueTime = totalQueueTime / queuedCustomers;

        String message = String.format("1) Av %d kunder handlade %d medan %d missades.%n" +
                        "2) Total tid %d kassor varit lediga: %.2f te.%n" +
                        "   Genomsnittlig ledig kassatid: %.2f te (dvs %.2f%% av tiden från öppning tills sista kunden betalat).%n" +
                        "3) Total tid %d kunder tvingats köa: %.2f te.%n" +
                        "   Genomsnittlig kötid: %.2f te.%n",
                totalCustomers, servedCustomers, missedCustomers, totalRegisters, totalIdleTime,
                avgIdleTimePerRegister, idlePercentage, queuedCustomers, totalQueueTime, avgQueueTime);

        System.out.println(message);
    }
}