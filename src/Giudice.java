import java.util.ArrayList;


public class Giudice extends Thread {
    static int numero;
    static ArrayList<Atleta> Atleti = new ArrayList<>();
    static ArrayList<Atleta> Podio = new ArrayList<>();

static GestioneFile IO = new GestioneFile();


    private Giudice() { }


    public static void aggiungiAtleta(Atleta a) {
        Atleti.add(a);
    }


    synchronized public static void finito(Atleta a) {
        System.out.println(a.nome + " a finito");
        System.out.println(Podio.size() + "|" + Atleti.size());
        Podio.add(a);
        if (Podio.size() == Atleti.size()) Giudice.GaraFinita();
    }


    public static void GaraFinita() {
        System.out.println("Gara Finita! Vediamo la classifica:");
        System.out.println("Primo classificato, Grande!: " + Podio.get(0).nome);
        System.out.println("Secondo classificato, Ritenta e sarai più fortunato!: " + Podio.get(1).nome);
        System.out.println("Terzo classificato, Cambia sport!!!!: " + Podio.get(2).nome);
    }


    public static void partenzaGara() {
       IO.ELENCO_ATLETICA();
        for (int i = 3; i > 0; i--) {
            System.out.println("Partenza in " + i);
            try { Thread.currentThread().sleep(1000); }
            catch (InterruptedException e) { System.err.println("Errore sleep"); }

        }


        System.out.println("VIAAAAA!!!");


        for (Atleta a : Atleti) {
            (new Thread(a)).start();

        }
    }
}
