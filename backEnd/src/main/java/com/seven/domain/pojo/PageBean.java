package com.seven.domain.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author :Wjh
 * @since :2023/12/7 14:35
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageBean {
    private Long total;
    private List rows;
}
