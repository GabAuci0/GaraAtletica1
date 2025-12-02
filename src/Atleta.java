import java.util.Random;

public class Atleta implements Runnable {
    int numero;
    String nome;
    double tempo = 0;
    double metri = 0;
    final double LUNGHEZZAGARA = 50.0;

    public Atleta(int pNumero, String pNome) {
        numero = pNumero;
        nome = pNome;
        Giudice.aggiungiAtleta(this);
    }

    @Override
    public void run() {
        long inizio = System.currentTimeMillis();
        Random rnd = new Random();

        while (metri <= LUNGHEZZAGARA) {


            int evento = rnd.nextInt(100); // 0 - 99

            if (evento < 5) {
                // 5% - SCIVOLATA
                System.out.println(nome + " è SCIVOLATO e perde tempo!");
                sleep(1500);
            } else if (evento < 10) {
                // 5% - CRAMPI / RALLENTAMENTO
                System.out.println(nome + " ha un crampo! Avanza pochissimo...");
                metri += rnd.nextDouble(1);
                sleep(1000);
            } else if (evento < 13) {
                // 3% - SUPER SPRINT
                System.out.println(nome + " fa un SUPER SPRINT!!");
                metri += rnd.nextDouble(20) + 5;
            } else if (evento < 18) {
                // 5% - DISTRAZIONE
                System.out.println(nome + " si distrae e rallenta...");
                sleep(2000);
            } else {

                metri += rnd.nextDouble(10);
            }

            System.out.println(nome + " Metri percorsi: " + metri);
            sleep(1000);
        }

        long fine = System.currentTimeMillis();
        tempo = (fine - inizio) / 1000.0;

        Giudice.finito(this);
    }

    private void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            System.err.println("Errore sleep");
        }
    }
}
