package com.codewithhimanshu.blog.blogappapis;

import com.codewithhimanshu.blog.blogappapis.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogAppApisApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void RepoTest(){
        String className = this.userRepository.getClass().getName();
        System.out.println("Name:    " + className);

        String packageName = this.userRepository.getClass().getPackageName();
        System.out.println("package Name:        "+packageName);
    }

}
