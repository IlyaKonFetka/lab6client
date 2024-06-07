package org.example.managers;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TCPTakeManager {
    private BufferedReader in;
    private InputStream inputStream;

    public TCPTakeManager(TCPConnectingManager connection) throws IOException {
        this.inputStream = connection.getIn();
        this.in = new BufferedReader(new InputStreamReader(inputStream));
    }

    public BufferedReader getIn() {
        return in;
    }

    public String take() throws IOException {
        DataInputStream dataIn = new DataInputStream(inputStream);

        // Чтение длины сообщения
        int messageLength = dataIn.readInt();
        byte[] messageBytes = new byte[messageLength];

        // Чтение самого сообщения
        dataIn.readFully(messageBytes);
        return new String(messageBytes, "UTF-8");
    }
}
