/*
 * Isak Ahlberg
 * Joline Hallberg
 * John Kågström
 * Oscar Bucht
 */
package specefika_klasser;

public class SkapaKund {
    private int nextId;

    public class Kund {
        public final int id;

        /**
         *
         * @param id
         */
        private Kund(int id){
            this.id = id;
        }
    }

    public SkapaKund(){
        nextId = 0;
    }

    public Kund getKund(){
        Kund kund = new Kund(nextId);
        nextId++;
        return kund;
    }
}
