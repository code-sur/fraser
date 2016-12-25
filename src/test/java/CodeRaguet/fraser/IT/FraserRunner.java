package CodeRaguet.fraser.IT;


import CodeRaguet.fraser.ENV;

import java.io.*;
import java.util.Map;
import java.util.Properties;

import static org.assertj.core.api.Assertions.*;


class FraserRunner {

    private String stdout;
    private final ProcessBuilder pb = new ProcessBuilder("bash", "fraser.sh");
    private final Map<String, String> env = pb.environment();

    private String runFraser() throws IOException, InterruptedException {
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

    void setRefreshToken(String refreshToken) {
        env.put(ENV.REFRESH_TOKEN.name(), refreshToken);
    }

    void setClientSecret(String clientSecret) {
        env.put(ENV.CLIENT_SECRET.name(), clientSecret);
    }

}
