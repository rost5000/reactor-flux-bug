package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    @DisplayName("Try to block all results in a one list")
    void doTest() {
        List<Long> result = Main.produceFlux()
                .collectList()
                .block(Duration.of(10, ChronoUnit.SECONDS));
        assertNotNull(result);
        assertEquals(100, result.size());
    }

    @Test
    @DisplayName("Try to block all results not in singleton list")
    void doTest2() {
        var result = new ArrayList<Long>();
        Main.produceFlux()
                .toIterable()
                .forEach(result::add);
        assertEquals(100, result.size());
    }

}