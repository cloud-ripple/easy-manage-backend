package com.bobo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author boboking
 * @date 2023/6/5
 * @description 日志
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Log {
    private Long id;  //id 主键
    private LocalDateTime createTime; //日志操作时间
    private String description; //描述信息
}
