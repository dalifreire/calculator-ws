package com.upwork.exercise.calculatorws;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.net.httpserver.HttpServer;

/**
 * Embedded HTTP server.
 * 
 * @author Dali Freire - dalifreire@gmail.com
 * @since 1.0
 */
public class JerseyEmbeddedHTTPServer {

    private final String hostname;
    private final int port;
    private final HttpServer httpServer;

    /**
     * Constructor.
     * 
     * @param port The port number to the embedded http server
     * @throws IOException
     */
    public JerseyEmbeddedHTTPServer(int port) throws IOException {
        this.hostname = hostName();
        this.port = port;
        this.httpServer = httpServer();
    }

    /**
     * Starts the embedded http server.
     * 
     * @throws IOException
     */
    public void start() throws IOException {
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Starting Embedded Jersey HTTPServer...");

        this.httpServer.start();

        Logger.getLogger(this.getClass().getName()).log(Level.INFO, String
            .format("\nJersey Application Server started with WADL available at %sapplication.wadl\n", serverUri()));
    }

    private HttpServer httpServer() throws IOException {
        final ResourceConfig resourceConfig = new PackagesResourceConfig(CalculatorRestService.class.getPackage().getName());
        final URI serverUri = serverUri();
        return HttpServerFactory.create(serverUri, resourceConfig);
    }

    private URI serverUri() {
        return UriBuilder.fromUri(String.format("http://%s/", this.hostname)).port(this.port).build();
    }

    private String hostName() {
        try {
            return InetAddress.getLocalHost().getCanonicalHostName();
        } catch (UnknownHostException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Cannot get the canonical hostname.", e);
            return "localhost";
        }
    }

    public static void main(String[] args) throws IOException {
        new JerseyEmbeddedHTTPServer(9090).start();
    }

}
