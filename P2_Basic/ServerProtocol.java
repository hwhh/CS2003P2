import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ServerProtocol {

    private static final int WAITING = 0;
    private static final int FILE_FOUND = 2;
    private static final int FILE_NOT_FOUND = 3;
    private static final int FINISHED = -1;

    private int state = WAITING;
    private Socket socket;

    public ServerProtocol(Socket socket) {
        this.socket = socket;
    }

    public int getState() {
        return state;
    }

    //Interpret response sent from client
    public void processInput(String input) throws IOException {
        if(input == null){//If the input is null the server is waiting for data from client
            state = WAITING;
        } else if (fileFound(input)) {//Check if the file requested by the client exists on the server
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());//Create output stream
            out.writeInt(2);//Tell the client the file has been found
            out.flush();
            for (String s : contentsOfFile(new File(input))) {
                out.writeUTF(s);//Write the contents of the file to the client
                out.flush();
            }
            state = FILE_FOUND;
        }else if(input.equals("exit")){//Check if the user has entered "exit"
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.writeInt(-1);//Tell the client the connection has been closed
            out.flush();
            state = FINISHED;
        }else {
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.writeInt(3);//Tell the client the file hasn't been found
            out.flush();
            state = FILE_NOT_FOUND;
        }
    }

    public boolean fileFound(String fileName){
        return new File(fileName).exists();//Check if a file exists in current directory
    }

    private List<String> contentsOfFile(File fileName){
        Scanner file = null;
        List<String> contents = new ArrayList<>();
        String fileExtension = fileName.getName().substring(fileName.getName().indexOf(".") + 1);
        if(!fileExtension.equals("txt")){
            contents.add(fileExtension + " not recognised, please enter a file in format .txt");
            contents.add("EOF");
            return contents;
        }
        try {
            file = new Scanner(fileName);//Create scanner for the found file
            while(file.hasNext()){
                contents.add(file.next());//Add the contents of the file to a list
            }
        } catch (FileNotFoundException e) {
            contents.add("something went wrong: "+e.getMessage());
        }
        contents.add("EOF");//Add EOF to the end of the list
        return contents;
    }
}
