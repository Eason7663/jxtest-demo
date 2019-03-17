package org.jiuxin.spring.demo.service;

import org.jiuxin.spring.demo.domain.Book;
import org.jiuxin.spring.demo.domain.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @ClassName BookService
 * @Description TODO
 * @User Administrator
 * @Date 2019/3/6 23:22
 * @Version 1.0
 **/
@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    /**
     * 查询所有的书单列表
     * @return
     */
    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    /**
     * 新增一本书
     * @param book
     * @return
     */
    public Book save(Book book){
        return bookRepository.save(book);
    }

    /**
     *  按id获取一条书本信息
     * @param id
     * @return
     */
    public Optional<Book> findOne(Long id){
        return bookRepository.findById(id);
    }

}
