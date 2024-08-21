package com.yexuhang.internship;

import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.yexuhang.internship.bean.User;
import com.yexuhang.internship.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SuppressWarnings("ALL")
@SpringBootTest(classes = InternshipApplication.class)
public class InternshipApplicationTests {

        @Autowired
        private UserMapper userMapper;

        @Test
        public void testSelect() {
            System.out.println(("----- selectAll method test ------"));
            List<User> userList = userMapper.selectList(null);
            Assert.notNull(userList, "userList is null");
            userList.forEach(System.out::println);
        }
}
