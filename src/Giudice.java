import java.util.ArrayList;
import java.util.Random;

/**
 * Classe che gestisce l'intera gara atletica:
 * - tiene traccia degli atleti
 * - registra l'ordine di arrivo
 * - dichiara la fine della gara
 * - salva il podio su file
 * - gestisce eventi anomali (es. caduta di un meteorite)
 */
public class Giudice extends Thread {

    static int numero;
    static ArrayList<Atleta> Atleti = new ArrayList<>();
    static ArrayList<Atleta> Podio = new ArrayList<>();

    static GestioneFile IO = new GestioneFile();

    private Giudice() { }

    /**
     * Aggiunge un atleta alla lista dei partecipanti.
     * @param a atleta da aggiungere
     */
    public static void aggiungiAtleta(Atleta a) {
        Atleti.add(a);
    }

    /**
     * Segnala che un atleta ha terminato la gara.
     * Aggiorna il podio e verifica se sono arrivati tutti.
     *
     * @param a atleta che ha finito la gara
     */
    synchronized public static void finito(Atleta a) {
        System.out.println(a.nome + " ha finito");
        Podio.add(a);
        System.out.println(Podio.size() + " | " + Atleti.size());
        if (Podio.size() == Atleti.size()) Giudice.GaraFinita();
    }

    /**
     * Mostra il podio finale e salva il risultato nel file PODIO.txt.
     */
    public static void GaraFinita() {
        System.out.println("Gara Finita! Vediamo la classifica:");
        System.out.println("Primo classificato, Grande!: " + Podio.get(0).nome);
        System.out.println("Secondo classificato, Ritenta e sarai più fortunato!: " + Podio.get(1).nome);
        System.out.println("Terzo classificato, Cambia sport!!!!: " + Podio.get(2).nome);

        IO.salvaPodioSuFile(Podio, "PODIO.txt");
    }

    /**
     * Avvia la gara:
     * - carica gli atleti
     * - effettua il countdown
     * - scatena un evento casuale (meteorite)
     * - fa partire i thread degli atleti
     */
    public static void partenzaGara() {

        IO.ELENCO_ATLETICA();

        eventoMeteorite();

        for (int i = 3; i > 0; i--) {
            System.out.println("Partenza in " + i);
            try { Thread.sleep(1000); }
            catch (InterruptedException e) { System.err.println("Errore sleep"); }
        }

        System.out.println("VIAAAAA!!!");

        for (Atleta a : Atleti) {
            (new Thread(a)).start();
        }
    }

    /**
     * Evento anomalo che può verificarsi prima dell'inizio della gara.
     * Con una probabilità del 10% cade un meteorite e metà degli atleti muore.
     * I superstiti continuano la gara normalmente.
     */
    private static void eventoMeteorite() {
        Random r = new Random();

        if (r.nextInt(10) == 0) {
            System.out.println(" EVENTO ANOMALO: UN METEORITE CADE SUL CAMPO GARA!!!");
            System.out.println(" BOOOOM!!! metà degli atleti non sopravvive...");

            int numeroTotale = Atleti.size();
            int daEliminare = numeroTotale / 2;

            for (int i = 0; i < daEliminare; i++) {
                int index = r.nextInt(Atleti.size());
                Atleta morto = Atleti.get(index);

                System.out.println( morto.nome + " è stato eliminato dall’impatto.");
                Atleti.remove(index);
            }

            System.out.println("Sopravvissuti: " + Atleti.size() + "/" + numeroTotale);
            System.out.println("La gara procede con i superstiti...\n");
        }
    }
}
