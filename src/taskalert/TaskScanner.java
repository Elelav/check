/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package taskalert;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.io.*;
import java.util.*;

/**
 *
 * @author Nikita
 */

public class TaskScanner {
    
    List<Task> tasks = new ArrayList<>();
    
    void add (String name, String description, Date date, String contacts) 
            throws ParseException{
        Task newTask = new Task(name,description,date,contacts);        
        tasks.add(newTask);
    }
    
    Task getNode(int num){
        try{
            return tasks.get(num);
        }
        catch (IndexOutOfBoundsException e){
            System.out.println("No Such Element");
        return null;
        }
    }
    
    List<Date> scanDate(){
        List <Date> dateList = new LinkedList();
        for(Task task : tasks){
            dateList.add(task.date);
        }
        return dateList;
    }
    
    
    void readFile(String fileName) throws IOException, ParseException{
        File file = new File(fileName);
        Scanner scan = new Scanner(file); 
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH-mm");       
        while (scan.hasNextLine()) {            
            String name=scan.nextLine();
            String description=scan.nextLine();           
            Date date = format.parse(scan.nextLine());
            String contacts=scan.nextLine();
            String emptyln = scan.nextLine();
            add(name,description,date,contacts);            
        }
    }
    
}
