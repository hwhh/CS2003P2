import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerProtocol {

    private static final int WAITING = 0;
    private static final int FILE_FOUND = 2;
    private static final int FILE_NOT_FOUND = 3;
    private static final int FINISHED = -1;

    private String currentFile;
    private Socket socket;

    public ServerProtocol(Socket socket) {
        this.socket = socket;
    }

    //Interpret response sent from client
    public int processClientResponse(String inFromClient) throws IOException {
        int response;
        if (inFromClient == null) {//If the input is null the server is waiting for data from client
           response = WAITING;
        } else if (fileFound(inFromClient)) {//Check if the file requested by the client exists on the server
            currentFile = inFromClient;
            response = FILE_FOUND;
        }else if(inFromClient.equals("exit")) {//Check if the user has entered "exit"
            response = FINISHED;
        }else {//Else input from the user is invalid
            response = FILE_NOT_FOUND;
        }
        return response;
    }

    public boolean fileFound(String fileName){
        return new File(fileName).exists();//Check if a file exists in current directory
    }

    public void writeFile() throws IOException {
        File file = new File(currentFile);//Get the file requested by the user

        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);//Send the file length and name to client
        out.println(file.length());
        out.println(currentFile);
        out.flush();
        try(//Automatically close the resources when done
            ServerSocket ss = new ServerSocket(3434);//Create new socket to send the file down
            Socket socket = ss.accept();
            InputStream fileIn = new FileInputStream(currentFile);//Set up file reader
            OutputStream fileOut = socket.getOutputStream();
        ) {
            byte[] buf = new byte[8192];
            int len = 0;
            while ((len = fileIn.read(buf)) != -1) {//Read in the file
                fileOut.write(buf, 0, len);//Write the bytes of the file to the client
            }
        }
    }

}
