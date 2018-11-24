/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taskalert;

import java.util.Date;

/**
 *
 * @author Nikita
 */
 public class Task {        
        String name;
        String description;
        Date date = new Date();
        String contacts;
        
        Task(String name, String description, Date date, String contacts){
            this.name=name;
            this.description=description;
            this.date=date;
            this.contacts=contacts;
        }
        
        public String getName(){
            return name;
        }
        
        public String getDescription(){
            return description;
        }
        
        public Date getDate(){
            return date;
        }
        
        public String getContacts(){
            return contacts;
        }
    }       

