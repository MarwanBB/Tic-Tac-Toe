
package controllers;

import Utility.Constants;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.User;


public class Client {
    Socket mySocket;
    PrintStream printstream;
        //DataInputStream dis;
        //PrintStream ps;
        
        
        
            public Client(){
            try {
            mySocket = new Socket("127.0.0.1" , 5005);
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
            createThread();
}
        
        
        
        public void createThread(){
             
             new Thread(){
                 @Override
                public void run() {

                    while(true){
                             DataInputStream dataInputStream;
                             try {
                                 dataInputStream = new DataInputStream(mySocket.getInputStream());
                                 String str = dataInputStream.readLine();

                                 if(str!=null)
                                         {
                                             if(!str.isEmpty()){                            
                                                 //textArea.appendText(str+"\n");
                                             }
                                         }
                             } catch (IOException e) {
                                 System.out.println(e.toString());
                             }
                    }
                }
             }.start();}
        
        public void sendMessage(User user) {
        try {
            
            System.out.println(user.getUsername());
            System.out.println(user.getPassword());
            
            System.out.println(printstream);
            printstream = new PrintStream(mySocket.getOutputStream());
            printstream.println(Constants.signUp + "/" + user.getUsername() + "/" + user.getPassword());
            System.out.println(printstream);
            
            
            //printStream.println();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        }
        
        

}
