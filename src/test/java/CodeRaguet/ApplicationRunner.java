package CodeRaguet;


import java.io.*;

import static org.junit.Assert.assertEquals;

public class ApplicationRunner {

    private String stdout;

    public void findFrase() throws IOException, InterruptedException {
        ProcessBuilder pb = new ProcessBuilder("bash", "fraser.sh");
        pb.redirectErrorStream(true);
        Process p = pb.start();
        p.waitFor();

        InputStreamReader inputStreamReader = new InputStreamReader(p.getInputStream());
        BufferedReader br = new BufferedReader(inputStreamReader);
        stdout = br.readLine();
    }

    public void showsFrase(String frase) {
        assertEquals(frase, stdout);
    }
}
