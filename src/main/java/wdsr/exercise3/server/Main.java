package wdsr.exercise3.server;

import org.jboss.resteasy.plugins.server.undertow.UndertowJaxrsServer;

import io.undertow.Undertow;

public class Main {
    public static final String SERVER_HOST = "localhost";
    public static final int SERVER_PORT = 8090;

    public static void main( String[] args ) {
        final UndertowJaxrsServer server = new UndertowJaxrsServer().start( Undertow.builder().addHttpListener( SERVER_PORT, SERVER_HOST ) );
        server.deploy( new ServerApplication() );

        Runtime.getRuntime().addShutdownHook( new Thread() {
            public void run() {
                server.stop();
            }
        } );
    }
}
