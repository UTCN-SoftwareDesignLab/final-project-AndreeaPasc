package com.finalprojectandreeapasc.websockets;

import com.finalprojectandreeapasc.websockets.dto.MessageDto;
import com.finalprojectandreeapasc.websockets.dto.ResponseDto;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import static com.finalprojectandreeapasc.UrlMapping.MESSAGE;
import static com.finalprojectandreeapasc.UrlMapping.SEND;

@Controller
public class MessageController {

    @MessageMapping(MESSAGE)
    @SendTo(SEND)
    public ResponseDto getMessage(final MessageDto message) throws InterruptedException {
        Thread.sleep(1000);

        return new ResponseDto("New Order has been placed!");
    }
}
