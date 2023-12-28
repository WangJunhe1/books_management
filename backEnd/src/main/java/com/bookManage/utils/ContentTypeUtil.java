package com.bookManage.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author :Wjh
 * @since :2023/12/12 12:16
 */

public class ContentTypeUtil {
    private static final Map<String, String> extensionToContentTypeMap = new HashMap<>();

    static {
        // 添加一些常见的扩展名和对应的contenttype
        extensionToContentTypeMap.put("txt", "text/plain");
        extensionToContentTypeMap.put("jpg", "image/jpeg");
        extensionToContentTypeMap.put("png", "image/png");
        // 添加其他扩展名和对应的contenttype
    }

    public static String getContentType(String extension) {
        String contentType = extensionToContentTypeMap.get(extension);
        if (contentType != null) {
            return contentType;
        }
        return "application/octet-stream";
    }
}
