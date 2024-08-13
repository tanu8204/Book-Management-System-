package com.incapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.incapp.beans.Book;
import com.incapp.repo.BookRepo;
import com.incapp.service.BookService;

@RestController
public class BookController {
	
	@Autowired
	BookService service;
	
	@RequestMapping("/")
	public String homePage() {
		return "Welcome to Book RESTful web service";
	}
	
//	@PostMapping(value = "/book",consumes = {"application/xml","application/json"})//support XML and JSON both
//	@PostMapping(value = "/book",consumes = {"application/xml"}) //only XML support
//	@PostMapping(value = "/book",consumes = {"application/json"}) //only JSON support
	@PostMapping(value = "/book") //by default consumes is JSON and XML both
	public String addBook(@RequestBody Book b) {
		return service.addBook(b);
	}
	@PostMapping(value = "/book_img") 
	public String addBook(@RequestPart("book") Book b,@RequestPart("photo") MultipartFile photo) {
		return service.addBook(b,photo);
	}
	
	@GetMapping("/book/{name}")
	public Book getBook(@PathVariable("name") String name) {
		return service.getBook(name);
	}
	
	@GetMapping("/books/{name}")
	public List<Book> getBooks(@PathVariable("name") String name) {
		return service.getBooks(name);
	}
	
//	@GetMapping("/books") //by default produce is JSON
//	@RequestMapping(value = "/books", method = RequestMethod.GET)
	@GetMapping(value = "/books",produces = {"application/json","application/xml"})
//	@GetMapping(value = "/books",produces = {"application/xml"}) //only XML support
//	@GetMapping(value = "/books",produces = {"application/json"}) //only JSON support
//	@GetMapping(value = "/books")//by default produce is JSON
	public List<Book> getBooks() {
		return service.getBooks();
	}
	
	@GetMapping("/bookPhoto/{name}")
	public byte[] getBookImage(@PathVariable("name") String name) {
		return service.getBookImage(name);
	}
}
