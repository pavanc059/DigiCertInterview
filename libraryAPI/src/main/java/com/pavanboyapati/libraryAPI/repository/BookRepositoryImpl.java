package com.pavanboyapati.libraryAPI.repository;

import com.pavanboyapati.libraryAPI.domain.Book;
import com.pavanboyapati.libraryAPI.domain.Library;
import com.pavanboyapati.libraryAPI.libraryExceptions.BookAddException;
import com.pavanboyapati.libraryAPI.libraryExceptions.LibraryRuntimeExceptions;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;

@Repository
public class BookRepositoryImpl implements IBookRepository{
    Map<Integer, Book> bookList = Library.bookList;


    public Map<Integer, Book> findAll() {
        return Library.bookList;
    }

    @Override
    public Book addBook(Book book) throws BookAddException {
        return bookList.put(book.getBookID(),book);
    }


    public Book save(Integer bookId, Book book){
        if (bookList.isEmpty() || null == bookList.get(bookId)) throw new LibraryRuntimeExceptions("Record NOT Found", "Book details Not Found" + book.toString());
       return bookList.replace(bookId,book);
    }


    public Book delete(Integer bookId) {
        if (bookList.isEmpty() || null == bookList.get(bookId)) throw new LibraryRuntimeExceptions("Record NOT Found", "Book details Not Found with ID" + bookId);
        return bookList.remove(bookId);
    }


    public boolean deleteAll() {
        if (bookList.isEmpty()) {throw new LibraryRuntimeExceptions("No Records found", "Library don't have any books" );
        }
        else{
            bookList.clear();
            return true;
        }
    }

    @ExceptionHandler(LibraryRuntimeExceptions.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleExceptions(Exception e){
        return e.getMessage();
    }

    @ExceptionHandler(BookAddException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleAddExceptions(Exception e){
        return e.getMessage();
    }

    @Override
    public Integer count() {
        return bookList.size();
    }
}
