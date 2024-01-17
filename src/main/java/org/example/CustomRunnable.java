package org.example;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.reactivestreams.Subscriber;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class CustomRunnable implements Runnable {
    Subscriber<String> subscriber;

    @Override
    public void run() {
        System.out.println("Starting");
        for (int i = 0; i < 100; i++) {
            subscriber.onNext(
                    Integer.toString(i)
            );
        }
        subscriber.onComplete();
        System.out.println("Completed");
    }
}