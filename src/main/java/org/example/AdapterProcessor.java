package org.example;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.reactivestreams.Processor;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AdapterProcessor implements Processor<String, String> {
    CompletableFuture<Subscriber<? super String>> subscriber = new CompletableFuture<>();
    @Override
    public void subscribe(Subscriber<? super String> s) {
        this.subscriber.complete(s);
    }

    @Override
    @SneakyThrows
    public void onSubscribe(Subscription s) {
        subscriber.get()
                .onSubscribe(s);
    }

    @Override
    @SneakyThrows
    public void onNext(String string) {
        subscriber.get()
                .onNext(string);
    }

    @Override
    @SneakyThrows
    public void onError(Throwable t) {
        subscriber.get()
                .onError(t);
    }

    @Override
    @SneakyThrows
    public void onComplete() {
        subscriber.get()
                .onComplete();
    }
}
