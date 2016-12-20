import java.io.*;
import java.net.*;

public class Client {


    public static void main(String[] args) throws IOException {
        if (args.length != 2) {//Make sure there is only two program argument
            System.err.println("<host name> <port number>");
            System.exit(1);
        }
        String hostName = args[0];//Get the host name
        int portNumber = Integer.parseInt(args[1]);//Get the port number
        try (//Automatically close the resources when done
                Socket client = new Socket(hostName, portNumber)
        ) {
            ClientProtocol clientProtocol = new ClientProtocol(client);
            int status;
            do {
                DataInputStream in = new DataInputStream(client.getInputStream());
                status = in.readInt();//Read the response from the server
                status = clientProtocol.processInput(status);//Interpret response from the server
            }while (status != -1);//Check if the server has terminated

        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.out.println("Server not active...");
            System.exit(1);
        }
    }

}
