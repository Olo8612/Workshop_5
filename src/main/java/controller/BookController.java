package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

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
	public Author helloAuthor() {
		return new Author("Janusz", "Biznesu", 55);
	}

	@RequestMapping("/all")
	public List<Book> getAllBooks() {
		List<Book> list = this.bookService.getList();
		return list;
	}

	@GetMapping("/{id}")
	public Book getBookFromId(@PathVariable Long id) {
		return this.bookService.getById(id);
	}

	@PostMapping("/")
	public void putBook(HttpServletRequest request) {
		 
		try {
			String bookJson = request.getReader().readLine();
			Book book = new ObjectMapper().readValue(bookJson, Book.class);
			this.bookService.add(book);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@DeleteMapping("/{id}")
	public void deleteBook(@PathVariable Long id){
		this.bookService.deleteById(id);
	}
}
