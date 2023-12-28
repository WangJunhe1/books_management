package com.bookManage.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author :Wjh
 * @since :2023/11/28 11:52
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterDTO {
    private String username;

    private String password;

    private String phone;

    @Builder.Default
    private String studentNumber = null;
}
