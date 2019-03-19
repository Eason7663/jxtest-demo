package org.jiuxin.spring.demo.domain;

import lombok.Data;

/**
 * @author : Eason
 * @date: 2019/3/19
 * @description:
 */
@Data
public class Progress {
    private long bytesRead; //已读取文件的比特数
    private long contentLength;//文件总比特数
    private long items; //正读的第几个文件
}