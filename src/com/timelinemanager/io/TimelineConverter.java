package com.timelinemanager.io;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import com.timelinemanager.Entity.Timeline;

/**
 * TimelineConverter class to handle converting a timeline object
 * together with its content to text format so it later can be saved
 * to a text file.
 * 
 * @author 
 * @version 0.00.00
 * @name TimelineConverter.java
 */
public class TimelineConverter {
	
	private ArrayList<String> events;
	private PrintStream p;
	
	public TimelineConverter(){}

	public TimelineConverter(Timeline t) {
		events.add(t.getTitle());
		events.add("\n");
		//events.addAll(t.outputdata());
	}
	
	public void addInformation(Timeline t){
		events.add(t.getTitle());
		events.add("\n");
		//events.addAll(t.outputdata());
	}
	
	public void save(String pathname ){
		FileOutputStream out;
		
		try{
			out = new FileOutputStream(pathname);
			p = new PrintStream(out);
			for(String s:events){
				p.append(s);
				p.append("\n");
			}
		}catch(Exception e){System.err.println ("Error writing to file");}
	}
	
	public void clear(){
		events.clear();
	}
	
	

}
