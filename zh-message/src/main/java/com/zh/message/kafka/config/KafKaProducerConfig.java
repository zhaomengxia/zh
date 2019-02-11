package com.zh.message.kafka.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.HashMap;
import java.util.Map;

/**
 * kafka生产者配置类
 *
 * @author  hahaha
 * @since 2018-11-16 11:07

 **/
@Configuration
@EnableKafka
@EnableAsync
public class KafKaProducerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String kafkaServer;

    @Bean
    public ProducerFactory<String, String> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>(16);
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        // 如果请求失败，生产者会自动重试，我们指定是0次，如果启用重试，则会有重复消息的可能性
        props.put(ProducerConfig.RETRIES_CONFIG, 0);
        // Request发送请求，即Batch批处理，以减少请求次数，该值即为每次批处理的大小
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 4096);
        /**
         * 这将指示生产者发送请求之前等待一段时间，希望更多的消息填补到未满的批中。这类似于TCP的算法，例如上面的代码段，
         * 可能100条消息在一个请求发送，因为我们设置了linger(逗留)时间为1毫秒，然后，如果我们没有填满缓冲区，
         * 这个设置将增加1毫秒的延迟请求以等待更多的消息。 需要注意的是，在高负载下，相近的时间一般也会组成批，即使是
         * linger.ms=0。在不处于高负载的情况下，如果设置比0大，以少量的延迟代价换取更少的，更有效的请求。
         */
        props.put(ProducerConfig.LINGER_MS_CONFIG, 2000);

        /**
         * 控制生产者可用的缓存总量，如果消息发送速度比其传输到服务器的快，将会耗尽这个缓存空间。
         * 当缓存空间耗尽，其他发送调用将被阻塞，阻塞时间的阈值通过max.block.ms设定， 之后它将抛出一个TimeoutException。
         */
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 40960);

        // 用于配置send数据或partitionFor函数得到对应的leader时，最大的等待时间，默认值为60秒
        // HH 警告：如无法连接 kafka 会导致程序卡住，尽量不要设置等待太久
        props.put(ProducerConfig.MAX_BLOCK_MS_CONFIG, 100);


        // 消息发送的最长等待时间
        props.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, 100);

        // 0：不保证消息的到达确认，只管发送，低延迟但是会出现消息的丢失，在某个server失败的情况下，有点像TCP
        // 1：发送消息，并会等待leader 收到确认后，一定的可靠性
        // -1：发送消息，等待leader收到确认，并进行复制操作后，才返回，最高的可靠性
        props.put(ProducerConfig.ACKS_CONFIG, "0");


        // See https://kafka.apache.org/documentation/#producerconfigs for more properties
        return props;
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
