package org.example.mygame.client;

import org.example.mygame.shared.Utilities;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Date;

public class Client extends Thread {
    Socket clientSocket = null;

    public Client(Socket s) {
        clientSocket = s;
    }

    public void run() {
        if (clientSocket == null) {
            return;
        }

        Utilities.printMsg("creating output stream");

        try (Socket localSocket = clientSocket;
             PrintStream out = new PrintStream(localSocket.getOutputStream())) {

            Utilities.printMsg("writing current date");
            Date d = new Date();
            out.println(d);

        } catch (IOException e) {
            System.err.println("IOException occurred, " + e);
        } finally {
            clientSocket = null;
        }
    }
}
