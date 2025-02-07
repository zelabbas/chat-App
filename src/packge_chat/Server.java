package packge_chat;


import javax.naming.NamingEnumeration;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Server extends JFrame implements ActionListener {

    JTextField message;
    JPanel panelData;
    Box vertical = Box.createVerticalBox();
    protected JLabel loadImage(final String pathToIcon, int width, int height) {
        ImageIcon image1 = new ImageIcon(ClassLoader.getSystemResource(pathToIcon));
        Image image2 = image1.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
        ImageIcon image3 = new ImageIcon(image2);
        return (new JLabel(image3));
    }

    public Server() {
        // set the size of the frame
        setSize(600, 1000);
        // Set a title for the window
        setTitle("Chat Server");
        setLayout(null);

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

        JLabel name = new JLabel("zelabbas");
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


        // add the second panel that will hold the Label messages and so forth
        panelData = new JPanel();
        panelData.setBounds(5, 85, 590, 820);
        add(panelData);

        // Required because panelData.add(vertical, BorderLayout.PAGE_START); needs a valid layout manager that supports positional adding
        panelData.setLayout(new BorderLayout()); // Set once at initialization


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

    @Override
    public void actionPerformed(ActionEvent ae) {
        String out = message.getText();
        if (out.isEmpty())
            return;
        message.setText("");

        JLabel output = new JLabel(out);
        JPanel p2 = new JPanel();
        p2.add(output);

        JPanel right = new JPanel(new BorderLayout());
        right.add(p2, BorderLayout.AFTER_LINE_ENDS);
        vertical.add(right);
        vertical.add(Box.createVerticalStrut(15));
        panelData.add(vertical, BorderLayout.PAGE_START);

        repaint();
        revalidate(); // Refresh the UI
//        invalidate();
//        validate();

        System.out.println("actionPerformed method from SERVER : " +  out);
    }
}
