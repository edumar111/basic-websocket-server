package academy.digitallab.store.websocket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveKeyCommands;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.ReactiveStringCommands;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

//    @Bean
//    fun reactiveRedisTemplate(factory: LettuceConnectionFactory): ReactiveRedisTemplate<String, String> {
//        val serializer = Jackson2JsonRedisSerializer(String::class.java)
//        val builder = RedisSerializationContext.newSerializationContext<String, String>(StringRedisSerializer())
//        val context = builder.value(serializer).build()
//        return ReactiveRedisTemplate(factory, context)
//    }


    @Bean
    public ReactiveRedisTemplate<String, String> reactiveRedisTemplate(ReactiveRedisConnectionFactory factory) {
        StringRedisSerializer keySerializer = new StringRedisSerializer();
        Jackson2JsonRedisSerializer<String> valueSerializer = new Jackson2JsonRedisSerializer<>(String.class);
        RedisSerializationContext.RedisSerializationContextBuilder<String, String> builder = RedisSerializationContext.newSerializationContext(keySerializer);
        RedisSerializationContext<String, String> context = builder.value(valueSerializer).build();
        return new ReactiveRedisTemplate<>(factory, context);
    }
}
