package mk.ukim.finki.library.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mk.ukim.finki.library.model.enums.Category;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookDto {
    private String name;
    private Category category;
    private Long authorId;
    private Integer availableCopies;
}
