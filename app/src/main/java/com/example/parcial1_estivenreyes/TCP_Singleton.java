package com.example.parcial1_estivenreyes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class TCP_Singleton extends Thread{

    private Socket sock;
    private BufferedReader bufferR;
    private BufferedWriter bufferW;
    private MainActivity app;

    public TCP_Singleton(MainActivity app){
        this.app = app;

    }

    public void run() {
        try {
            this.sock = new Socket("192.168.18.4", 5000);

            //Reader
            InputStream s = sock.getInputStream();
            InputStreamReader sr = new InputStreamReader(s);
            this.bufferR = new BufferedReader(sr);

            //Writer
            OutputStream os = sock.getOutputStream();
            OutputStreamWriter sw = new OutputStreamWriter(os);
            this.bufferW = new BufferedWriter(sw);

            while (true) {
                messageRecived();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void requestConexion(/*String ip*/) {
        this.start();
    }

    public void sendMessage(String message) {
        new Thread(
                () -> {
                    try {
                        bufferW.write(message + "\n");
                        bufferW.flush();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
        ).start();
    }

    public void messageRecived() throws IOException {
        String line = bufferR.readLine();

    }

    public void closeConexion (){
        try {
            sock.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


}
