package telran.java52.book.service;

import java.util.Set;

import telran.java52.book.dto.AuthorDto;
import telran.java52.book.dto.BookDto;

public interface BookService {
	Boolean addBook(BookDto bookDto);

	BookDto findBookByIsbn(String isbn);
			//TODO
	BookDto removeBook(String isbn);
	
	BookDto updateBookTitle(String isbn, String title);
	
	BookDto[] findBooksByAuthor(String author);
	
	BookDto[] findBooksByPublisher(String publisher);
	
	AuthorDto[] findBooksAuthors(String isbn);
	
	Set<String> findPublishersByAuthor(String author); //?????????????
	
	AuthorDto removeAuthor(String author);
	
	
	
}
