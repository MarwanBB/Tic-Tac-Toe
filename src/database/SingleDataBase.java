/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.GameDataPVP;
import models.GameDataPlayerVsPC;

/**
 *
 * @author Copy Center
 */
public class SingleDataBase {
    FileInputStream singleFileInputStream;
    FileOutputStream singleFileOuttputStream;
    File singleFile;
    public String singleData;
    public SingleDataBase() throws IOException {
        singleFile = new File("SingleDataBase.txt");
        if (!singleFile.exists()) {
            singleFile.createNewFile();
        }
        singleData = readData();
    }
    public String readData() throws IOException {
        singleFileInputStream = new FileInputStream(singleFile);
        String text = new String();
        int i = singleFileInputStream.read();
        while (!(i == -1)) {
            char c = (char) i;
            text+=String.valueOf(c);
            
            i = singleFileInputStream.read();
            
        }
        return text;
    }
     public void writeData(GameDataPlayerVsPC gameDataPlayerVsPC) {
        try {
            String delimiter= ",";
            singleFileOuttputStream = new FileOutputStream(singleFile ,true);
            singleFileOuttputStream.write((gameDataPlayerVsPC.getGameDate()).getBytes());
            singleFileOuttputStream.write(delimiter.getBytes());
            
            singleFileOuttputStream.write((gameDataPlayerVsPC.getPlayer1()).getBytes());
            singleFileOuttputStream.write(delimiter.getBytes());
            singleFileOuttputStream.write((gameDataPlayerVsPC.getPlayer1Score()).getBytes());
            singleFileOuttputStream.write(delimiter.getBytes());
            
            singleFileOuttputStream.write((gameDataPlayerVsPC.getPlayer2()).getBytes());
            singleFileOuttputStream.write(delimiter.getBytes());
            singleFileOuttputStream.write((gameDataPlayerVsPC.getPlayer2Score()).getBytes());
            singleFileOuttputStream.write(delimiter.getBytes());
            
            singleFileOuttputStream.write((gameDataPlayerVsPC.getWinner()).getBytes());
            singleFileOuttputStream.write("//t".getBytes());
            singleFileOuttputStream.flush();
            singleFileOuttputStream.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LocalDataBase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LocalDataBase.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
