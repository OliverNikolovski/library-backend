package mk.ukim.finki.library.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mk.ukim.finki.library.model.Country;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthorDto {
    private String name;
    private String surname;
    private Country country;
}
