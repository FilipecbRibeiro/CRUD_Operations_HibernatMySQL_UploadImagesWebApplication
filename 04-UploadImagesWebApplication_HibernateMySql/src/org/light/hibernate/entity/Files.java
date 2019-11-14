package org.light.hibernate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/*
 * Through several documentation it is said that an Entity Class should have a no argument Constructor !
 * 
 * NOTe: ALways use javax.persistence
 * 
 */



@Entity (name="files")//
@Table (name ="tablefiles")

public class Files {

	@Id
	@Column(name="id")
	private int id; 
	
	@Column(name="file_name")
	private String fileName ;
	
	@Column(name="label")
	private String label;
	
	@Column(name="caption")
	
	
	
	private String caption;

	public Files() {
	
	}

	public Files(String fileName, String label, String caption) {
		
		this.fileName = fileName;
		this.label = label;
		this.caption = caption;
	}

	public Files(String name) {
		this.fileName= name;
	}

	public Files(int fieldId, String label, String caption) {
		this.id=fieldId;
		this.label=label;
		this.caption=caption;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	@Override
	public String toString() {
		return "Files [id=" + id + ", fileName=" + fileName + ", label=" + label + ", caption=" + caption + "]";
	}
	
	
	
}
