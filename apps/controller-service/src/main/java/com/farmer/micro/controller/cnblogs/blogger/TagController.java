package com.farmer.micro.controller.cnblogs.blogger;

import com.farmer.micro.common.api.message.blogger.tag.DownTagMessage;
import com.farmer.micro.common.api.message.util.Constants;
import com.farmer.micro.common.message.core.ActiveMqMessageSend;
import com.farmer.micro.controller.entity.BloggerEntity;
import com.farmer.micro.controller.mapper.BloggerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TagController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TagController.class);

    @Autowired
    private BloggerMapper bloggerMapper;

    @Autowired
    private ActiveMqMessageSend activeMqMessageSend;

    public void start() {

        int init = 648;

        while(true) {
            BloggerEntity bloggerEntity = bloggerMapper.selectByPrimaryKey(init);
            if (null == bloggerEntity) {
                init = init + 1;
                continue;
            }
            DownTagMessage downTagMessage = new DownTagMessage();
            downTagMessage.setBloggerName(bloggerEntity.getBloggerName());
            downTagMessage.setMessageId(UUID.randomUUID().toString());
            downTagMessage.setMessageType(Constants.MessageType.DOWN_TAG);
            activeMqMessageSend.send(downTagMessage,
                    com.farmer.micro.common.message.core.Constants.BLOGGER_TAG_DOWNLOAD_QUEUE);

            LOGGER.info("*********id : {} ***************",init);

            init = init + 1;
        }

    }
}
