package org.by.issoft.paramCollector.sockets;

import java.net.*;

import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class Server extends Thread {

	private static final int PORT = 8888;

	@Override
	public void run() {

		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(PORT);
			System.out.println("-----------SERVER STARTED------------");
			while (true) {
				try {
					Socket clientSocket = serverSocket.accept();
					new Thread(new ServerThread(clientSocket)).start();
				} catch (IOException e) {

				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
