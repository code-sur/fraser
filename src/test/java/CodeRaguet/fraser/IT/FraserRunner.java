package CodeRaguet.fraser.IT;


import CodeRaguet.fraser.Frase;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Properties;

import static org.assertj.core.api.Assertions.*;


class FraserRunner {

    private String stdout;
    private final ProcessBuilder processBuilder = new ProcessBuilder("java", "-jar", "target/fraser.jar");
    private final Map<String, String> env = processBuilder.environment();

    void run() throws IOException, InterruptedException {
        processBuilder.redirectErrorStream(true);
        Process p = processBuilder.start();
        p.waitFor();

        stdout = IOUtils.toString(p.getInputStream(), StandardCharsets.UTF_8);
    }

    void posterShows(Frase frase) {
        assertThat(stdout).contains(frase.toString());
    }

    void with(Properties env) {
        env.forEach((name, value) -> this.env.put(name.toString(), value.toString()));
    }

}
