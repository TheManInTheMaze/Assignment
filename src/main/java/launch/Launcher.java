package launch;

/**
 * Created by pcorentin on 08/07/2015.
 */
public class Launcher {

    public static void main(String[] args) throws Exception {

        EmbeddedServer embeddedServer = new EmbeddedServer();
        embeddedServer.start();
        System.in.read();
        embeddedServer.stop();


    }
}
