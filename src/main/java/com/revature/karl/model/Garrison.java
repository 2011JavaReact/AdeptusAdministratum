package com.revature.karl.model;

public class Garrison {

	private int id;
	private String chapter;
	private int size;

	public Garrison() {
		super();
	}

	public Garrison(String chapter, int size) {
		this.chapter = chapter;
		this.size = size;
	}

	public Garrison(int id, String chapter, int size) {
		this.id = id;
		this.chapter = chapter;
		this.size = size;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getChapter() {
		return chapter;
	}

	public void setChapter(String chapter) {
		this.chapter = chapter;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	@Override
	public boolean equals(Object object) {
		if (object == this) {return true;}
		if (!(object instanceof Garrison)) {return false;}
		
		if (this.getChapter().equals(((Garrison) object).getChapter()) 
				&& this.getSize() == (((Garrison) object).getSize()) ) {
			return true;
		} else {
			return false;
		}
	}

}
