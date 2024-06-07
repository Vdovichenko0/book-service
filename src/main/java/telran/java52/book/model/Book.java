package telran.java52.book.model;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor //modelMapper+hibernate нужен @NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "isbn") //с какой то версии можно задавать по другому
@Entity
public class Book implements Serializable {//делаем Serializable чтобы хоть как то внедрилась в виду обьекта сериализации+ когда несколько серверов и реп на разных местах
	private static final long serialVersionUID = 1950334873767354808L;
	
	@Id
	String isbn;
	String title;
					//hibernate не знает что с ними делать если оставить как обычно authors + publisher
	@ManyToMany //говорит что нужно организовать отношения многие ко многим 
	Set<Author> authors;
	@ManyToOne
	Publisher publisher;//таблица из одного столбца 
	
	
	
	
//	Serializable — это маркерный интерфейс в Java, который указывает, что экземпляры класса могут быть преобразованы в поток байтов. Это позволяет объекты класса записывать в файл, передавать по сети, сохранять в базу данных и затем восстанавливать в их первоначальном состоянии.
//
//	Когда класс реализует интерфейс Serializable, вы можете использовать Java стандартные механизмы сериализации для преобразования объекта в байтовый поток и обратно. Это полезно для различных задач, таких как кэширование, обмен данными между различными компонентами системы или сохранение состояния объекта.
}
