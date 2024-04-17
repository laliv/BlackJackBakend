package no.liv1.cardgames.blackjack.kafka;

import no.liv1.cardgames.blackjack.game.Card;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class CardsProcessor {

    private static final Serde<String> STRING_SERDE = Serdes.String();

    private static final Serializer<Card> cardSerializer = new JsonPOJOSerializer<>();
    private static final Deserializer<Card> cardDeserializer = new JsonPOJODeserializer<>();
    private static final Serde<Card> CARD_SERDE = Serdes.serdeFrom(cardSerializer, cardDeserializer);

    @Autowired
    static void buildPipeline(StreamsBuilder streamsBuilder) {
        KStream<String, Card> messageStream = streamsBuilder
                .stream("input-cards-topic", Consumed.with(STRING_SERDE, CARD_SERDE));

        KStream<String, Card> cardsStream = messageStream;
               // .mapValues( value -> );
                //.flatMapValues(value -> Arrays.asList(value.split("\\W+")))
                //.groupBy((key, word) -> word, Grouped.with(STRING_SERDE, STRING_SERDE))



        cardsStream.to("output-cards-topic");
    }
}
