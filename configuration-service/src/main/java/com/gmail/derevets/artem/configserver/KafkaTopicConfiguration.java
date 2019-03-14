package com.gmail.derevets.artem.configserver;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfiguration {
    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka-cloud-1:19092");
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic topicUserUpdate() {
        return new NewTopic("user-update", 2, (short) 1);
    }

    @Bean
    public NewTopic topicUserCreate() {
        return new NewTopic("user-create", 2, (short) 1);
    }

    @Bean
    public NewTopic topicContactCreate() {
        return new NewTopic("contact-create", 2, (short) 2);
    }

    @Bean
    public NewTopic topicChatCreate() {
        return new NewTopic("chat-create", 2, (short) 2);
    }

    @Bean
    public NewTopic topicMessageCreate() {
        return new NewTopic("message-create", 2, (short) 2);
    }

}
