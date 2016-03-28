package org.by.issoft.paramCollector.sockets;

import java.net.*;
import org.by.issoft.paramCollector.params.ParamValueAbstract;

import java.io.*;

public class Client {

	private static final int PORT = 8888;
	private InetAddress IP;
	private String HOST;

	public Client(String HOST) {

		this.HOST = HOST;
		setIP(HOST);

	}

	public synchronized ParamValueAbstract<?> paramRequest(String paramName) {

		ParamValueAbstract<?> value = null;

		try (Socket socket = new Socket(IP, PORT);
				InputStream sin = socket.getInputStream();
				OutputStream sout = socket.getOutputStream();
				DataOutputStream toServer = new DataOutputStream(socket.getOutputStream());
				ObjectInputStream fromServer = new ObjectInputStream(socket.getInputStream());) {

			System.out.println("CLIENT: Sending new param request to server...");
			System.out.println();

			toServer.writeUTF(paramName);
			toServer.flush();
			value = (ParamValueAbstract<?>) fromServer.readObject();

			System.out.println("CLIENT: The server sent back value: " + value);

		} catch (Exception x) {
			x.printStackTrace();
		}
		return value;
	}

	private void setIP(String hostName) {
		try {
			IP = InetAddress.getByName(hostName);
		} catch (UnknownHostException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public String getHOST() {
		return HOST;
	}

}
