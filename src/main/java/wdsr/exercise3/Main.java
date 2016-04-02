package wdsr.exercise3;

import wdsr.exercise3.record.RecordApplication;

public class Main {
	public static final String SERVER_HOST = "localhost";
	public static final int SERVER_PORT = 8090;
	
	public static void main(String[] args) {
		MyServer server = new MyServer(SERVER_HOST, SERVER_PORT);
		server.deploy(RecordApplication.class, "Records inventory", "/", "/");
	}
}
