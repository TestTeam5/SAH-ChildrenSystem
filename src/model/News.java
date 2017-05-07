package model;

import java.util.ArrayList;

public class News {
	
	private String title;
	private String date;
	private String detail;
	private String ID;
	private ArrayList<NewsTags> tagIts;
	private String location;
	boolean isDeleted = false;
	
	public String getTitle(){
		return this.title;
	}
	public String getDate(){
		return this.date;
	}
	public String getDetail(){
		return this.detail;
	}
	public String getID(){
		return this.ID;
	}
	public ArrayList<NewsTags> getTagIts(){
		return this.tagIts;
	}
	public String getLocation(){
		return this.location;
	}

	public void setTitle(String title){
		this.title = title;
	}
	public void setDate(String date){
		this.date = date;
	}
	public void setDetail(String detail){
		this.detail = detail;
	}
	public void setID(String ID){
		this.ID = ID;
	}
	public void setTagIts(ArrayList<NewsTags> tags){
		this.tagIts = tags;
	}
	public void setLocation(String location){
		this.location = location;
	}
	
	public void addTag(NewsTags tag){
		this.tagIts.add(tag);
		NewsTags.addCount(tag);
	}
	public void removeTag(NewsTags tag){
		this.tagIts.remove(tag);
		NewsTags.minusCount(tag);
	}
	
	public boolean isDeleted(){
		return this.isDeleted;
	}
	public void delete(){
		this.isDeleted = true;
	}
	public void restore(){
		this.isDeleted = false;
	}

}

