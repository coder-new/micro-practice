package com.farmer.micro.parser.cnblogs.blogger.category;

import com.farmer.micro.parser.entity.CategoryEntity;
import com.farmer.micro.parser.util.Constants;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class ParserTagAndCategory {

    private static final Logger LOGGER = LoggerFactory.getLogger(ParserTagAndCategory.class);

    @Autowired
    private CategoryBatchDao categoryBatchDao;

    public void parser(File file,String bloggerName) {

        try {
            Document doc = Jsoup.parse(file,"UTF-8");

            parserTag(doc,bloggerName);

        } catch (IOException e) {

            LOGGER.error("parser file error : {}",e.getMessage());
        }
    }

    public void parser(String content,String bloggerName) {

        Document doc = Jsoup.parse(content);
        parserTag(doc,bloggerName);
    }

    private void parserTag(Document doc,String bloggerName) {

        Element element = doc.getElementById("MyTag1_dtTagList");
        if (null == element) {
            return;
        }
        Elements elements = element.select("a");
        List<String> urlList = elements.stream().map(ele -> decode(ele.attr("href"))).collect(toList());

        List<CategoryEntity> categoryEntities = urlList.stream().map(url -> {
            int index = url.substring(0,url.length() - 1).lastIndexOf("/");
            String name = url.substring(index + 1,url.length() - 1);
            return generateEntity(url,0,Constants.Category.TAG,name,bloggerName);
        }).collect(toList());

        categoryBatchDao.batchInsert(categoryEntities);
    }

    private CategoryEntity generateEntity(String categoryUrl,int categorySum,String categoryType,
                                          String categoryName,String bloggerName) {

        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setCategoryName(categoryName);
        categoryEntity.setCategoryNumber(categorySum);
        categoryEntity.setCategoryType(categoryType);
        categoryEntity.setCategoryUrl(categoryUrl);
        categoryEntity.setBloggerName(bloggerName);
        categoryEntity.setCreateAt(new Date());
        categoryEntity.setUpdateAt(new Date());

        return categoryEntity;
    }

    private String decode(String url) {

        try {
            return URLDecoder.decode(url,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("decode error : {}",e.getMessage());
        }

        return "";
    }

}
