package proyectoSyPHundirLaFlota.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import proyectoSyPHundirLaFlota.servidor.thread.ClienteHilo;

public class Principal {
	private static final int PORT = 12345;
	private static ArrayList<ClienteHilo> clients = new ArrayList<>();

	public static void main(String[] args) {
		try {
			System.out.println("Servidor");
			ServerSocket serverSocket = new ServerSocket(PORT);

			while (true) {
				Socket clientSocket = serverSocket.accept();
				System.out.println("New client connected: " + clientSocket);

				ClienteHilo cHilo = new ClienteHilo(clientSocket);
				clients.add(cHilo);
				new Thread(cHilo).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}