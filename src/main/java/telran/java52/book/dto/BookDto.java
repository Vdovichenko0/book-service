package telran.java52.book.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
	String isbn;
	String title;
//	"authors": [
//	            {
//	                "name": "Author1",
//	                "birthDate": "1990-05-23"
//	            }
//	        ],
	Set<AuthorDto> authors;
	String publisher;
}
