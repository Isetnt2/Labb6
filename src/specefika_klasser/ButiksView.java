package specefika_klasser;

import java.util.Observable;
import general_classes.*;

// ButiksView implements the general view and prints output when events occur.
public class ButiksView extends View {

    private final ButiksState state;
    
    // This is the constructor for ButiksView. It prints initial information about the simulation. ButiksState being observed from here.
    public ButiksView(ButiksState state) {
        super(state);
        this.state = state;
        printStart();
    }
    
    // Update method that is called every time an event occurs and prints the corresponding output.
    @Override
    public void update(Observable o, Object arg) {
        Event event = (Event) arg;
        printUpdate(event);
        if (event instanceof Stopphändelse) {
            endPrint();
        }
    }

    // Head of the simulation, prints out parameters.
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
        System.out.printf(format, state.getAntalKassor(), state.getMaxAntalKunder(), state.getAnkomstTid().getLambda(),
                state.getPlockTid().getMin(), state.getPlockTid().getMax(), state.getBetalningsTid().getMin(),
                state.getBetalningsTid().getMax(), state.getSeed());
    }

    // Prints out simulation updates.
    void printUpdate(Event event) {
        String format = "%6.2f %s  %s  %3d %7.2f % 4d % 4d  % 4d    % 4d  %6.2f    % 4d    %s";

        if (event instanceof StartHändelse || event instanceof Stopphändelse) {
            System.out.printf("%6.2f %s%n", state.getTime(), event.toString());
            return;
        }

        String shopStatus = state.getButikÖppen() ? "Ö" : "S";
        int ledigaKassor = state.getAntalLedigaKassor();
        double tidLedigaKassor = state.getLedigaKassorTid();
        int antalKunder = state.getAntalKunder();
        int antalKunderHandlat = state.getAntalKunderHandlat();
        int antalKunderMissat = state.getKunderMissade();
        int antalKunderKöat = state.getKunderKöat();
        double tidKunderKöat = state.getKundKöTid();
        int kassaköSize = state.getRegisterQueue().size();
        String kassaköString = state.getRegisterQueue().toString();

        System.out.printf(format, state.getTime(), event.toString(), shopStatus, ledigaKassor, tidLedigaKassor,
                antalKunder, antalKunderHandlat, antalKunderMissat, antalKunderKöat, tidKunderKöat, kassaköSize,
                kassaköString);
        System.out.println();
    }

    // Final prints cluster. Tells store Story. (total time registers have been free and other interesting statistics...)
    void endPrint() {
        int totalCustomers = state.getAntalKunderHandlat() + state.getKunderMissade();
        int servedCustomers = state.getAntalKunderHandlat();
        int missedCustomers = state.getKunderMissade();
        int totalRegisters = state.getAntalKassor();
        double totalIdleTime = state.getLedigaKassorTid();
        double avgIdleTimePerRegister = totalIdleTime / totalRegisters;
        double idlePercentage = (avgIdleTimePerRegister / state.getSistaBetalningsTid()) * 100;
        int queuedCustomers = state.getKunderKöat();
        double totalQueueTime = state.getKundKöTid();
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
