package com.bookManage;

import com.bookManage.service.CommentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
class BackEndApplicationTests {
    @Autowired
    private CommentService c;


    @Test
    public void test2 () {
        c.getCommentByUserId(1);
    }

    @Test
    public void test3 () {
        c.selectInfoByBookId(111);
    }
    @Test
    public void test4 () {
        c.selectInfoByBookId(111);
    }

    @Test
    public void test5 () {

    }
}
