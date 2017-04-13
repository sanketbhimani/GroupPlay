/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package groupplay;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;


public class play {
    File file;
    boolean pc;
    Player playmp3;
    FileInputStream fis;
    play(File f) throws FileNotFoundException, JavaLayerException
    {
    file = f;
    
     fis = new FileInputStream(file);
   
    }
   void start() throws FileNotFoundException, JavaLayerException {
     
       playmp3 = new Player(fis);
      playmp3.play();
      
   }
    void stop() throws InterruptedException{
       playmp3.wait();
       
   }
}
