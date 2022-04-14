package mk.ukim.finki.library.service;

import mk.ukim.finki.library.model.Book;
import mk.ukim.finki.library.model.dto.BookDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface BookService {
    List<Book> findAllWithoutPagination();

    Page<Book> findAll(Pageable pageable);

    Optional<Book> findById(Long id);

    Optional<Book> create(BookDto bookDto);

    Optional<Book> update(Long id, BookDto bookDto);

    Optional<Book> delete(Long id);

    Optional<Book> markAsTaken(Long id);
}
