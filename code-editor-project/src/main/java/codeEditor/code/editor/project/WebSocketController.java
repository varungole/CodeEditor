package codeEditor.code.editor.project;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    @MessageMapping("/edit/{uniqueID}")
    @SendTo("/topic/editor/{uniqueID}")
    public String broadcastEdit(@DestinationVariable String uniqueID, String code) {
        return code;
    }
}
