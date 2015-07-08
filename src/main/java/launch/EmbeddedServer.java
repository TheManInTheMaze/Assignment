package launch;

import config.AppConfiguration;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.loader.WebappLoader;
import org.apache.catalina.startup.Tomcat;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import java.io.File;
import java.io.IOException;

/**
 * Created by pcorentin on 08/07/2015.
 */
public class EmbeddedServer implements Runnable {

    private Tomcat tomcat;
    private Context context;
    private Thread serverThread;

    public EmbeddedServer() throws IOException {
        final File base = createBaseDirectory();
        tomcat = new Tomcat();


        String webPort = System.getenv("PORT");
        if (webPort == null || webPort.isEmpty()) {
            webPort = "8080";
        }

        tomcat.setPort(Integer.valueOf(webPort));

        context = tomcat.addContext("/", base.getAbsolutePath());
        Tomcat.addServlet(context, "CXFServlet", new CXFServlet());

        context.addServletMapping("/game/*", "CXFServlet");
        context.addApplicationListener(ContextLoaderListener.class.getName());
        context.setLoader(new WebappLoader(Thread.currentThread().getContextClassLoader()));
        context.addParameter("contextClass", AnnotationConfigWebApplicationContext.class.getName());
        context.addParameter("contextConfigLocation", AppConfiguration.class.getName());

        serverThread = new Thread(this);


    }

    public void start() {
        serverThread.start();
    }


    public void run() {
        try {
            tomcat.start();
        } catch (LifecycleException e) {
            throw new RuntimeException(e);
        }
        tomcat.getServer().await();
    }

    public void stop() {
        try {
            tomcat.stop();
            tomcat.destroy();
        } catch (LifecycleException e) {
            throw new RuntimeException(e);
        }
    }

    private File createBaseDirectory() throws IOException {
        final File base = File.createTempFile("tmp-", "");

        if (!base.delete()) {
            throw new IOException("Cannot (re)create base folder: " + base.getAbsolutePath());
        }

        if (!base.mkdir()) {
            throw new IOException("Cannot create base folder: " + base.getAbsolutePath());
        }

        return base;
    }

}
