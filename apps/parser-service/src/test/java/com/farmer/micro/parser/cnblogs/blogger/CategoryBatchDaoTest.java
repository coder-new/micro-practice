package com.farmer.micro.parser.cnblogs.blogger;

import com.farmer.micro.parser.cnblogs.blogger.category.CategoryBatchDao;
import com.farmer.micro.parser.entity.CategoryEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.config.ExecutorBeanDefinitionParser;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryBatchDaoTest {

    @Autowired
    private CategoryBatchDao categoryBatchDao;


    @Test
    public void test() {

        List<CategoryEntity> categoryEntities1 = new ArrayList<>();
        CategoryEntity categoryEntity1 = new CategoryEntity();
        categoryEntity1.setCategoryName("test001");
        categoryEntity1.setCategoryNumber(1);
        categoryEntity1.setCategoryType("testType");
        categoryEntity1.setCategoryUrl("http://");
        categoryEntity1.setCreateAt(new Date());
        categoryEntity1.setUpdateAt(new Date());
        categoryEntities1.add(categoryEntity1);

        List<CategoryEntity> categoryEntities2 = new ArrayList<>();
        CategoryEntity categoryEntity2 = new CategoryEntity();
        categoryEntity2.setCategoryName("test002");
        categoryEntity2.setCategoryNumber(5);
        categoryEntity2.setCategoryType("testType");
        categoryEntity2.setCategoryUrl("http://");
        categoryEntity2.setCreateAt(new Date());
        categoryEntity2.setUpdateAt(new Date());
        categoryEntities2.add(categoryEntity2);

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(() -> categoryBatchDao.batchInsert(categoryEntities1));

        executorService.submit(() -> categoryBatchDao.batchInsert(categoryEntities2));


        try {
            Thread.sleep(5*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
