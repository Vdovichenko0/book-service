package telran.java52.book.dao;

import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import telran.java52.book.model.Book;

@Repository
public class BookRepositoryImpl implements BookRepository {
	@PersistenceContext
	EntityManager em;

	@Override
	public Stream<Book> findByAuthorsName(String name) {
		return em.createQuery("select b from Book b join b.authors a where a.name=?1",Book.class)
				.setParameter(1, name)
				.getResultStream();
	}

	@Override
	public Stream<Book> findByPublisherPublisherName(String publisherName) {
		return em.createQuery("select b from Book b joib b.publisher p where p.publisherName=?1",Book.class)
				.setParameter(1, publisherName)
				.getResultStream();
	}

	@Override
	public void deleteByAuthorsName(String name) {
		em.createQuery("delete from Book b where ?1 members of b.authors")
				.setParameter(1, name)
				.executeUpdate();//обновляем данные
	}

	@Override
	public boolean existsById(String isbn) {
		return em.find(Book.class, isbn) != null; //если не нал isbn такой книги
	}

	@Override
	public Book save(Book book) {
//		em.persist(book);
		em.merge(book);
		return book;
	}

	@Override
	public Optional<Book> findById(String isbn) { 
//		return Optional.empty();
//// Возвращает Optional, который содержит найденный объект или пустой Optional, если объект не найден
		return Optional.ofNullable(em.find(Book.class, isbn));
	}

	@Override
	public void deleteById(String isbn) {
//		Book book = em.find(Book.class, isbn);
//		if(book != null) {
//			em.remove(book);
//		}
		em.remove(em.find(Book.class, isbn));  //search book by isbn
	}

}