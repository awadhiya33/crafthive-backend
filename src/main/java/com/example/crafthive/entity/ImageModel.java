package com.example.crafthive.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="image_model")
public class ImageModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private String type;
	
	@Column(length=50000000)
	private byte[] picBytes;

	public ImageModel() {
		
	}

	public ImageModel(String name, String type, byte[] picBytes) {
		
		this.name = name;
		this.type = type;
		this.picBytes = picBytes;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public byte[] getPicBytes() {
		return picBytes;
	}

	public void setPicBytes(byte[] picBytes) {
		this.picBytes = picBytes;
	}
	
	

}
