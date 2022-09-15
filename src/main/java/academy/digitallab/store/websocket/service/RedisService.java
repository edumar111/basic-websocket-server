package academy.digitallab.store.websocket.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.ReactiveSubscription;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;


import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
@Slf4j
public class RedisService {

    private final ReactiveRedisTemplate<String, String> reactiveRedisTemplate;
    private final SimpMessagingTemplate websocketTemplate;


    public void publish( String topic ,String message ) {
        reactiveRedisTemplate.convertAndSend(topic, message).subscribe();
    }

    public void subscribe(String channelTopic , String destination ) {
        reactiveRedisTemplate.listenTo(ChannelTopic.of(channelTopic))
                .map(ReactiveSubscription.Message<String, String>::getMessage)
                .subscribe ( message ->
                websocketTemplate.convertAndSend(destination, message)
                );
    }

    @PostConstruct
    public void subscribe() {
        subscribe("GREETING_CHANNEL_INBOUND", "/topic/greetings");
    }
}
