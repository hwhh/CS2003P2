import java.io.*;
import java.net.Socket;

public class ClientProtocol {

    private static final int WAITING = 0;
    private static final int FILE_FOUND = 2;
    private static final int FILE_NOT_FOUND = 3;
    private static final int FINISHED = -1;

    private GUI gui;
    private Socket socket;
    private double downloaded; // number of bytes downloaded
    private double size; // current status of download

    public ClientProtocol(Socket socket, GUI gui) {
        this.socket = socket;
        this.gui = gui;
    }

    //Calculate the amount of the file has been downloaded
    public double getProgress() {
        double x = (downloaded / size) * 100;
        return x;
    }

    //Interpret the response from the server
    public void processInput(int status) throws IOException {
        if (status == WAITING) {//If the server is waiting for input from the client
            gui.getjTextAreaOutput().setText("SERVER: Please enter a file name \n");
        }
        else if (status == FILE_FOUND) {//If the server finds the searched for file
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            size = Integer.parseInt(in.readLine());//Get the size of the file
            String file = in.readLine();//Get the name of the file
            receiveFile(file);//Download the file
        }
        else if (status == FILE_NOT_FOUND) {//If the server doesnt find the searched for file
            gui.getjTextAreaOutput().append("SERVER: File not found. \n");
        }
        else if (status == FINISHED){//If the server has terminated
            gui.getjTextAreaOutput().setText("SERVER: Finished");
        }
    }



    public void receiveFile(String file) throws IOException {
        try (//Automatically close the resources when done
                Socket socket = new Socket("127.0.0.1", 3434);//Create a new socket for the file to be transferred over
                InputStream in = socket.getInputStream();//Create an input stream from the socket
                OutputStream out = new FileOutputStream("recv." + file.substring(file.indexOf(".") + 1));//Get the file extension
        ) {
            gui.getjTextAreaOutput().setText("SERVER: File '" + file + "' found. Beginning writhing file...\n");
            byte[] buf = new byte[8192];//Create a buffer
            int bytesRead = 0;
            while ((bytesRead = in.read(buf)) != -1) {//Read the input bytes from the server
                out.write(buf, 0, bytesRead);//Write the data to the new file
                downloaded += bytesRead;
                gui.getjProgressBar().setValue((int) getProgress());//Calculate quantity of file downloaded
            }
            gui.getjTextAreaOutput().append("SERVER: Writing file complete...\n");
            gui.setFileName(file);
        }
    }

}
