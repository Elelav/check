/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taskalert;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import taskalert.AlertFrame.AlertJFrame;

/**
 *
 * @author Nikita
 */
public class Alert {
    
    class AlarmSound {        
        Clip clip;
        File file = new File("a.wav");
        
        void initSound() throws MalformedURLException,LineUnavailableException,
                UnsupportedAudioFileException,IOException {
            clip = AudioSystem.getClip();
            AudioInputStream ais = AudioSystem.getAudioInputStream(file.toURI().toURL());
            clip.open(ais);
        }
        void play(){
            clip.start();
        }
    }
    
    List<Date> alarmTimeList = new ArrayList<>();
    
    void recieveTime(TaskScanner tasks)throws ParseException{
        alarmTimeList=tasks.scanDate();              
    }
    
    boolean alarm(TaskScanner tasks)
            throws MalformedURLException, LineUnavailableException, 
            UnsupportedAudioFileException, IOException{         
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm");
        Date currentTime = new Date();
        Date subTime;
        Calendar sub5Minutes = Calendar.getInstance();
        AlarmSound sound = new AlarmSound();                
        int count=0;
        for(Date date : alarmTimeList){
            sub5Minutes.setTime(date);
            sub5Minutes.add(Calendar.MINUTE, -1);            
            if (sdf.format(date).equals(sdf.format(currentTime))){
                Task node = tasks.getNode(count);                
                AlertJFrame ajf = new AlertJFrame();
                ajf.setName("Alarm");
                ajf.setTextName(node.name);
                ajf.setTextDesc(node.description);
                ajf.setTextDate(sdf.format(node.date));
                ajf.setTextContacts(node.contacts);                
                sound.initSound();
                sound.play();
                ajf.setVisible(true);
                return true;
            }
            subTime = sub5Minutes.getTime();
            if(sdf.format(subTime).equals(sdf.format(currentTime))){
                Task node = tasks.getNode(count);                
                AlertJFrame ajf = new AlertJFrame();
                ajf.setName("5 minutes to alarm");
                ajf.setTextName(node.name);
                ajf.setTextDesc(node.description);
                ajf.setTextDate(sdf.format(node.date));
                ajf.setTextContacts(node.contacts);                
                sound.initSound();
                sound.play();
                ajf.setVisible(true);
                return true;
            }
        count++;    
        }
        return false;
    }
}
