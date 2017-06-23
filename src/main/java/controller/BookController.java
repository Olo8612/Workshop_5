package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bean.MockBookService;
import model.Author;
import model.Book;

@RestController
@RequestMapping("/books")
public class BookController {
	
	@Autowired
	private MockBookService bookService;
	
	@RequestMapping("/hello")
	public String hello() {
		return "{hello: World}";

	}
	
	
	@RequestMapping("/helloBook")
	public Book helloBook() {
		return new Book(1L, "9788324631766", "Thiniking in Java", "Bruce Eckel", "Helion", "programming");
	}
	
	@RequestMapping("/helloAuthor")
	public Author helloAuthor(){
		return new Author("Janusz", "Biznesu", 55);
	}
	
	@RequestMapping("/all")
	public List<Book> getAllBooks(){
		return bookService.getList();
	}
}
