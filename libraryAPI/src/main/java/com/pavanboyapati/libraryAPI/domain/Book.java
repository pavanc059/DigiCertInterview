package com.pavanboyapati.libraryAPI.domain;

import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

/*Data Transfer Object*/
public class Book  extends RepresentationModel<Book> implements Serializable {

    private int bookID;

    private String title;

    private long ISBN;

    private String author;

    private LocalDate publishedDate;

    protected Book(int bookID, String title, long ISBN, String author, LocalDate publishedDate){
        this.bookID=bookID;
        this.title = title;
        this.ISBN = ISBN;
        this.author = author;
        this.publishedDate = publishedDate;
    }

    public int getBookID() {
        return bookID;
    }

    public String getTitle() {
        return title;
    }

    public long getISBN() {
        return ISBN;
    }

    public String getAuthor() {
        return author;
    }

    public LocalDate getPublishedDate() {
        return publishedDate;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookID=" + bookID +
                ", title='" + title + '\'' +
                ", ISBN=" + ISBN +
                ", author='" + author + '\'' +
                ", publishedDate=" + publishedDate +
                '}';
    }
}
