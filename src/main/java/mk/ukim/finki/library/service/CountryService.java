package mk.ukim.finki.library.service;

import mk.ukim.finki.library.model.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CountryService {
    Page<Country> findAll(Pageable pageable);

    Optional<Country> findById(Long id);

    Optional<Country> save(String name, String continent);
}
