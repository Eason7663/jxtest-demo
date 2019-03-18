package org.jiuxin.spring.demo.controller;

import org.jiuxin.spring.demo.domain.Book;
import org.jiuxin.spring.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName BookApp
 * @Description TODO
 * @User Administrator
 * @Date 2019/3/6 23:24
 * @Version 1.0
 **/
//@RestController
@Controller
@RequestMapping("/api/v1")
public class BookApp {

    @Autowired
    private BookService bookService;

    /**
     * 获取读书清单列表
     * @return
     */
    @GetMapping("/books")
    public List<Book> getAll(){
        return bookService.findAll();
    }

    @PostMapping("/books")
    public Book post(@RequestParam String name,
                     @RequestParam String author,
                     @RequestParam String description,
                     @RequestParam int status){
        Book book = new Book();
        book.setName(name);
        book.setDescription(description);
        book.setStatus(status);
        book.setAuthor(author);
        return bookService.save(book);
    }

    /**
     * 获取一条书单信息
     * @param id
     * @return
     */
    @GetMapping("/books/{id}")
    public Book getOne(@PathVariable Long id){
        return bookService.findOne(id);
    }
}
