package academy.digitallab.store.websocket.api;

import academy.digitallab.store.websocket.model.NewMessageRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/notification")
@RequiredArgsConstructor
@Slf4j
public class NotificationController {


    private final SimpMessagingTemplate template;


    @PostMapping
    public Mono<ResponseEntity<Void>> newMessage(@RequestBody NewMessageRequest request) {
        log.info("newMessage");
        template.convertAndSend(request.getTopic(), request.getMessage());
        log.info("newMessage convertAndSend");
        return Mono.just( new ResponseEntity<Void>(HttpStatus.NO_CONTENT));
    }
}
