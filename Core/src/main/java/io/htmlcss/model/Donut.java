package io.htmlcss.model;

public class Donut {
	
	private String type;
	private Integer id;
	private String description;
	private String img;
	private String flavor;
	
	public Donut(Integer id, String type, String flavor, String desc, String img) {
		this.id = id;
		this.type = type;
		this.flavor = flavor;
		this.description = desc;
		this.img = img;
	}
	
	public Donut() {
		
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getImg() {
		return img;
	}
	
	public void setImg(String img) {
		this.img = img;
	}
	
	public String getFlavor() {
		return flavor;
	}
	
	public void setFlavor(String flavor) {
		this.flavor = flavor;
	}
}
