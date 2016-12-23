package CodeRaguet.fraser.IT;


import java.io.*;

import static org.assertj.core.api.Assertions.*;


class FraserRunner {

    private String stdout;

    private String runFraser() throws IOException, InterruptedException {
        ProcessBuilder pb = new ProcessBuilder("bash", "fraser.sh");
        pb.redirectErrorStream(true);
        Process p = pb.start();
        p.waitFor();

        InputStreamReader inputStreamReader = new InputStreamReader(p.getInputStream());
        BufferedReader br = new BufferedReader(inputStreamReader);
        return br.readLine();
    }

    void run() throws IOException, InterruptedException {
        stdout = runFraser();
    }

    void shows(String label) {
        assertThat(stdout).isEqualTo(label);
    }
}
