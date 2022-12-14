/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.FileChooser;
import models.GameDataPVP;

/**
 *
 * @author Copy Center
 */
public class LocalDataBase {

    FileOutputStream localFileOutputStream;
    FileInputStream localFileIntputStream;
    File localFile;
    public String localData;

    public LocalDataBase() throws IOException {
        localFile = new File("localDataBase.txt");
        if (!localFile.exists()) {
            localFile.createNewFile();
        }
        localData = readData();
    }

    public String readData() throws IOException {
        localFileIntputStream = new FileInputStream(localFile);
        String text = new String();
        int i = localFileIntputStream.read();
        while (!(i == -1)) {
            char c = (char) i;
            text+=String.valueOf(c);
            
            i = localFileIntputStream.read();
            
        }
        return text;
    }

    public void writeData(GameDataPVP gamePVP) {
        try {
            String delimiter= ",";
            localFileOutputStream = new FileOutputStream(localFile ,true);
            localFileOutputStream.write((gamePVP.getGameDate()).getBytes());
            localFileOutputStream.write(delimiter.getBytes());
            
            localFileOutputStream.write((gamePVP.getPlayer1()).getBytes());
            localFileOutputStream.write(delimiter.getBytes());
            localFileOutputStream.write((gamePVP.getPlayer1Score()).getBytes());
            localFileOutputStream.write(delimiter.getBytes());
            
            localFileOutputStream.write((gamePVP.getPlayer2()).getBytes());
            localFileOutputStream.write(delimiter.getBytes());
            localFileOutputStream.write((gamePVP.getPlayer2Score()).getBytes());
            localFileOutputStream.write(delimiter.getBytes());
            
            localFileOutputStream.write((gamePVP.getWinner()).getBytes());
            localFileOutputStream.write("//t".getBytes());
            localFileOutputStream.flush();
            localFileOutputStream.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LocalDataBase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LocalDataBase.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}