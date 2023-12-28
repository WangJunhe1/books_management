package com.bookManage.properties;

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
    //密钥
    private String adminSecretKey;
    //过期时间
    private long adminTtl;
    //令牌名称
    private String adminTokenName;
}
