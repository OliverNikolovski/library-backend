package mk.ukim.finki.library.controller;

import mk.ukim.finki.library.model.Author;
import mk.ukim.finki.library.service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
//@CrossOrigin({"http://localhost:3000", "http://127.0.0.1:3000", "http://192.168.0.30:3000/"})
@CrossOrigin("*")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<Author> findAll() {
        return this.authorService.findAllWithoutPagination();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> find(@PathVariable Long id) {
        return this.authorService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
