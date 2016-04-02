package wdsr.exercise3;

import javax.ws.rs.core.Application;

import org.jboss.resteasy.cdi.CdiInjectorFactory;
import org.jboss.resteasy.plugins.server.undertow.UndertowJaxrsServer;
import org.jboss.resteasy.spi.ResteasyDeployment;

import io.undertow.Undertow;
import io.undertow.servlet.Servlets;
import io.undertow.servlet.api.DeploymentInfo;

public class MyServer {
	public static final String SERVER_HOST = "localhost";
	public static final int SERVER_PORT = 8090;
	
	private UndertowJaxrsServer server;
	
	public MyServer() {
		server = new UndertowJaxrsServer().start(Undertow.builder().addHttpListener(SERVER_PORT, SERVER_HOST));	
	}
	
	public void deploy(Class<? extends Application> clazz, String deploymentName, String mapping, String contextPath) {
		ResteasyDeployment deployment = new ResteasyDeployment();
        deployment.setInjectorFactoryClass(CdiInjectorFactory.class.getName());
        deployment.setApplicationClass(clazz.getName());
        DeploymentInfo di = server.undertowDeployment(deployment, mapping)
        		.setClassLoader(this.getClass().getClassLoader())
        		.setContextPath(contextPath)
                .setDeploymentName(deploymentName)
                .addListeners(Servlets.listener(org.jboss.weld.environment.servlet.Listener.class));
        server.deploy(di);		
	}
	
	public void stop() {
		server.stop();
	}
}
