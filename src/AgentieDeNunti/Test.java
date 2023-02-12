package AgentieDeNunti;

import java.io.File;

/**
 * The class that runs the project.
 */
public class Test {
    public static void main(String[] args) {
        File dataBaseFile = new File("AgentieDeNunti.db");
        if (dataBaseFile.exists()) {
            dataBaseFile.delete();
        }
        new Start();
    }
}
