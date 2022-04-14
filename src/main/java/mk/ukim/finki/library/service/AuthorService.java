package mk.ukim.finki.library.service;

import mk.ukim.finki.library.model.Author;
import mk.ukim.finki.library.model.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    Page<Author> findAll(Pageable pageable);

    List<Author> findAllWithoutPagination();

    Optional<Author> findById(Long id);

    Optional<Author> save(String name, String surname, Country country);
}
