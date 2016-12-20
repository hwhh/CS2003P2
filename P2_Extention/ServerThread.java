import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread {

    private Socket socket;
    private ServerProtocol serverProtocol;

    public ServerThread(Socket socket) {
        //Create the new server
        super("ServerThread");
        this.socket = socket;
        serverProtocol = new ServerProtocol(socket);
    }

    public void run() {
        try (//Automatically close the resources when done
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            System.out.println("Client connected: "+socket.getRemoteSocketAddress().toString());
            String inputLine;
            int response;
            //Set the server status to waiting
            response = serverProtocol.processClientResponse(null);
            out.write(response);
            out.flush();
            while ((inputLine = in.readLine()) != null) {//Loop whilst the client is connected
                response = serverProtocol.processClientResponse(inputLine);//Interpret the data from the client
                out.write(response);//Send the response to the client
                out.flush();
                if (response == -1)//Check if the client is still connected
                    break;
                else if (response ==2){//Check if a file has been found
                    serverProtocol.writeFile();
                }
            }
            socket.close();
        } catch (Exception e) {
            System.out.println("Lost connection to client...");
        }
    }
    
}
