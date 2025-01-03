package com.task.servlet;
import com.task.servlet.book;

public class test {
	public static void main(String[] args) {
		book book = new book();
		book.setTitle("First book");
		book.setAuthor("Someone");
        book.setPrice(20);
        
        
        System.out.println("Title: " + book.getTitle());
        System.out.println("Author: " + book.getAuthor());
        System.out.println("Price: " + book.getPrice());
	}

}


