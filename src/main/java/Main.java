import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * @author Crunchify.com
 */

public class Main {
    private static int extractPID(String psOutput) {
        for (String current : psOutput.split(" ")) {
            try {
                return Integer.parseInt(current);
            } catch (NumberFormatException ignore) {

            }
        }
        throw new RuntimeException("Can't fine pid.");
    }

    private static void killPanGpHip() {
        try {
            String process;
            int pid;
            Process p = Runtime.getRuntime().exec("ps -ax");
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((process = input.readLine()) != null) {
                if (process.contains("PanGpHip") && ! process.contains("PanGpHipMap")) {
                    pid = extractPID(process);
                    System.out.println(process); // <-- Print all Process here line
                    Process killCommand = Runtime.getRuntime().exec("sudo -S kill -9 " + pid);
                    Writer toSudo = new OutputStreamWriter(killCommand.getOutputStream());
                    String password = "";

                    toSudo.write(password);
                    toSudo.write('\n');  // sudo's docs demand a newline after the password
                    toSudo.flush();
                    toSudo.close();      // but closing the stream might be sufficient
                }
            }
            input.close();
        } catch (Exception err) {
            err.printStackTrace();
        }
    }

    public static void main(String[] args) {
        killPanGpHip();
    }
}

