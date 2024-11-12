package codeEditor.code.editor.project;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    @MessageMapping("/edit")
    @SendTo("/topic/editor")
    public String broadcastEdit(String code) {
        return code;
    }
}
