package specefika_klasser;
import java.util.ArrayList;
import java.util.Arrays;

import specefika_klasser.SkapaKund;

/**
 * FIFO kö som lagrar Kunder från SkapaKund.
 */
public class FIFO {
    ArrayList<SkapaKund.Kund> content = new ArrayList<SkapaKund.Kund>();

    /**
     * Lägger till en kund sist i kön
     * @param kund Kunden som ska läggas till
     */
    public void add(SkapaKund.Kund kund){
        content.add(kund);
    }

    /**
     * Returnerar och tar bort nästa kund som står först i kön
     * @return Kunden som står först i kön
     */
    public SkapaKund.Kund next(){
        return content.remove(0);
    }

    @Override
    public String toString() {
        int[] tmp = new int[content.size()];
        for(int i = 0; i < content.size(); i++){
            tmp[i] = (content.get(i).id);
        }
        return Arrays.toString(tmp);
    }
    /**
     * Kollar om det det finns en nästa kund i kön
     * @return En boolean för om det finns en nästa kund i kön
     */
    public Boolean hasNext(){
        if (content.size() != 0){
            return true;
        }
        else{
            return false;
        }
    }
    /**
     * Returnerar antalet kunder i kön
     * @return Antal kunder i kön
     */
    public int size() {
        return content.size();
    }
}