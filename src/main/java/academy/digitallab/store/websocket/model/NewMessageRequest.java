package academy.digitallab.store.websocket.model;

import lombok.Data;

@Data
public class NewMessageRequest {
    private String topic;
    private String message;

}
