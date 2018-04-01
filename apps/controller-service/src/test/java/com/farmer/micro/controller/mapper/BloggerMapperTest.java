package com.farmer.micro.controller.mapper;

import com.farmer.micro.controller.entity.BloggerEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BloggerMapperTest {


    @Autowired
    private BloggerMapper bloggerMapper;


    @Test
    public void test() {

        Example example = new Example(BloggerEntity.class);
        Example.Criteria criteria = example.createCriteria();

        criteria.andEqualTo("bloggerName","happysboy");
        List<BloggerEntity> bloggerEntities = bloggerMapper.selectByExample(example);

        System.out.println(bloggerEntities.size());
    }

    @Test
    public void testSelect() {

        BloggerEntity bloggerEntity = bloggerMapper.selectByPrimaryKey(202);

        System.out.println(bloggerEntity.getBloggerName());
    }
}
