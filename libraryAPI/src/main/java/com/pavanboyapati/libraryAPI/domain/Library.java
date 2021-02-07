package com.pavanboyapati.libraryAPI.domain;

import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.*;


public class Library {

   public static Map<Integer, Book> bookList = new HashMap<Integer, Book>();

    public Library(int bookID, String title, long ISBN, String author, LocalDate publishedDate){
        Book book = new Book(bookID, title, ISBN, author,publishedDate);
        bookList.put(bookID, book);
    }

}
