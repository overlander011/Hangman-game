package Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiThreadRespond implements Runnable{
    
    private ServerSocket server;
    private Socket socket;
    private int port;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private int i=0;
    private String message ="";
    private static String[] words = {};
    private int no = 1;
    
    public MultiThreadRespond(int port,int no){
        this.port = port;
        try{
           this.no = no;
           server = new ServerSocket(port);
        }catch(Exception e){
            
        }

    }

    @Override
    public void run(){
        while(true){
            try{
                socket = server.accept();
                //System.out.println(socket);
                this.ois = new ObjectInputStream(socket.getInputStream());
                this.message = (String) ois.readObject();
                System.out.println(message);
                System.out.println("Client");
                
                if(message.equalsIgnoreCase("play")){
                    System.out.println("Client "+no+" : Playing");
                    //System.out.println("Message Received: " + message);
                    oos = new ObjectOutputStream(socket.getOutputStream());
                    oos.writeObject("Hi Client play now");
                }
                
                
                if(message.equalsIgnoreCase("exit")) break;
                
                i++;
                        
                //ois.close();
                //oos.close();
                //socket.close();
            }catch(Exception e){

            }

        }
    }

}
