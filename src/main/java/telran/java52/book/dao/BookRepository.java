package telran.java52.book.dao;

import java.util.Optional;

import telran.java52.book.model.Book;

public interface BookRepository{
//	Stream<Book> findByAuthorsNameIgnoreCase(String name);
//
//	Stream<Book> findByPublisherPublisherNameIgnoreCase(String publisherName);
	
	void deleteByAuthorsName(String name);

	boolean existsById(String isbn);

	Book save(Book book);

	Optional<Book> findById(String isbn);

	void deleteById(String isbn);
}
