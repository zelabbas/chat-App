import packge_chat.*;

import javax.swing.*;
import java.awt.*;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.*;

public class Main {
    public static void main(String[] args) {

        Server server = new Server();
        Client client = new Client();

        server.startChat();
        client.startChat();

//        try {
//            ServerSocket skt = new ServerSocket(8080);
//            while (true) {
//               Socket s =  skt.accept();
//                DataInputStream din = new DataInputStream(s.getInputStream());
//                Server.dout = new DataOutputStream(s.getOutputStream());
//
//                while (true) {
//                    String msg = din.readUTF();
//                    JPanel panel = Server.formatLabel(msg);
//
//                    JPanel left = new JPanel(new BorderLayout());
//                    left.add(panel, BorderLayout.LINE_START);
//                    Server.vertical.add(left);
//                    Server.window.validate();
//                }
//            }
//        } catch (Exception e) {
//            System.err.println("something went wrong!");
//            e.printStackTrace();
//        }

    }
}