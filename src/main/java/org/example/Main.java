package org.example;

import reactor.core.publisher.Flux;

public class Main {

    public static Flux<Long> produceFlux() {
        var function = new AdapterProcessor();
        var runnable = new CustomRunnable(function);
        var thread = new Thread(runnable);

        return Flux.from(function)
                .map(Integer::parseInt)
                .map(Integer::longValue)
                .doFirst(thread::start);

    }

}