package packge_chat;

import javax.naming.NamingEnumeration;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Client extends JFrame implements ActionListener {

    JTextField message;
    static JPanel panelData;
    static DataOutputStream dout;
    static Box vertical = Box.createVerticalBox();
    protected JLabel loadImage(final String pathToIcon, int width, int height) {
        ImageIcon image1 = new ImageIcon(ClassLoader.getSystemResource(pathToIcon));
        Image image2 = image1.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
        ImageIcon image3 = new ImageIcon(image2);
        return (new JLabel(image3));
    }

    public Client() {
        // set the size of the frame
        setSize(600, 1000);
        // Set a title for the window
        setTitle("Chat Server");
        setLayout(null);

        // Register Escape key to close the window
        getRootPane().registerKeyboardAction(e -> {
            System.out.println("close window!");
            System.exit(0);
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);

        // to remove the up bar!
//        setUndecorated(true);

        // create the Panel that hold the profile
        JPanel panel = new JPanel();
//        panel.setBackground(Color.RED);
        panel.setBackground(new Color(0, 128, 128)); // Elegant Teal

        panel.setLayout(null);

        // set the position on the Frame && the width and height
        panel.setBounds(0, 0, 600, 80);

        // add the panel on the Frame
        add(panel);

        // load the icon
        JLabel back = loadImage("icons/3.png", 25, 25);
        // set the position on the frame and the width and height
        back.setBounds(5, 22 , 25, 25);
        panel.add(back);

        JLabel profile = loadImage("icons/1.png", 50,50);
        profile.setBounds(50, 10, 50, 50);
        panel.add(profile);

        JLabel video = loadImage("icons/video.png", 40, 40);
        video.setBounds(420, 20, 40, 40);
        panel.add(video);

        JLabel phone = loadImage("icons/phone.png", 40, 40);
        phone.setBounds(490, 20, 40, 40);
        panel.add(phone);

        JLabel moreVert = loadImage("icons/3icon.png", 20, 30);
        moreVert.setBounds(550, 25, 20, 30);
        panel.add(moreVert);

        JLabel name = new JLabel("saim");
        name.setBounds(110, 25, 100, 18);
        name.setForeground(Color.white);
        name.setFont(new Font("SAN_SERIF", Font.BOLD, 18));
        panel.add(name);

        JLabel status = new JLabel("online");
        status.setBounds(110, 50, 100, 15);
        status.setForeground(Color.green);
        status.setFont(new Font("SAN_SERIF", Font.BOLD, 15));
        panel.add(status);

        // add mouseListener on the Label
        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                System.out.println("exist from mouse");
                System.exit(0);
            }
        });


        // Create panelData (message container)
        panelData = new JPanel();
        panelData.setLayout(new BoxLayout(panelData, BoxLayout.Y_AXIS)); // Stack messages vertically

// Wrap panelData inside a JScrollPane
        JScrollPane scrollPane = new JScrollPane(panelData);
        scrollPane.setBounds(5, 85, 590, 820);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

// Add the scrollPane instead of panelData directly
        add(scrollPane);


//        // add the second panel that will hold the Label messages and so forth
//        panelData = new JPanel();
//        panelData.setBounds(5, 85, 590, 820);
//        add(panelData);
//
//        // Required because panelData.add(vertical, BorderLayout.PAGE_START); needs a valid layout manager that supports positional adding
//        panelData.setLayout(new BorderLayout()); // Set once at initialization


        // add the input message
        message = new JTextField();
        message.setBounds(5, 915, 420, 40);
        message.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        add(message);

        message.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    actionPerformed(new ActionEvent(message, ActionEvent.ACTION_PERFORMED, null));
                }
            }
        });

        // add the button send
        JButton send = new JButton("Send");
        send.setBounds(450, 915, 123, 40);
        send.setBackground(new Color(0, 128, 128));
        send.setForeground(Color.WHITE);
        send.addActionListener(this);
        send.setFont(new Font("SAN_SERIF", Font.BOLD, 16));
        add(send);

        // just a test for scroll
//        JScrollPane scrollPane = new JScrollPane(panelData);
//        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // make the Frame visible
        setVisible(true);

        // Center the window on screen
        setLocationRelativeTo(null);

        getContentPane().setBackground(Color.white);
        // Close the application when the frame is closed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static JPanel formatLabel(String out) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JLabel output = new JLabel("<html><p style=\"width: 170px\"> " + out + "</p></html>");
        output.setFont(new Font("Tahoma", Font.PLAIN, 16));
        output.setBackground(new Color(37, 211, 102));
        output.setOpaque(true);
        output.setBorder(new EmptyBorder(15, 15, 15 ,50));

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        JLabel time = new JLabel();
        time.setText(sdf.format(cal.getTime()));
        time.setForeground(Color.gray);

        panel.add(output);
        panel.add(time);
        return (panel);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (message.getText().isEmpty())
            return;
        String out = message.getText();
        try {
            dout.writeUTF(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        message.setText("");

//        JLabel output = new JLabel(out);
//        output.setFont(new Font("Tahoma", Font.PLAIN, 16));
//        output.setBackground(new Color(37, 211, 102));
//        output.setOpaque(true);
        JPanel p2 = formatLabel(out);
//        p2.add(output);

        JPanel right = new JPanel(new BorderLayout());
        right.add(p2, BorderLayout.LINE_END);
        vertical.add(right);
        vertical.add(Box.createVerticalStrut(15));
        panelData.add(vertical, BorderLayout.PAGE_START);

        repaint();
        revalidate(); // Refresh the UI
        //        validate();
        //        invalidate();

        System.out.println("actionPerformed from Client : " +  out);
    }

    public void startChat() {
        try {
            Socket s = new Socket("127.0.0.1", 8080);
            DataInputStream din = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());

            while (true) {
                panelData.setLayout(new BorderLayout());
                String msg = din.readUTF();
                JPanel panel = Server.formatLabel(msg);

                JPanel left = new JPanel(new BorderLayout());
                left.add(panel, BorderLayout.LINE_START);
                vertical.add(left);
                vertical.add(Box.createVerticalStrut(15));
                panelData.add(vertical, BorderLayout.PAGE_START);
                validate();
            }
        } catch (Exception e) {
            System.err.println("something went wrong!");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        Client client = new Client();
        client.startChat();
    }

}
