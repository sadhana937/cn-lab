/*Write a program on datagram socket for client/server to display the 
messages on client side, typed at the server side.*/

import java.net.*;

public class UDPS {
    
    public static void main(String[] args) throws Exception{
        DatagramSocket serverSocket = null;
        try{
            serverSocket = new DatagramSocket(9000);
            System.out.println("Server is ready for the client");
            byte[] receiveData = new byte[1024];
            byte[] sendData = new byte[1024];

            while(true){
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);
                String sentence = new String(receivePacket.getData());
                System.out.println("RECEIVED: " + sentence);
                InetAddress IPAddress = receivePacket.getAddress();
                int port = receivePacket.getPort();
                String capitalizedSentence = sentence.toUpperCase();
                sendData = capitalizedSentence.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData,sendData.length, IPAddress, port);
                serverSocket.send(sendPacket);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        finally{
            serverSocket.close();
        }
        }
}
