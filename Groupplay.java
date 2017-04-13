package groupplay;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
//import javax.media.CannotRealizeException;
//import javax.media.NoPlayerException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javazoom.jl.decoder.JavaLayerException;


/**
 *
 * @author Sanket Bhimani
 */
public class Groupplay {

    
    public static void main(String[] args) throws IOException, InterruptedException, UnsupportedAudioFileException, LineUnavailableException, FileNotFoundException, JavaLayerException{
        ServerSocket ss = new ServerSocket(61361);
       
           
            //String filename;
            //Scanner s = new Scanner(System.in);
            //filename = s.nextLine();
            
            File file = new File("---File path---");
            //Path a = file.toPath();
            System.out.println(file.length());
            byte[] ba = null;
            ba = Files.readAllBytes(file.toPath());
            FileInputStream fin = new FileInputStream(file);
        try ( //BufferedInputStream bin = new BufferedInputStream(fin);
        //bin.read(ba);
        //System.out.println();
        //Thread.sleep(5000);
                Socket socket = ss.accept()) {
            System.out.println("Accepted Connection: " + socket);
            OutputStream os = socket.getOutputStream();
            
            int bytesread=40;
            int tmp=0;
            while((fin.read(ba,tmp,bytesread))>0)
            {
                
                
                
                os.write(ba,tmp,bytesread);
                System.out.println("Sending File..." +tmp+"    "+bytesread);
                tmp=bytesread;
                bytesread=bytesread+40;
                
                if(bytesread>file.length())
                {
                    bytesread = (int) file.length();
                }
                // bytesread = bytesread+10000;
                
                
            }
            os.flush();
        }
            
       System.out.println("File tranfer completed");  
      
       Thread.sleep(3000);
       
    
        final play a = new play(file);
        //  Thread.sleep(1900);
     Thread t = new Thread( new Runnable(){

            @Override
            public synchronized void run() {
                try {
                    synchronized(a){
                    a.start();
                    System.out.println("play");
                    }
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Groupplay.class.getName()).log(Level.SEVERE, null, ex);
                } catch (JavaLayerException ex) {
                    Logger.getLogger(Groupplay.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        });
        t.start();
//        t.wait();
        a.stop();
        ServerSocket sss = new ServerSocket(61362);
        
      Socket socketplay = sss.accept();
      System.out.println("connect");
        a.start();
       
    }
    
}
