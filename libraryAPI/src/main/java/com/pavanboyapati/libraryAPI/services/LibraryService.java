package com.pavanboyapati.libraryAPI.services;

import com.pavanboyapati.libraryAPI.domain.Book;
import com.pavanboyapati.libraryAPI.libraryExceptions.LibraryRuntimeExceptions;
import com.pavanboyapati.libraryAPI.repository.IBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class LibraryService {

    private IBookRepository bookRepository;

    public IBookRepository getBookRepository() {
        return bookRepository;
    }

    @Autowired
    public void setBookRepository(IBookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book findById(Integer bookID){
        Map<Integer, Book> availableBooks = getBookRepository().findAll();
        Book book = availableBooks.get(bookID);
        if(null == book)
            throw new LibraryRuntimeExceptions("Not found", "Book details with Book ID passed not found");
        return  book;
    }

    public boolean existsById(int bookID){
        Iterator<Map.Entry<Integer, Book>> iterator= getBookRepository().findAll().entrySet().iterator();
        while (iterator.hasNext()){
            if(iterator.next().getKey().equals(bookID)){
                return true;
            }
        }
        return false;
    }

    public Integer count(){
        return getBookRepository().count();
    }
    public List<Book> getAllBooks(){
        List<Book> booksList = new ArrayList<>();
        Map<Integer, Book> booksMap= getBookRepository().findAll();
        if(booksMap.isEmpty()){
            throw new LibraryRuntimeExceptions("Not found", "Books Not Available in Library");
        }
        booksList.addAll(booksMap.values().stream().collect(Collectors.toList()));
        return booksList;
    }

    public Book addNewBook(Book book) {
        return getBookRepository().addBook(book);
    }

    public Book save(Integer bookId, Book book) {
        Map<Integer, Book> availableBooks = getBookRepository().findAll();
        if(null == availableBooks.get(bookId)){
            throw new LibraryRuntimeExceptions("Not found", "Book Details passed Not Available in Library");
        }
        return getBookRepository().save(bookId, book);
    }


    public Book deleteBook(Integer bookId) {
       return getBookRepository().delete(bookId);
    }


    @ExceptionHandler(LibraryRuntimeExceptions.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleExceptions(Exception e){
        return e.getMessage();
    }

}
