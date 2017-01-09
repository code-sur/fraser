package CodeRaguet.fraser.tests.tools;


import java.io.IOException;
import java.util.Map;
import java.util.Properties;


public class FraserRunner {

    private final ProcessBuilder processBuilder = new ProcessBuilder("java", "-jar", "target/fraser.jar");
    private final Map<String, String> env = processBuilder.environment();

    public void run() throws IOException, InterruptedException {
        processBuilder.redirectErrorStream(true);
        Process p = processBuilder.start();
        p.waitFor();
    }

    public void with(Properties env) {
        env.forEach((name, value) -> this.env.put(name.toString(), value.toString()));
    }

}
