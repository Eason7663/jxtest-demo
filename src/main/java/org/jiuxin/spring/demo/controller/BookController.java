package org.jiuxin.spring.demo.controller;

import org.jiuxin.spring.demo.domain.Book;
import org.jiuxin.spring.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

/**
 * @ClassName BookController
 * @Description 获取书单
 * @User Administrator
 * @Date 2019/3/18 22:37
 * @Version 1.0
 **/
@Controller
public class BookController {
    @Autowired
    private BookService bookService;

    /**
     * 获取书单列表
     * @param model
     * @return
     */
    @GetMapping("/books")
    public String list(Model model){
        List<Book> books = bookService.findAll();
        model.addAttribute("books",books);
        return "books";
    }

    /**
     * 获取书单详情
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/books/{id}")
    public String detail(@PathVariable long id,
                         Model model){
        Book book = bookService.findOne(id);
        if(book == null){
            book = new Book();
        }
        model.addAttribute("book",book);
        return "book";
    }
}
