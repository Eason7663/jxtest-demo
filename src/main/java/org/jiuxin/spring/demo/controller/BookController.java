package org.jiuxin.spring.demo.controller;

import org.jiuxin.spring.demo.domain.Book;
import org.jiuxin.spring.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

/**
 * @ClassName BookController
 * @Description TODO
 * @User Administrator
 * @Date 2019/3/18 22:37
 * @Version 1.0
 **/
@Controller
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public String list(){
        return "books";
    }

    @GetMapping("/books/{id}")
    public String detail(@PathVariable long id,
                         Model model){
        Optional<Book> book = bookService.findOne(id);
        model.addAttribute("book",book);
        return "book";
    }
}
