package snabbköp;

import java.util.Observable;

import allmänt.Event;
import allmänt.StartEvent;
import allmänt.StopEvent;
import allmänt.View;
/**
 * SnabbköpView implementerar den allmäna vyn och gör fina utskrifter när event händer.
 */
public class SnabbköpView extends View {

    SnabbköpState state;

    /**
     * Konstruktor för SnabbköpView.
     * Skriver ut initiell info om simuleringen.
     * @param state SnabbköpState som observeras
     */
    public SnabbköpView(SnabbköpState state) {
        super(state);
        this.state = state;

        startPrint();
    }

    /**
     * Update-metod som anropas varje gång ett event sker och skriver ut raden för detta.
     */
    @Override
    public void update(Observable arg0, Object arg1) {
        // TODO gör en utskrift
        Event event = (Event) arg1;

        updatePrint(event);

        if (event instanceof StopEvent) {
            endPrint();
        }
    }

    void startPrint() {
        System.out.print(String.format(
                """
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
                """,
                state.getAntalKassor(),
                state.getMaxAntalKunder(),
                state.getArrivalTime().getLambda(),
                state.getPickupTime().getMin(),
                state.getPickupTime().getMax(),
                state.getPaymentTime().getMin(),
                state.getPaymentTime().getMax(),
                state.getSeed()
        ));
    }

    void updatePrint(Event event) {

        if (event instanceof StartEvent) {
            System.out.println(String.format("%6.2f %s", state.getTime(), event.toString()));
            return;
        }

        if (event instanceof StopEvent) {
            System.out.println(String.format("%6.2f %s", state.getTime(), event.toString()));
            return;
        }

        System.out.println(
                String.format(
                        "%6.2f %s  %s  %3d %7.2f % 4d % 4d  % 4d    % 4d  %6.2f    % 4d    %s",
                        state.getTime(),
                        event.toString(),
                        state.getShopOpen() ? "Ö" : "S",
                        state.getLedigaKassor(),
                        state.getTidLedigaKassor(),
                        state.getAntalKunder(),
                        state.getAntalKunderHandlat(),
                        state.getAntalKunderMissat(),
                        state.getAntalKunderKöat(),
                        state.getTidKunderKöat(),
                        state.getKassakö().size(),
                        state.getKassakö().toString()
                )
        );
    }

    void endPrint() {
        System.out.println(String.format("""
            1) Av %d kunder handlade %d medan %d missades.
            2) Total tid %d kassor varit lediga: %.2f te.
            Genomsnittlig ledig kassatid: %.2f te (dvs %.2f%% av tiden från öppning tills sista kunden
            betalat).
            3) Total tid %d kunder tvingats köa: %.2f te.
            Genomsnittlig kötid: %.2f te.
                """,
                state.getAntalKunderHandlat()+state.getAntalKunderMissat(),
                state.getAntalKunderHandlat(),
                state.getAntalKunderMissat(),
                state.getAntalKassor(),
                state.getTidLedigaKassor(),
                state.getTidLedigaKassor()/state.getAntalKassor(),
                ((state.getTidLedigaKassor()/state.getAntalKassor()) / state.getLastPaymentTime())*100,
                state.getAntalKunderKöat(),
                state.getTidKunderKöat(),
                state.getTidKunderKöat()/state.getAntalKunderKöat()
        ));
    }
}