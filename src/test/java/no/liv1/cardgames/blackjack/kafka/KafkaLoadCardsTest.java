package no.liv1.cardgames.blackjack.kafka;

import no.liv1.cardgames.blackjack.game.Card;
import no.liv1.cardgames.blackjack.kafka.KafkaStreamsApplication;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.streams.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.utility.DockerImageName;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = KafkaStreamsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class KafkaLoadCardsTest {

    private static final KafkaContainer KAFKA = new KafkaContainer(
            DockerImageName.parse("confluentinc/cp-kafka:5.4.3"));

    private final BlockingQueue<String> output = new LinkedBlockingQueue<>();

    // other test setup
    @Test
    void givenInputMessages_whenProcessed_thenCardsIsProduced() {
        StreamsBuilder streamsBuilder = new StreamsBuilder();
        CardsProcessor.buildPipeline(streamsBuilder);
        Topology topology = streamsBuilder.build();

        try (TopologyTestDriver topologyTestDriver = new TopologyTestDriver(topology, new Properties())) {
            TestInputTopic<String, String> inputTopic = topologyTestDriver
                    .createInputTopic("input-cards-topic", new StringSerializer(), new JsonPOJOSerializer<>());

            TestOutputTopic<String, Card> outputTopic = topologyTestDriver
                    .createOutputTopic("output-cards-topic", new StringDeserializer(), new JsonPOJODeserializer<>());

            inputTopic.pipeInput("key1", cardToBytesHelper().toString());
            inputTopic.pipeInput("key2", "hello");

            assertThat(outputTopic.readKeyValuesToList())
                    .containsExactly(
                            KeyValue.pair("hello", new Card("CLUBS","6"))
                    );
        }
    }

    private byte[] cardToBytesHelper() {
        Card card = new Card("CLUBS", "6");

        try (
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(card);
            return bos.toByteArray();
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

}
