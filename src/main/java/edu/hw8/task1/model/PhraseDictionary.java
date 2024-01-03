package edu.hw8.task1.model;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PhraseDictionary extends ConcurrentHashMap<String, String> {
    public PhraseDictionary() {
        super(
            Map.of(
                "личности",
                "Не переходи на личности там, где их нет",
                "оскорбления",
                "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами",
                "глупый",
                "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма.",
                "интеллект",
                "Чем ниже интеллект, тем громче оскорбления"
            )
        );
    }
}
