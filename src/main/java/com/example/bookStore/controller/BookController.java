package com.example.bookStore.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bookStore.model.Book;
import com.example.bookStore.service.BookService;

@RestController
@RequestMapping("/api/books")
public class BookController 
{
	private BookService bookService;
	
	public BookController(BookService bookService)
	{
		this.bookService=bookService;
	}
	
	@GetMapping
	public List<Book> getAllBooks()
	{
		return bookService.getAllBooks();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable Long id)
	{
		return bookService.getBookById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public Book createBook(@RequestBody Book book)
	{
		return bookService.createBook(book);
	}
	
	@PutMapping
	public ResponseEntity<Book> updateBook(@PathVariable Long id, Book book)
	{
		try
		{
			return ResponseEntity.ok(bookService.updateBook(id,  book));
		}
		
		catch(RuntimeException e)
		{
			return ResponseEntity.notFound().build();
		}
	}
}
