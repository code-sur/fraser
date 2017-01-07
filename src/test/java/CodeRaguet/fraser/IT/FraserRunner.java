package CodeRaguet.fraser.IT;


import java.io.IOException;
import java.util.Map;
import java.util.Properties;


class FraserRunner {

    private final ProcessBuilder processBuilder = new ProcessBuilder("java", "-jar", "target/fraser.jar");
    private final Map<String, String> env = processBuilder.environment();

    void run() throws IOException, InterruptedException {
        processBuilder.redirectErrorStream(true);
        Process p = processBuilder.start();
        p.waitFor();
    }

    void with(Properties env) {
        env.forEach((name, value) -> this.env.put(name.toString(), value.toString()));
    }

}
