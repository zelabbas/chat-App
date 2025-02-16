# chat-App
Chatting App using Java Swing &amp; Sockets for real-time messaging. Supports multiple users connecting to a server for live chat. Demonstrates networking, and GUI concepts in Java. A simple yet powerful way to learn real-time communication in Java.


# Java Swing GUI Structure:
1   ️⃣ JFrame (Window/Main Container)

    1.1 The main window that holds everything.
    1.2 Example: new JFrame("My Chat App").
2   ️⃣ JPanel (Sections within the Window)

    2.1 Panels organize components inside the JFrame.
    2.1 Example: Header panel, chat area, input field panel.
3   ️⃣ JLabel, JButton, JTextField (Components inside Panels)

    3.1 JLabel → Displays text/images.
    3.2 JButton → Clickable button.
    3.3 JTextField/JTextArea → Input fields for text.

## Example: WhatsApp-Style Chat Structure: 
    [JFrame] (Main Window)
    ├── [JPanel: Header] (Holds Profile, Video Call, Phone Icons)
    ├── [JPanel: Chat Area] (Holds Messages)
    │     ├── [JPanel: Message 1]
    │     ├── [JPanel: Message 2]
    │     ├── [JPanel: Message 3]
    ├── [JPanel: Input Area] (Holds TextField + Send Button)
    ├── [JTextField] (Message Input)
    ├── [JButton] (Send Button)

![Chat App Preview](https://github.com/zelabbas/chat-App/blob/main/src/icons/screenShot.png)
