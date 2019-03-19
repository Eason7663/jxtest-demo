package org.jiuxin.spring.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author : Eason
 * @date: 2019/3/19
 * @description:
 */
@Data
@AllArgsConstructor
public class ResponseEntity<T> {

    private boolean success;

    private String msg;

    private T body;
}
