package telran.java52.book.service;

import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import telran.java52.book.dao.AuthorRepository;
import telran.java52.book.dao.BookRepository;
import telran.java52.book.dao.PublisherRepository;
import telran.java52.book.dto.AuthorDto;
import telran.java52.book.dto.BookDto;
import telran.java52.book.dto.exception.EntityNotFoundException;
import telran.java52.book.model.Author;
import telran.java52.book.model.Book;
import telran.java52.book.model.Publisher;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
	final BookRepository bookRepository;
	final AuthorRepository authorRepository;
	final PublisherRepository publisherRepository;
	final ModelMapper modelMapper;
	
	@Transactional
	@Override
	public Boolean addBook(BookDto bookDto) {
		if (bookRepository.existsById(bookDto.getIsbn())) {
			return false;
		}
		//publisher 
		//если есть нет найдем/ есил нет сохраняем
		Publisher publisher = publisherRepository.findById(bookDto.getPublisher())
				.orElse(publisherRepository.save(new Publisher(bookDto.getPublisher())));
		
		//authors from DB
		//преобразовать авторов 	если есть нет найдем/ есил нет сохраняем
		Set<Author> authors = bookDto.getAuthors().stream()
				.map(a -> authorRepository.findById(a.getName())
						.orElse(authorRepository.save(new Author(a.getName(),a.getBirthDate()))))
				.collect(Collectors.toSet());
		Book book = new Book(bookDto.getIsbn(), bookDto.getTitle(), authors, publisher);
		bookRepository.save(book);
		return true;
	}
	
	@Transactional(readOnly = true)
	@Override
	public BookDto findBookByIsbn(String isbn) {
		Book book = bookRepository.findById(isbn).orElseThrow(EntityNotFoundException::new);
		return modelMapper.map(book, BookDto.class);
	}

	@Transactional
	@Override
	public BookDto removeBook(String isbn) {
		Book book = bookRepository.findById(isbn).orElseThrow(EntityNotFoundException::new);
		bookRepository.delete(book);
		return modelMapper.map(book, BookDto.class);
	}

	@Transactional
	@Override
	public BookDto updateBookTitle(String isbn, String title) {
		Book book = bookRepository.findById(isbn).orElseThrow(EntityNotFoundException::new);
		book.setTitle(title);
		return modelMapper.map(book, BookDto.class);
	}

	@Transactional(readOnly = true)
	@Override
	public BookDto[] findBooksByAuthor(String author) {
		return bookRepository.findByAuthorsNameIgnoreCase(author)
                .map(book -> modelMapper.map(book, BookDto.class))
                .toArray(BookDto[]::new);
	}

	@Transactional(readOnly = true)
	@Override
	public BookDto[] findBooksByPublisher(String publisher) {
		return bookRepository.findByPublisher_PublisherNameIgnoreCase(publisher)
                .map(book -> modelMapper.map(book, BookDto.class))
                .toArray(BookDto[]::new);
	}

	@Transactional(readOnly = true)
	@Override
	public AuthorDto[] findBooksAuthors(String isbn) {
		Book book = bookRepository.findById(isbn).orElseThrow(EntityNotFoundException::new);
		 Set<Author> authors = book.getAuthors();
		 return authors.stream()
		            .map(author -> modelMapper.map(author, AuthorDto.class))
		            .toArray(AuthorDto[]::new);
	}
//помогли сделать
	@Transactional(readOnly = true)
	@Override
	public Set<String> findPublishersByAuthor(String author) {
		return bookRepository.findByAuthorsNameIgnoreCase(author)
				 .map(Book::getPublisher) // Получаем издателя для каждой книги
				    .map(Publisher::getPublisherName) // Получаем имя издателя для каждого издателя
				    .collect(Collectors.toSet()); // Собираем результат в множество
	}

	@Transactional
	@Override // not working
	public AuthorDto removeAuthor(String author) {
		Author authorEntity = authorRepository.findById(author).orElseThrow(EntityNotFoundException::new);
		authorRepository.delete(authorEntity);
		return modelMapper.map(authorEntity, AuthorDto.class);
	}

}
