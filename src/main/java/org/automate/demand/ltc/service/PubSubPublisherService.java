package org.automate.demand.ltc.service;

import com.google.cloud.pubsub.v1.Publisher;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.PubsubMessage;
import com.google.pubsub.v1.TopicName;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@Service
public class PubSubPublisherService {

    @Value("${gcp.projectId}")
    private String projectId;

    @Value("${gcp.pubsub.topicId}")
    private String topicId;

    public void publishMessage(String message) throws IOException, ExecutionException, InterruptedException {
        TopicName topicName = TopicName.of(projectId, topicId);
        Publisher publisher = null;

        try {
            publisher = Publisher.newBuilder(topicName).build();

            ByteString data = ByteString.copyFromUtf8(message);
            PubsubMessage pubsubMessage = PubsubMessage.newBuilder()
                    .setData(data)
                    .build();

            publisher.publish(pubsubMessage).get();
            System.out.println("Message published: " + message);
        } finally {
            if (publisher != null) {
                publisher.shutdown();
            }
        }
    }
}
