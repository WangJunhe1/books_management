package com.seven.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author :Wjh
 * @since :2023/11/28 12:09
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfoVO {
    private String username;
    private String password;
    private String phone;
}
