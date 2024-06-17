package telran.java52.book.model;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.CascadeType;
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
		//Параметр cascade = CascadeType.ALL определяет, что все типы каскадных операций будут применяться к связанным сущностям.
	
	//Каскадно можно не только удалять но и добавлять
	@ManyToMany(cascade = CascadeType.ALL) //говорит что нужно организовать отношения многие ко многим 
	Set<Author> authors;
	@ManyToOne(cascade = CascadeType.ALL)
	Publisher publisher;//таблица из одного столбца 

	
	
	
	
	
//		Параметр cascade определяет, какие каскадные операции должны применяться к связанным сущностям. CascadeType.ALL означает, что все типы каскадных операций (persist, merge, remove, refresh, detach) будут применяться. Это значит, что любые операции (сохранение, обновление, удаление и т.д.) выполненные с Book будут автоматически применены и к связанным Author.
	
	
//	Serializable — это маркерный интерфейс в Java, который указывает, что экземпляры класса могут быть преобразованы в поток байтов. Это позволяет объекты класса записывать в файл, передавать по сети, сохранять в базу данных и затем восстанавливать в их первоначальном состоянии.
//
//	Когда класс реализует интерфейс Serializable, вы можете использовать Java стандартные механизмы сериализации для преобразования объекта в байтовый поток и обратно. Это полезно для различных задач, таких как кэширование, обмен данными между различными компонентами системы или сохранение состояния объекта.
}
