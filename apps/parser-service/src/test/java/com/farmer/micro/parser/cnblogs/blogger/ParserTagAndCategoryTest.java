package com.farmer.micro.parser.cnblogs.blogger;

import com.farmer.micro.parser.cnblogs.blogger.category.ParserTagAndCategory;
import com.farmer.micro.parser.util.Constants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ParserTagAndCategoryTest {


    @Autowired
    private ParserTagAndCategory parserTagAndCategory;

    @Test
    public void test() {

        List<String> bloggerList = new ArrayList<>();
        bloggerList.add("happysboy");
        //bloggerList.add("NeverCtrl-C");

        Consumer<String> consumer = bloggerName
                -> parserTagAndCategory.parser(new File("C:\\Users\\aprim\\tempfile\\" + bloggerName + ".html"),bloggerName);

        bloggerList.forEach(consumer);

    }
}
