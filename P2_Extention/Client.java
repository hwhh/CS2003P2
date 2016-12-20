import java.io.*;
import java.net.Socket;

public class Client {

    private GUI gui;
    private Socket socket;
    private ClientProtocol clientProtocol;
    private boolean connected = false;

    public boolean isConnected() {
        return connected;
    }

    public static void main(String [] args) {
        //Create new jFrame on new thread
        java.awt.EventQueue.invokeLater(() -> {
            GUI gui = new GUI();
            gui.setVisible(true);//Set the jFrame to visible
        });
    }

    public Client(GUI gui) {
        this.gui = gui;
    }

    //Connect to server
    public void connect(String hostName, int portNumber) throws IOException {
        socket = new Socket(hostName, portNumber);//Create new socket with hist name and port number
        clientProtocol = new ClientProtocol(socket, gui);
        getServerStatus();//get the server status
        connected = true;
    }

    //Disconnect from server
    public void disconnect() throws IOException {
        if(socket.isConnected()) {
            socket.close();
            connected = false;
        }
    }

    public void search(String fileName) throws IOException {
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        out.println(fileName);//Send the name of the searched for file to server
        out.flush();
        getServerStatus();//Get the response from the server
    }

    public void getServerStatus() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            int fromServer;
            fromServer = in.read();//Read the response from the server
            clientProtocol.processInput(fromServer);//Interpret the response from the server
            if(fromServer == -1){//Check if the server has terminated
                disconnect();
                gui.getjTextAreaOutput().setText("SERVER DISCONNECTED");
            }

        } catch (Exception e) {
            gui.getjTextAreaOutput().setText("SERVER DISCONNECTED");
        }
    }

}
