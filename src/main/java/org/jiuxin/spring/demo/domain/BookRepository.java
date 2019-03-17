package org.jiuxin.spring.demo.domain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName BookRepository
 * @Description TODO
 * @User Administrator
 * @Date 2019/3/6 23:16
 * @Version 1.0
 **/
public interface BookRepository extends JpaRepository<Book,Long>{

}
