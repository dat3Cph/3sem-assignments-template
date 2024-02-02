package Concurrency_TaskNine;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {


    public static void main(String[] args) {

    //////////////////////////////////////////////// CompletableFuture: ///////////////////////////////////////////////////
        CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> new Task().run()).thenApply(result -> (Void) null).exceptionally(ex -> null).whenComplete((ex, e) -> Void.TYPE.cast(ex));
        CompletableFuture<Void> future2 = CompletableFuture.runAsync(() -> new Task().run()).thenApply(result -> (Void) null).exceptionally(ex -> null).whenComplete((ex, e) -> Void.TYPE.cast(ex));
        CompletableFuture<Void> future3 = CompletableFuture.runAsync(() -> new Task().run()).thenApply(result -> (Void) null).exceptionally(ex -> null).whenComplete((ex, e) -> Void.TYPE.cast(ex));
        CompletableFuture<Void> future4 = CompletableFuture.runAsync(() -> new Task().run()).thenApply(result -> (Void) null).exceptionally(ex -> null).whenComplete((ex, e) -> Void.TYPE.cast(ex));
        CompletableFuture<Void> future5 = CompletableFuture.runAsync(() -> new Task().run()).thenApply(result -> (Void) null).exceptionally(ex -> null).whenComplete((ex, e) -> Void.TYPE.cast(ex));
        CompletableFuture<Void> future6 = CompletableFuture.runAsync(() -> new Task().run()).thenApply(result -> (Void) null).exceptionally(ex -> null).whenComplete((ex, e) -> Void.TYPE.cast(ex));

        CompletableFuture<Void> futures = CompletableFuture.allOf(future1, future2, future3, future4, future5, future6);
        futures.thenRun(() -> System.out.println("Complete"));

        //////////////////////////////////////////////// CompletableFuture: ///////////////////////////////////////////////////
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(() -> new Task().run());
        executorService.submit(() -> new Task().run());

        executorService.shutdown();
        System.out.println("Executed order 66");

    }
}
