import java.net.*;
import java.io.*;

public class ServerThread extends Thread {

    private Socket socket;

    public ServerThread(Socket socket) {
        super("ServerThread");
        this.socket = socket;
    }

    public void run() {
        try  {
            System.out.println("Client connected: "+socket.getRemoteSocketAddress().toString());
            ServerProtocol serverProtocol = new ServerProtocol(socket);
            //Set the server status to waiting
            serverProtocol.processInput(null);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());//Set up output stream
            out.writeInt(serverProtocol.getState());
            out.flush();
            do {
                DataInputStream in = new DataInputStream(socket.getInputStream());//Set up input stream
                String fromClient = in.readUTF();//Read response from client
                serverProtocol.processInput(fromClient);//Interpret the data from the client
            }while (serverProtocol.getState() != -1);//Loop whilst the client is connected
            socket.close();//Close the socket
        } catch (IOException e) {
            System.out.println("Lost connection to client...");
        }
    }

}
