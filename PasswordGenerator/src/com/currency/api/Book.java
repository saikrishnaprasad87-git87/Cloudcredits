package com.currency.api;

class Book {
	
	private String title;
	private String author;
	private boolean isAvailable;
	
	//Constructor
	
	public Book(String title,String author) {
		this.title= title;
		this.author=author;
		this.isAvailable= true;  //Initially Book is available
		
		}
	
	public String getTitle() {
		return title;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public boolean getIsAvaibale(){
		return isAvailable;
		
	}	
	public void barrowBook() {
		
		if(isAvailable) {
			isAvailable=false;
			System.out.println(title + "has been borrowed.");
		}else {
			System.out.println(title + "is currently unavailable.");
		}
	}
	public void returnBook() {
		isAvailable=true;
		System.out.println(title + "has been returned");
	}
	public void displayBookInfo() {
		System.out.println("Title: " + title + ",Author: " + author + ", Available: " +(isAvailable ? "Yes" : "No"));
	}	
	}
