package com.farmer.micro.download.cnblogs.blogger.tag;

import com.farmer.micro.common.api.message.blogger.tag.DownTagMessage;
import com.farmer.micro.common.api.message.blogger.tag.ParserTagMessage;
import com.farmer.micro.common.api.message.util.Constants;
import com.farmer.micro.common.message.core.ActiveMqMessageSend;
import com.farmer.micro.download.core.CallBackExecute;
import com.farmer.micro.download.core.RestTemplateHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DownloadTagService {

    @Autowired
    private RestTemplateHttpClient restTemplateHttpClient;

    @Autowired
    private ActiveMqMessageSend activeMqMessageSend;

    public void handle(DownTagMessage downTagMessage) {

        String url = getTagUrl(downTagMessage.getBloggerName());

        String body = restTemplateHttpClient.get(url);

        if (null == body) {
            return;
        }

        ParserTagMessage parserTagMessage = new ParserTagMessage();
        parserTagMessage.setBody(body);
        parserTagMessage.setBloggerName(downTagMessage.getBloggerName());
        parserTagMessage.setMessageId(UUID.randomUUID().toString());
        parserTagMessage.setMessageType(Constants.MessageType.PARSER_TAG);
        activeMqMessageSend.send(parserTagMessage,
                com.farmer.micro.common.message.core.Constants.BLOGGER_TAG_PARSER_QUEUE);

    }

    private String getTagUrl(String bloggerName) {

        return "https://www.cnblogs.com/" + bloggerName + "/tag/";
    }
}
