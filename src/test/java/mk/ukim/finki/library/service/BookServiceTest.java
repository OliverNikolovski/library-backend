package mk.ukim.finki.library.service;

import mk.ukim.finki.library.model.Author;
import mk.ukim.finki.library.model.Book;
import mk.ukim.finki.library.model.dto.BookDto;
import mk.ukim.finki.library.model.enums.Category;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    @Test
    public void testFindAllWithoutPagination() {
        List<Book> books = this.bookService.findAllWithoutPagination();
        Assert.assertEquals(28, books.size());
    }

    @Test
    public void testFindAllWithPaginationFirstPage() {
        Pageable pageable = PageRequest.of(0, 5);
        Page<Book> page = this.bookService.findAll(pageable);
        Assert.assertEquals(28, page.getTotalElements());
        Assert.assertEquals(6, page.getTotalPages());
        Assert.assertEquals(0, page.getNumber());
        Assert.assertEquals(5, page.getSize());
    }

    @Test
    public void testFindAllWithPaginationLastPage() {
        Pageable pageable = PageRequest.of(5, 5);
        Page<Book> page = this.bookService.findAll(pageable);
        Assert.assertEquals(28, page.getTotalElements());
        Assert.assertEquals(6, page.getTotalPages());
        Assert.assertEquals(5, page.getNumber());
        Assert.assertEquals(5, page.getSize());
        Assert.assertEquals(3, page.getNumberOfElements());
        Assert.assertFalse(page.hasNext());
    }

    @Test
    public void testCreate() {
        BookDto bookDto = new BookDto("My new classics book", Category.CLASSICS, 1L, 5);
        Assert.assertEquals(28, this.bookService.findAllWithoutPagination().size());
        Optional<Book> bookOptional = this.bookService.create(bookDto);
        Assert.assertTrue(bookOptional.isPresent());
        Assert.assertEquals(29, this.bookService.findAllWithoutPagination().size());
        Book book = bookOptional.get();
        Assert.assertEquals("My new classics book", book.getName());
        Assert.assertEquals(Category.CLASSICS, book.getCategory());
        Assert.assertEquals(5, book.getAvailableCopies().intValue());
        Author author = this.authorService.findById(1L).orElse(null);
        assert author != null;
        Assert.assertEquals(author.getName(), book.getAuthor().getName());
    }

    @Test
    public void testUpdate() {
        Book book = this.bookService.findById(1L).orElseThrow();
        Assert.assertEquals(28, this.bookService.findAllWithoutPagination().size());
        Assert.assertEquals("Book 1", book.getName());
        Assert.assertEquals(Category.NOVEL, book.getCategory());
        Assert.assertEquals(4, book.getAvailableCopies().intValue());
        BookDto bookDto = new BookDto();
        bookDto.setName("Changed name");
        bookDto.setCategory(Category.DRAMA);
        bookDto.setAuthorId(book.getAuthor().getId());
        bookDto.setAvailableCopies(100);
        Book updatedBook = this.bookService.update(1L, bookDto).orElseThrow();
        Assert.assertEquals(28, this.bookService.findAllWithoutPagination().size());
        Assert.assertEquals(1L, updatedBook.getId().longValue());
        Assert.assertEquals("Changed name", updatedBook.getName());
        Assert.assertEquals(Category.DRAMA, updatedBook.getCategory());
        Assert.assertEquals(100, updatedBook.getAvailableCopies().intValue());
    }

}
