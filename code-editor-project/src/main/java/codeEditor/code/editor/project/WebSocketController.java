package codeEditor.code.editor.project;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    @MessageMapping("/sendCode")
    @SendTo("/topic/receiveCode")
    public String sendCode(String code) {
        return code;
    }

}
