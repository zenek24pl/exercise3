package wdsr.exercise3;

import wdsr.exercise3.record.RecordApplication;

public class Main {
	public static void main(String[] args) {
		MyServer server = new MyServer();
		server.deploy(RecordApplication.class, "Records inventory", "/", "/");
	}
}
