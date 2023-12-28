package com.bookManage.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 * 图书表
 * </p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@TableName("book")
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 图书Id
     */
    @TableId(value = "book_id", type = IdType.AUTO)
    private Integer bookId;

    /**
     * 图书名称
     */
    private String bookName;

    /**
     * 图书种类的ID
     */
    private Integer bookTypeid;

    /**
     * 图书作者
     */
    private String bookAuthor;

    /**
     * 图书出版社
     */
    private String bookPublish;

    /**
     * 图书简介
     */
    private String bookDesc;

    /**
     * 图书数量
     */
    private Integer bookNumber;

    /**
     * 图书图片信息
     */
    private String bookImg;


}
