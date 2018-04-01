package com.farmer.micro.parser.cnblogs.blogger.category;

import com.farmer.micro.parser.entity.CategoryEntity;
import com.farmer.micro.parser.mapper.CategoryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryBatchDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryBatchDao.class);

    @Autowired
    private CategoryMapper categoryMapper;

    public synchronized void batchInsert(List<CategoryEntity> categoryEntities) {

        LOGGER.debug("start001");

        categoryMapper.insertList(categoryEntities);

        LOGGER.debug("start002");
    }
}
