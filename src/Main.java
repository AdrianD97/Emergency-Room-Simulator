import com.fasterxml.jackson.databind.ObjectMapper;
import reader.Reader;

import java.io.File;

public final class Main {
    private Main() {
    }

    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        Reader reader = null;
        try {
            reader = objectMapper.readValue(new File(args[0]), Reader.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Simulator.getSimulator().setReader(reader);
        Simulator.getSimulator().init();
        Simulator.getSimulator().simulate();
    }
}
