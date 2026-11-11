package psu.se411.lab13.model;
import org.springframework.data.annotation.Id;
public record Product(	@Id Integer id,
		String name,
		String description,
		Double price
) {

}
