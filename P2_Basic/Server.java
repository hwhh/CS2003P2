import java.net.*;
import java.io.*;

public class Server {

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {//Make sure there is only one program argument
            System.err.println("<port number>");
            System.exit(1);
        }
        int portNumber = Integer.parseInt(args[0]);//Get the port number
        boolean listening = true;

        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {//Attempt to create a new server socket on the given port
            while (listening) {
                new ServerThread(serverSocket.accept()).start();//Start an instance of the server in a new thread
            }
        } catch (Exception e) {
            System.err.println("Could not listen on port " + portNumber);
            System.exit(-1);
        }
    }
}
