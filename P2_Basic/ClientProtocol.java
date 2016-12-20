import java.io.*;
import java.net.Socket;
import java.util.List;

public class ClientProtocol {

    private static final int WAITING = 0;
    private static final int REQUEST_SENT =1;
    private static final int FILE_FOUND = 2;
    private static final int FILE_NOT_FOUND = 3;
    private static final int FINISHED = -1;
    private Socket socket;

    public ClientProtocol(Socket socket) {
        this.socket = socket;
    }

    //Interpret the response from the server
    public int processInput(int status) throws IOException {
        if (status == WAITING) {//If the server is waiting for input from the client
            return sendRequest();//Prompt the user to input a filename
        }
        else if (status == FILE_FOUND) {//If the server finds the searched for file
            String fromServer;
            DataInputStream in = new DataInputStream(socket.getInputStream());//Get the response from the sever
            System.out.println("Response from server:");
            while (!(fromServer = in.readUTF()).equals("EOF")) {//Read in next line returned from server if the response is "EOF" break

                System.out.println(fromServer);
            }
            return sendRequest();//Prompt the user to input a filename
        }
        else if (status == FILE_NOT_FOUND) {//If the server doesnt find the searched for file
            System.out.print("File not found. ");
            return sendRequest();//Prompt the user to input a filename
        }
        else if (status == FINISHED){//If the server has terminated
            System.out.println("Finished");
            return FINISHED;
        }
        return 0;
    }

    public int sendRequest() throws IOException {
        System.out.print("Please enter a file name or type exit: ");
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));//Get input from user
        String fromUser;
        while((fromUser = stdIn.readLine()) == null){//Read in input from console
            fromUser = stdIn.readLine();
        }
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());//Send the input from the user to the client
        out.writeUTF(fromUser);
        out.flush();
        return REQUEST_SENT;
    }

}
