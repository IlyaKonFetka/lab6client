package org.example.managers;

import org.example.managers.TCPConnectingManager;

import java.io.*;

public class TCPSendManager {
    private PrintWriter out;
    private OutputStream outputStream;

    public TCPSendManager(TCPConnectingManager connection) throws IOException {
        outputStream = connection.getOut();
        out = new PrintWriter(new OutputStreamWriter(outputStream), true);
    }

    public PrintWriter getOut() {
        return out;
    }

    public void push(String jsonMessage) throws IOException {
        byte[] messageBytes = jsonMessage.getBytes("UTF-8");
        int messageLength = messageBytes.length;

        // Отправка длины сообщения
        DataOutputStream dataOut = new DataOutputStream(outputStream);
        dataOut.writeInt(messageLength);

        // Отправка самого сообщения
        dataOut.write(messageBytes);
        dataOut.flush();
    }
}
