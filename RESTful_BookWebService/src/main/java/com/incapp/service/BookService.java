package com.incapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.incapp.beans.Book;
import com.incapp.repo.BookRepo;

import jakarta.servlet.http.Part;

@Service
public class BookService {
	
	@Autowired
	BookRepo repo;
	
	public String addBook(Book b,MultipartFile image) {
		return repo.addBook(b,image);
	}
	public String addBook(Book b) {
		return repo.addBook(b);
	}
	public Book getBook(String name) {
		return repo.getBook(name);
	}
	public List<Book> getBooks(String name) {
		return repo.getBooks(name);
	}
	public List<Book> getBooks() {
		return repo.getBooks();
	}
	public byte[] getBookImage(String name) {
		return repo.getBookImage(name);
	}
}
