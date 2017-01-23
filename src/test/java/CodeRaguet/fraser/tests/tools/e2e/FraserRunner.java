package CodeRaguet.fraser.tests.tools.e2e;


import java.io.IOException;
import java.util.Map;
import java.util.Properties;


public class FraserRunner {

    private final ProcessBuilder processBuilder = new ProcessBuilder("java", "-jar", "target/fraser.jar");
    private final Map<String, String> env = processBuilder.environment();

    public FraserRunner(Properties env) {
        env.forEach((name, value) -> this.env.put(name.toString(), value.toString()));
    }

    public void run() {
        try {
            processBuilder.redirectErrorStream(true);
            Process p = processBuilder.start();
            p.waitFor();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
