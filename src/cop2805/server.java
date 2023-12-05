package cop2805;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.* ;
import java.io.*;
import java.nio.charset.*;
import java.util.ArrayList;
import java.util.List;

public class server {
    public static void main(String[] args) {
        LineSearcher search = new LineSearcher("hamlet.txt");
        Boolean shutdown =false ;
        ServerSocket server = null;
        //create server
        try {
            server = new ServerSocket(1236);
            System.out.println("Is on....");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

            while(!shutdown)
            {
                Socket client = null;
                InputStream input= null;
                OutputStream output = null;


                try {
                    client = server.accept();
                    input = client.getInputStream();
                    output = client.getOutputStream();


                    int n = input.read();
                    byte[] data = new byte[n];
                    input.read(data);

                    String clientInput = new String(data, StandardCharsets.UTF_8);
                    //checking too shutdown
                    if(clientInput.equalsIgnoreCase("shutdown")) {
                        System.out.println("Shutting down...");
                        shutdown = true;
                        client.close();
                        break;
                    }

                    //calling to search for the lines
                    List<String> lines=search.LineSearcher(Integer.parseInt(clientInput));
                    System.out.println("Client said:"+clientInput);
                   //output of client
                    for (String line: lines)
                    {
                        String response = line + "\n";
                        output.write(response.getBytes());
                    }

                    client.close();



                } catch (IOException e) {
                    throw new RuntimeException(e);
                }


            }
        }
    }

