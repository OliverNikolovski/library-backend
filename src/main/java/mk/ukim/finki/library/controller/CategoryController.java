package mk.ukim.finki.library.controller;

import mk.ukim.finki.library.model.enums.Category;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
//@CrossOrigin({"http://localhost:3000", "http://127.0.0.1:3000", "http://192.168.0.30:3000/"})
@CrossOrigin("*")
public class CategoryController {

    @GetMapping
    public List<Category> listCategories() {
        return List.of(Category.values());
    }

}
