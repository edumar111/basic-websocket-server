package academy.digitallab.store.websocket.api;

import academy.digitallab.store.websocket.service.RedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@Slf4j
public class WebsocketController {

    private final RedisService redisService;

    @MessageMapping("/greet")
    public void greetMessage(@Payload String message ) {
        redisService.publish("GREETING_CHANNEL_OUTBOUND", message);
    }
}
