package com.javen.beans;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@Data
public class User {

    //主键递增的设置
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private Integer age;
    private String email;

    //字段填充的设置
    @TableField(fill = FieldFill.INSERT)
    private Date cTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date uTime;

    @Version//乐观锁的注解的处理
    private Long version;

}
