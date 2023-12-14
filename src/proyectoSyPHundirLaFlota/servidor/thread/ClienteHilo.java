package proyectoSyPHundirLaFlota.servidor.thread;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import proyectoSyPHundirLaFlota.servidor.logicaHlF.LogicaHundirLaFlota;
import proyectoSyPHundirLaFlota.servidor.models.Coordenada;

public class ClienteHilo implements Runnable {
    private Socket clientSocket;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    public ClienteHilo(Socket socket) {
        try {
            clientSocket = socket;
            outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            inputStream = new ObjectInputStream(clientSocket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (clientSocket.isConnected()) {
                // Esperar y recibir mensajes del cliente
                String clientMessage = (String) inputStream.readObject();
                System.out.println("Conexión con cliente " + clientMessage);

                //jugar partida
				boolean salir = false;
				boolean colocar = false;
				LogicaHundirLaFlota tableroServidor = null;
				LogicaHundirLaFlota tableroCliente = null;
				while (!salir) {
					if (!colocar) {
						// tablero servidor
						tableroServidor = new LogicaHundirLaFlota();
						tableroServidor.addBarcoAleatorios();
						//tableroServidor.imprimirTablero();

						// tablero cliente
						tableroCliente = new LogicaHundirLaFlota();
						tableroCliente.addBarcoAleatorios();
						//tableroCliente.imprimirTablero();
						// prueba de envio a cliente:
						outputStream.writeObject(tableroCliente.enviarTablero());
						outputStream.flush();
						colocar = true;
					}
					
					//a al espera de respuesta del cliente
	                Coordenada coordenadaDesdeCliente = (Coordenada) inputStream.readObject();
	                int ejeXenviado = Character.getNumericValue(coordenadaDesdeCliente.getEjeX());
	                int ejeyenviado = Character.getNumericValue(coordenadaDesdeCliente.getEjeY());
	                System.out.println("llegara la coordenada?");
				}
                
                // Enviar mensaje a todos los clientes conectados
                //sendMessageToAllClients(clientMessage);
                //Cerrar conexiones cuando el cliente se desconecta
                //clients.remove(this);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    private void sendMessageToAllClients(String message) {
        /*for (ClientHandler client : clients) {
            try {
                client.outputStream.writeObject(message);
                client.outputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
    }
}