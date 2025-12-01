import java.io.*;
import java.util.List;

public class GestioneFile {

    public void ELENCO_ATLETICA() {
        try (BufferedReader br = new BufferedReader(new FileReader("ELENCO_ATLETI.txt"))) {
            int num = 0;
            String line;
            while ((line = br.readLine()) != null) {
                new Atleta(num++, line);
            }
        } catch (IOException e) {
            System.out.println("Errore nel file: " + e.getMessage());
        }
    }

    public void salvaPodioSuFile(List<Atleta> podio, String percorso) {
        File destinazione = new File(percorso);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(destinazione))) {

            int indice = 1;
            for (Atleta atleta : podio) {
                String riga = String.format(
                        "%d) Numero: %d - %s | Tempo = %.2f s",
                        indice,
                        atleta.numero,
                        atleta.nome,
                        atleta.tempo
                );
                bw.write(riga);
                bw.newLine();
                indice++;
            }

            System.out.println("File podio generato correttamente: " + percorso);

        } catch (IOException ex) {
            System.err.println("Impossibile salvare il podio: " + ex.getMessage());
        }
    }
}
