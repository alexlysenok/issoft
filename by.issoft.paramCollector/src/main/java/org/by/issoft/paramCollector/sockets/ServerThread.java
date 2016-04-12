package org.by.issoft.paramCollector.sockets;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.by.issoft.paramCollector.params.ParamValueAbstract;
import org.by.issoft.paramCollector.reflection.ObtainerRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import org.by.issoft.paramCollector.ParamObtainer;

public class ServerThread implements Runnable {

	Socket clientSocket = null;
	boolean threadIsDead = false;

	public ServerThread(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}

	public void run() {

		try {
			InputStream sin = clientSocket.getInputStream();
			OutputStream sout = clientSocket.getOutputStream();
			DataInputStream fromClient = new DataInputStream(sin);
			ObjectOutputStream toClient = new ObjectOutputStream(sout);
			String paramName = null;

			while (true) {

				try {

					paramName = fromClient.readUTF();

					System.out.println("SERVER: The client asked me for param: " + paramName);

					ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

					ObtainerRegistry registry = (ObtainerRegistry) context.getBean("obtainerRegistry");

					ParamObtainer<?> obtainer = registry.findObtainer(paramName);
					ParamValueAbstract<?> paramValue = (ParamValueAbstract<?>) obtainer.getCurrentParamValue();

					System.out.println("SERVER: I'm sending it back...");

					toClient.writeObject(paramValue);

					System.out.println("SERVER: Waiting for the next request...");
					System.out.println();

				} catch (IOException e) {
					// TODO Auto-generated catch block
					threadIsDead = true;
					clientSocket.close();

				}

			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
