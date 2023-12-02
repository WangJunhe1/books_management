package com.seven.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author wjh
 * @since 2023-12-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("book_type")
public class BookType implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "book_type_id", type = IdType.AUTO)
    private Integer bookTypeId;

    private String bookType;

    private String bookTypeLocation;

    private Integer bookNumber;
}
