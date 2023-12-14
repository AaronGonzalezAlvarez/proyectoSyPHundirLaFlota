package proyectoSyPHundirLaFlota.cliente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import proyectoSyPHundirLaFlota.servidor.models.Coordenada;

public class Cliente {
	
	private static final String SERVER_IP = "localhost";
	private static final int SERVER_PORT = 12345;

	public static void main(String[] args) {
		try {
			Socket socket = new Socket(SERVER_IP, SERVER_PORT);
			System.out.println("Connected to server: " + socket);

			ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

			// Escuchar mensajes del servidor en un hilo separado
			/*new Thread(() -> {
				try {
					while (true) {
						String serverMessage = (String) inputStream.readObject();
						System.out.println("Received from server: " + serverMessage);
					}
				} catch (IOException | ClassNotFoundException e) {
					e.printStackTrace();
				}
			}).start();*/

			// Enviar mensajes al servidor desde la consola
			Scanner scanner = new Scanner(System.in);
				System.out.print("Enviar mensaje a servidor: ");
				String message = scanner.nextLine();

				outputStream.writeObject(message);
				outputStream.flush();
				ArrayList<Coordenada> clientMessage = null;
				try {
					clientMessage = (ArrayList<Coordenada>) inputStream.readObject();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				while(true) {
					System.out.println("--------------------------------");
					System.out.print("Coordenada x: ");
					String ejeX = scanner.nextLine();
					System.out.print("Coordenada y: ");
					String ejeY = scanner.nextLine();
					
					int ejeXchar = ejeX.charAt(0);
					int ejeYchar = ejeY.charAt(0);
					outputStream.writeObject(new Coordenada(ejeXchar, ejeYchar));
					outputStream.flush();
					
				}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
