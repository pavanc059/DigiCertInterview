package com.pavanboyapati.libraryAPI.repository;

import com.pavanboyapati.libraryAPI.domain.Book;
import com.pavanboyapati.libraryAPI.libraryExceptions.LibraryRuntimeExceptions;

import java.util.Map;
import java.util.Optional;

public interface IBookRepository {

    /*To retrieve all books in Library*/
    public Map<Integer, Book> findAll();

    /*add new Book the book instance passed*/
    public Book addBook(Book book);

    /*Updates the book instance passed*/
    public Book save(Integer bookId, Book book);

    /*delete the book instance passed*/
    public Book delete(Integer bookId);

    /*To remove all Books from library*/
    public boolean deleteAll();

    /*get count of Books from library*/
    public Integer count();

}
