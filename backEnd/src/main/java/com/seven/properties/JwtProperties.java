package com.seven.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author :Wjh
 * @since :2023/11/28 12:18
 */

@Component
@ConfigurationProperties(prefix = "books.jwt")
@Data
public class JwtProperties {
    /**
     * 管理端员工生成jwt令牌相关配置
     */
    private String adminSecretKey;
    private long adminTtl;
    private String adminTokenName;

}
