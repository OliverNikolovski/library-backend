package mk.ukim.finki.library.service.impl;

import mk.ukim.finki.library.model.Author;
import mk.ukim.finki.library.model.Country;
import mk.ukim.finki.library.repository.AuthorRepository;
import mk.ukim.finki.library.service.AuthorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Page<Author> findAll(Pageable pageable) {
        return this.authorRepository.findAll(pageable);
    }

    @Override
    public List<Author> findAllWithoutPagination() {
        return this.authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return this.authorRepository.findById(id);
    }

    @Override
    public Optional<Author> save(String name, String surname, Country country) {
        Author author = this.authorRepository.save(new Author(name, surname, country));
        return Optional.of(author);
    }
}
