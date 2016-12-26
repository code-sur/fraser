package CodeRaguet.fraser.IT;


import java.io.*;
import java.util.Map;
import java.util.Properties;

import static org.assertj.core.api.Assertions.*;


class FraserRunner {

    private String stdout;
    private final ProcessBuilder processBuilder = new ProcessBuilder("bash", "fraser.sh");
    private final Map<String, String> env = processBuilder.environment();

    void run() throws IOException, InterruptedException {
        processBuilder.redirectErrorStream(true);
        Process p = processBuilder.start();
        p.waitFor();

        InputStreamReader inputStreamReader = new InputStreamReader(p.getInputStream());
        BufferedReader br = new BufferedReader(inputStreamReader);
        stdout = br.readLine();
    }

    void shows(String label) {
        assertThat(stdout).isEqualTo(label);
    }

    void with(Properties env) {
        env.forEach((name, value) -> this.env.put(name.toString(), value.toString()));
    }

}
