package org.jiuxin.spring.demo.domain;

import lombok.Data;

/**
 * @author : Eason
 * @date: 2019/3/22
 * @description:
 */
@Data
public class ResponseGw {
    public String macaddr;
    public String type;
    public String subtype;
    public String status;
    public String info;
}
