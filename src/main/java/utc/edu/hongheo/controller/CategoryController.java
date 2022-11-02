package utc.edu.hongheo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import utc.edu.hongheo.model.Category;
import utc.edu.hongheo.service.ICategoryService;

import java.util.Optional;

@Controller
@CrossOrigin("*")
@RequestMapping("categories")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    @GetMapping("")
    public ResponseEntity<Iterable<Category>> findAll(){
        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id){
        Optional<Category> categoryOptional = categoryService.findById(id);
        if(!categoryOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(categoryOptional.get(), HttpStatus.OK);
    }

    @GetMapping("find-by-user/{id}")
    public ResponseEntity<Iterable<Category>> findByIdUser(@PathVariable Long id) {
        return new ResponseEntity<>(categoryService.findAllByUserId(id), HttpStatus.OK);
    }

    @GetMapping("find-by-status/{num}/{id}")
    public ResponseEntity<Iterable<Category>> findAllByStatus(@PathVariable int num, @PathVariable Long id){
        return new ResponseEntity<>(categoryService.findAllByStatus(num, id), HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<Category> save(@RequestBody Category category){
        return new ResponseEntity<>(categoryService.save(category), HttpStatus.OK);
    }

    @PutMapping("{id}")
    private ResponseEntity<Category> update(@PathVariable Long id, @RequestBody Category category){
        Optional<Category> optionalCategory = categoryService.findById(id);
        if(!optionalCategory.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        category.setId(id);
        return new ResponseEntity<>(categoryService.save(category), HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    private ResponseEntity<Category> delete(@PathVariable Long id){
        Optional<Category> optionalCategory = categoryService.findById(id);
        if(!optionalCategory.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        categoryService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
