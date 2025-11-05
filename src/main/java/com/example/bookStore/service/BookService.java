package com.example.bookStore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.bookStore.model.Book;
import com.example.bookStore.repository.BookRepository;

@Service
public class BookService
{
	private final BookRepository bookRepository;
	
	public BookService(BookRepository bookRepository)
	{
		this.bookRepository = bookRepository;
	}
	
	public List<Book> getAllBooks()
	{
		return bookRepository.findAll();
	}
	
	public Optional<Book> getBookById(Long id)
	{
		return bookRepository.findById(id);
	}
	
	public Book createBook(Book book)
	{
		return bookRepository.save(book);
	}
	
	public Book updateBook(Long id, Book bookDetails) {
	    return bookRepository.findById(id)
	        .map(book -> {
	            book.setTitle(bookDetails.getTitle());
	            book.setAuthor(bookDetails.getAuthor());
	            book.setPrice(bookDetails.getPrice());
	            book.setGenre(bookDetails.getGenre());
	            // MUST return something here:
	            return bookRepository.save(book);
	        })
	        .orElseThrow(() -> new RuntimeException("Book not found"));
	}

	public void deleteBook(Long id)
	{
		bookRepository.deleteById(id);
	}
	
}
