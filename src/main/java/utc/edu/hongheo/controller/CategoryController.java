package utc.edu.hongheo.controller;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import utc.edu.hongheo.model.Category;
import utc.edu.hongheo.service.ICategoryService;
import utc.edu.hongheo.service.Impl.CategoryService;

@Controller
@CrossOrigin("*")
@RequestMapping("categories")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    @GetMapping("get-all-category")
    public ResponseEntity<Iterable<Category>> findAll(){
        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
    }

}
