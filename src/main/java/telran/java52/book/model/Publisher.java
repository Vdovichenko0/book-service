package telran.java52.book.model;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "publisherName") 
@Entity
public class Publisher implements Serializable{
	private static final long serialVersionUID = 4328523889665632423L;
	@Id
	String publisherName;
	//указываем с каким полей доч сущ связываем  
	@OneToMany(mappedBy = "publisher") //один паблишер много книг - если другой паблишер то уже другой isbn у книги
	Set<Book> books;// не надо инициализировать это делает hivernate 
	
	public Publisher(String publisherName) {
		this.publisherName = publisherName;
	}
	
	@Override
	public String toString() { //убрали баг "publisher": "telran.java52.book.model.Publisher@e9407a10"
		return publisherName;
	}


	
}
