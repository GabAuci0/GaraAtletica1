public class Main {
    public static void main(String[] args) {
        System.out.println("Gara di Atletica");
        Atleta a1 = new Atleta(67, "Gabriele");
        Atleta a2 = new Atleta(68, "Alessandro");
        Thread ta2 = new Thread(a2);
        ta2.setPriority(10);
        Thread ta1 = new Thread(a1);


        for(int i = 3; i > 0; --i) {
            System.out.println("Inizio gara in:" + i);
        }


        ta1.start();


        try {
            ta1.join();
        } catch (InterruptedException var6) {
            System.err.println("Errore join");
        }


        ta2.start();
    }
}
