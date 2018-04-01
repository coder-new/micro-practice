package com.farmer.micro.parser.api.message;

import com.farmer.micro.common.api.message.blogger.tag.ParserTagMessage;
import com.farmer.micro.common.message.core.Constants;
import com.farmer.micro.common.message.serialze.SerialzeUtil;
import com.farmer.micro.parser.cnblogs.blogger.category.ParserTagAndCategory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MessageApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageApi.class);

    @Autowired
    private SerialzeUtil serialzeUtil;


    @Autowired
    private ParserTagAndCategory parserTagAndCategory;

    @JmsListener(destination = Constants.BLOGGER_TAG_PARSER_QUEUE,id = ListenerConstants.Id.ID_3)
    public void receive(String messageStr) {

        LOGGER.debug("receive message : {}",messageStr);

        try {
            ParserTagMessage parserTagMessage
                    = serialzeUtil.serialze(messageStr,ParserTagMessage.class);

            parserTagAndCategory.parser(parserTagMessage.getBody(),parserTagMessage.getBloggerName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
