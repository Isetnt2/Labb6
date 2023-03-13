/*
 * Isak Ahlberg
 * Joline Hallberg
 * John Kågström
 * Oscar Bucht
 */
package specefika_klasser;

import java.util.ArrayList;
import java.util.Arrays;

import specefika_klasser.SkapaKund;

// FIFO kö som lagrar Kunder från SkapaKund.
public class FIFO {
    ArrayList<SkapaKund.Kund> content = new ArrayList<SkapaKund.Kund>();

    /**
     * 
     * @param kund
     */
    public void add(SkapaKund.Kund kund){
        content.add(kund);
    }

    // Deletear den nästa kunden som befinner sig först i kön.
    public SkapaKund.Kund next(){
        return content.remove(0);
    }

    // Returnerar en sträng som säger vad som står i kö.
    @Override
    public String toString() {
        int[] tmp = new int[content.size()];
        for(int i = 0; i < content.size(); i++){
            tmp[i] = (content.get(i).id);
        }
        return Arrays.toString(tmp);
    }
    
    // Checkar efter en nästa kund i kön.
    public Boolean hasNext(){
        if (content.size() != 0){
            return true;
        }
        else{
            return false;
        }
    }
    
    // Returnerar mängden kunder i kö.
    public int size() {
        return content.size();
    }
}
