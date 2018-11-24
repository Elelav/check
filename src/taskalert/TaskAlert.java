/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taskalert;

import java.util.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author Nikita
 */
public class TaskAlert {
   
    public static void main(String[] args) throws IOException, ParseException, 
            InterruptedException {
        TaskScanner tScan = new TaskScanner();
        tScan.readFile("d:\\JAVA\\TaskManager2\\Journal.txt");        
        boolean check = false;        
        Alert alarm = new Alert();
        alarm.recieveTime(tScan);
        while(check == false){
            try{
                while(alarm.alarm(tScan) == false){            
                    alarm.alarm(tScan);                
                }
                Thread.sleep(60000);
            }
            catch(LineUnavailableException e){
                System.err.println("LineUnavailableException");
            }
            catch(UnsupportedAudioFileException e){
                System.err.println("UnsupportedAudioFileException"); //пока так
            }
            catch(MalformedURLException e){
                System.err.println("MalformedURLException");
            }
        }
    }
    
}
