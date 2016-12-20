import javax.swing.*;
import javax.swing.JOptionPane;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GUI extends javax.swing.JFrame {

    public GUI() {
        initComponents();
        this.client = new Client(this);
    }

    //Netbeans generated code
    private void initComponents() {
        jTextFieldFile = new javax.swing.JTextField();
        jTextFieldPort = new javax.swing.JTextField();
        jTextFieldServerName = new javax.swing.JTextField();
        jButtonConnect = new javax.swing.JButton();
        jProgressBar = new javax.swing.JProgressBar();
        jButtonSearch = new javax.swing.JButton();
        jButtonOpen = new javax.swing.JButton();
        jScrollPane = new javax.swing.JScrollPane();
        jTextAreaOutput = new javax.swing.JTextArea();
        jButtonOpen.setEnabled(false);
        jButtonSearch.setEnabled(false);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextFieldFile.setText("File name");
        jTextFieldFile.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldFileFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldFileFocusLost(evt);
            }
        });


        jTextFieldPort.setText("Port Number");
        jTextFieldPort.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldPortFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldPortFocusLost(evt);
            }
        });

        jTextFieldServerName.setText("Server Name");
        jTextFieldServerName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldServerNameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldServerNameFocusLost(evt);
            }
        });

        jButtonConnect.setText("Connect");
        jButtonConnect.addActionListener(this::jButtonConnectActionPerformed);

        jButtonSearch.setText("Search");
        jButtonSearch.addActionListener(this::jButtonSearchActionPerformed);

        jButtonOpen.setText("Open");
        jButtonOpen.addActionListener(this::jButtonOpenActionPerformed);

        jTextAreaOutput.setColumns(20);
        jTextAreaOutput.setRows(5);
        jScrollPane.setViewportView(jTextAreaOutput);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jTextFieldFile, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jTextFieldServerName, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jTextFieldPort, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jProgressBar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jButtonConnect, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButtonOpen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addComponent(jButtonSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 525, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jTextFieldServerName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextFieldPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButtonConnect))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jTextFieldFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButtonSearch))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButtonOpen))
                                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }
    //Clear text filed
    private void jTextFieldFileFocusGained(java.awt.event.FocusEvent evt) {
        jTextFieldFile.setText("");
    }


    private void jTextFieldFileFocusLost(java.awt.event.FocusEvent evt) {
        if (jTextFieldFile.getText().equals("")) {
            jTextFieldFile.setText("File name");//Reset the text in the jTextFieldFile
        } else {
            try{
                fileName = jTextFieldFile.getText();//Set the file name attribute to the text in the jTextFieldFile
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    //Clear text filed
    private void jTextFieldServerNameFocusGained(java.awt.event.FocusEvent evt) {
        jTextFieldServerName.setText("");
    }

    private void jTextFieldServerNameFocusLost(java.awt.event.FocusEvent evt) {
        if (jTextFieldServerName.getText().equals("")) {
            jTextFieldServerName.setText("Server Name");//Reset the text in the jTextFieldServerName
        } else {
            try{
                serverName = jTextFieldServerName.getText();//Set the server name attribute to the text in the jTextFieldServerName
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    //Clear text filed
    private void jTextFieldPortFocusGained(java.awt.event.FocusEvent evt) {
        jTextFieldPort.setText("");
    }

    private void jTextFieldPortFocusLost(java.awt.event.FocusEvent evt) {
        if (jTextFieldPort.getText().equals("")) {
            jTextFieldPort.setText("Port Number");//Reset the text in the jTextFieldPort
        } else {
            try{
                portNumber = Integer.parseInt(jTextFieldPort.getText());//Set the port number attribute to the text in the jTextFieldPort
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    private void jButtonConnectActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            serverName=jTextFieldServerName.getText();
            portNumber = Integer.parseInt(jTextFieldPort.getText());
            if(serverName != null && portNumber != 0 && portNumber !=3434){//Check the port number and server name is valid and not reserved
                jTextAreaOutput.setText("");
                    if(!connected){//If the client isn't connected to a server
                        //Connect to the server
                        client.connect(serverName, portNumber);
                        //Change the attributes of components
                        jTextFieldPort.setText(String.valueOf(portNumber));
                        jTextFieldServerName.setText(serverName);
                        jTextFieldPort.setEnabled(false);
                        jTextFieldServerName.setEnabled(false);
                        jButtonSearch.setEnabled(true);
                        jProgressBar.setValue(0);
                        jButtonConnect.setText("Disconnect");
                    }else {//If the client is connected to a server
                        //Disconnect to the server
                        client.disconnect();
                        //Change the attributes of components
                        jTextFieldPort.setText("Port Number");
                        jTextFieldServerName.setText("Server Name");
                        jTextFieldFile.setText("File name");
                        jTextFieldPort.setEnabled(true);
                        jTextFieldServerName.setEnabled(true);
                        jButtonSearch.setEnabled(false);
                        jProgressBar.setValue(0);
                        jButtonConnect.setText("Connect");
                    }
                    connected = client.isConnected();

            }else {
                JOptionPane.showMessageDialog(this,"Invalid server name/port number"," Warning", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,"Invalid server name/port number"," Warning", JOptionPane.WARNING_MESSAGE);
        }
    }



    private void jButtonSearchActionPerformed(java.awt.event.ActionEvent evt) {
        jProgressBar.setValue(0);//Reset the jProgressBar
        if (!jTextFieldFile.getText().equals("") && connected)
            try {
                jButtonOpen.setEnabled(false);
                client.search(jTextFieldFile.getText());//Query the server for the input file
                jButtonOpen.setEnabled(true);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Invalid input", " Warning", JOptionPane.WARNING_MESSAGE);
            }
    }

    private void jButtonOpenActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            Desktop.getDesktop().open(new File(fileName));//Open the returned file from the server with the defult program
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Can't open file", " Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    //Getters and setters
    public JTextArea getjTextAreaOutput() {
        return jTextAreaOutput;
    }

    public JProgressBar getjProgressBar() {
        return jProgressBar;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    //Attribute deceleration
    private Client client;
    private boolean connected  = false;
    private String fileName;
    private String serverName = "127.0.0.1";
    private int portNumber = 12345;
    private javax.swing.JButton jButtonConnect;
    private javax.swing.JButton jButtonOpen;
    private javax.swing.JButton jButtonSearch;
    private javax.swing.JProgressBar jProgressBar;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JTextField jTextFieldFile;
    private javax.swing.JTextField jTextFieldPort;
    private javax.swing.JTextField jTextFieldServerName;
    private javax.swing.JTextArea jTextAreaOutput;

}
