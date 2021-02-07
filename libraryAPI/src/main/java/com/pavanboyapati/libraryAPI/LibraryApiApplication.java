package com.pavanboyapati.libraryAPI;

import com.pavanboyapati.libraryAPI.Utility.DataHelper;
import com.pavanboyapati.libraryAPI.domain.Book;
import com.pavanboyapati.libraryAPI.libraryExceptions.BookAddException;
import com.pavanboyapati.libraryAPI.services.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.MediaTypeNotSupportedStatusException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@SpringBootApplication
@RestController
@RequestMapping(path = "/libraryAPi", produces =MediaType.APPLICATION_JSON_VALUE)
public class LibraryApiApplication implements CommandLineRunner {

	LibraryService libraryService;
	public LibraryService getLibraryService() {
		return libraryService;
	}
	@Autowired
	public void setLibraryService(LibraryService libraryService) {
		this.libraryService = libraryService;
	}

	@RequestMapping(value = { "/getAllBook","/{bookID}"},method= RequestMethod.GET)
	public ResponseEntity<List<Book>> getAllBooksInLibrary(@PathVariable(required = false) Integer bookID){
		ResponseEntity<List<Book>> responseEntity = null;
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		if(null != bookID){
			Book book = getLibraryService().findById(bookID);
			List<Book>  singleBook = new ArrayList<>();
			singleBook.add(book);
			return responseEntity = new ResponseEntity<>(singleBook,headers, HttpStatus.OK);
		}
		List<Book> bookList = getLibraryService().getAllBooks();
		for (Book book : bookList) {
			Integer bookIDtoLink = book.getBookID();
			Link selfLink = linkTo(LibraryApiApplication.class).slash(bookIDtoLink).withSelfRel();
			if(!book.hasLinks())
				book.add(selfLink);
		}
		return responseEntity = new ResponseEntity<>(bookList,headers, HttpStatus.OK);
	}

	@RequestMapping(method= RequestMethod.POST)
	public ResponseEntity<List<Book>> getAllBooksInLibrary(@RequestBody Book newBook) throws MediaTypeNotSupportedStatusException{
		ResponseEntity<List<Book>> responseEntity = null;
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		if(null != newBook){
			getLibraryService().addNewBook(newBook);
			List<Book>  singleBook = new ArrayList<>();
			Integer bookIDtoLink = newBook.getBookID();
			Link selfLink = linkTo(LibraryApiApplication.class).slash(bookIDtoLink).withSelfRel();
			newBook.add(selfLink);
			singleBook.add(newBook);
			return responseEntity = new ResponseEntity<>(singleBook,headers, HttpStatus.OK);
		}
		List<Book> bookList = getLibraryService().getAllBooks();
		return responseEntity = new ResponseEntity<>(bookList,headers, HttpStatus.OK);
	}

	@RequestMapping(method= RequestMethod.PUT)
	public ResponseEntity<List<Book>> updateExistingBokDetails(@RequestBody Book newBook) throws MediaTypeNotSupportedStatusException{
		ResponseEntity<List<Book>> responseEntity = null;
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		if(null != newBook){
			Book book = getLibraryService().save(newBook.getBookID(), newBook);
			List<Book>  singleBook = new ArrayList<>();
			singleBook.add(book);
			return responseEntity = new ResponseEntity<>(singleBook,headers, HttpStatus.OK);
		}
		List<Book> bookList = getLibraryService().getAllBooks();
		return responseEntity = new ResponseEntity<>(bookList,headers, HttpStatus.OK);
	}

	@RequestMapping(method= RequestMethod.DELETE, value = { "/{bookID}"})
	public ResponseEntity<List<Book>> deleteABook(@PathVariable Integer bookID){
		ResponseEntity<List<Book>> responseEntity = null;
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		if(null != bookID){
			Book book = getLibraryService().deleteBook(bookID);
			List<Book>  singleBook = new ArrayList<>();
			singleBook.add(book);
			return responseEntity = new ResponseEntity<>(singleBook,headers, HttpStatus.OK);
		}
		List<Book> bookList = getLibraryService().getAllBooks();
		return responseEntity = new ResponseEntity<>(bookList,headers, HttpStatus.OK);
	}


	public static void main(String[] args) {
		SpringApplication.run(LibraryApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		DataHelper dataLoad = new DataHelper();
	}

	@ExceptionHandler(MediaTypeNotSupportedStatusException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handleMediaTypeExceptions(Exception e){
		return e.getMessage();
	}
}
