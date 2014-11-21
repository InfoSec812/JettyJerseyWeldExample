package com.zanclus.example;

import com.beust.jcommander.JCommander;
import com.zanclus.example.entities.Configuration;
import java.net.InetSocketAddress;
import java.util.List;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.jboss.resteasy.plugins.server.servlet.HttpServlet30Dispatcher;
import org.jboss.resteasy.plugins.server.servlet.ResteasyBootstrap;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.jboss.weld.environment.se.bindings.Parameters;
import org.jboss.weld.environment.servlet.BeanManagerResourceBindingListener;
import org.jboss.weld.environment.servlet.Listener;

@Slf4j
@Singleton
public class Main {

    /**
     * @param args
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

        Weld weld = new Weld();
        WeldContainer container = weld.initialize();

        Server server = new Server(new InetSocketAddress(config.listen(), config.port()));
        ServletContextHandler handler = new ServletContextHandler(null, "/");
        handler.setInitParameter("javax.ws.rs.Application", "com.zanclus.example.jaxrs.WeldApplication");
        handler.setInitParameter("resteasy.injector.factory", "org.jboss.resteasy.cdi.CdiInjectorFactory");
        handler.addEventListener(new ResteasyBootstrap());
        handler.addEventListener(new BeanManagerResourceBindingListener());
        handler.addEventListener(new Listener());
        ServletHolder restEasyHolder = new ServletHolder(HttpServlet30Dispatcher.class);
        restEasyHolder.setInitOrder(1);
        handler.addServlet(restEasyHolder, "/*");
        server.setHandler(handler);
        
        server.start();
        server.join();
    }
}