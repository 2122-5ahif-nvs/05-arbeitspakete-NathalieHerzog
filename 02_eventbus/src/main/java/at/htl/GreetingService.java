package at.htl;

import io.quarkus.vertx.ConsumeEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.context.ManagedExecutor;

@ApplicationScoped
public class GreetingService {
    @Inject
    ManagedExecutor executor;

    @ConsumeEvent("greeting")
    public Uni<String> consume(String name) {
        return Uni.createFrom().item(() -> name.toUpperCase()).emitOn(executor);
    }

    @ConsumeEvent
    public Uni<String> process(String name) {
        return Uni.createFrom().item(name::toUpperCase).emitOn(executor);
    }
}
