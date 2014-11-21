package com.zanclus.example;

import com.beust.jcommander.JCommander;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.spi.container.servlet.ServletContainer;
import com.zanclus.example.entities.Configuration;
import java.lang.annotation.Annotation;
import java.net.InetSocketAddress;
import javax.enterprise.inject.spi.BeanManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.deltaspike.cdise.api.CdiContainer;
import org.apache.deltaspike.cdise.api.CdiContainerLoader;
import org.apache.deltaspike.cdise.api.ContextControl;
import org.apache.deltaspike.cdise.servlet.CdiServletRequestListener;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

@Slf4j
public class Main {
    /**
     * @param args the command line arguments
     * @throws Exception in case of an error.
     */
    public static void main(String[] args) throws Exception {
        Configuration config = new Configuration();
        JCommander argParser = new JCommander();
        argParser.setProgramName("JettyJerseyWeldExample");
        argParser.addObject(config);
        try {
            argParser.parse(args);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            argParser.usage();
        }
        LOG.debug("Args Config:\n\n"+config.toString()+"\n\n");
        
        CdiContainer cdiContainer = CdiContainerLoader.getCdiContainer();
        cdiContainer.boot();
        ContextControl ctxCtrl = cdiContainer.getContextControl();
        ctxCtrl.startContexts();

        BeanManager bm = cdiContainer.getBeanManager();
        bm.fireEvent(config, new Annotation[] {});
        
        Server server = new Server(new InetSocketAddress(config.listen(), config.port()));
        ServletContextHandler handler = new ServletContextHandler(null, "/");
        handler.addEventListener(new CdiServletRequestListener());
        ServletContainer jerseyContainer = new ServletContainer(new PackagesResourceConfig("com.zanclus.example.api"));
        ServletHolder jersey = new ServletHolder(jerseyContainer);
        handler.addServlet(jersey, "/*");
        server.setHandler(handler);
        
        server.start();
        bm.fireEvent(server, new Annotation[] {});
        server.join();
    }
}