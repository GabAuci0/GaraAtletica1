import java.io.*;

public class GestioneFile {
    BufferedReader br;
    BufferedReader wr;

    public void ELENCO_ATLETICA() {
        try {
            FileReader file = new FileReader("ELENCO_ATLETI.txt");
            br = new BufferedReader(file);
            int num = 0;
            String line = "";
            while ((line = br.readLine()) != null) {
                new Atleta (num++, line);

            }

        } catch (IOException e){
            System.out.println("Errore nel file");
        }
    }

}

