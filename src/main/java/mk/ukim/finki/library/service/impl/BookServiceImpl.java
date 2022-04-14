package mk.ukim.finki.library.service.impl;

import mk.ukim.finki.library.model.Author;
import mk.ukim.finki.library.model.Book;
import mk.ukim.finki.library.model.dto.BookDto;
import mk.ukim.finki.library.model.exception.AuthorNotFoundException;
import mk.ukim.finki.library.model.exception.BookNotFoundException;
import mk.ukim.finki.library.model.exception.BookOutOfStockException;
import mk.ukim.finki.library.repository.BookRepository;
import mk.ukim.finki.library.service.AuthorService;
import mk.ukim.finki.library.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;

    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }

    @Override
    public List<Book> findAllWithoutPagination() {
        return this.bookRepository.findAll();
    }

    @Override
    public Page<Book> findAll(Pageable pageable) {
        return this.bookRepository.findAll(pageable);
    }

    @Override
    public Optional<Book> findById(Long id) {
        return this.bookRepository.findById(id);
    }

    @Override
    public Optional<Book> create(BookDto bookDto) {
        Author author = this.authorService.findById(bookDto.getAuthorId())
                .orElseThrow(() -> new AuthorNotFoundException(bookDto.getAuthorId()));
        Book book = new Book(bookDto.getName(), bookDto.getCategory(), author, bookDto.getAvailableCopies());
        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public Optional<Book> update(Long id, BookDto bookDto) {
        Book book = this.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        Author author = this.authorService.findById(bookDto.getAuthorId())
                .orElseThrow(() -> new AuthorNotFoundException(bookDto.getAuthorId()));
        book.setName(bookDto.getName());
        book.setCategory(bookDto.getCategory());
        book.setAuthor(author);
        book.setAvailableCopies(bookDto.getAvailableCopies());
        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public Optional<Book> delete(Long id) {
        Book book = this.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        this.bookRepository.delete(book);
        if (this.bookRepository.findById(id).isEmpty())
            return Optional.of(book);
        return Optional.empty();
    }

    @Override
    public Optional<Book> markAsTaken(Long id) {
        Book book = this.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        if (book.getAvailableCopies() <= 0)
            throw new BookOutOfStockException(book.getName());
        book.setAvailableCopies(book.getAvailableCopies() - 1);
        return Optional.of(this.bookRepository.save(book));
    }
}
