package com.masmovil.lumiere.basic.rest.api;

import com.masmovil.lumiere.basic.domain.Dummy;
import com.masmovil.lumiere.basic.domain.DummyBuilder;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.binder.jvm.JvmGcMetrics;
import io.micrometer.core.instrument.binder.jvm.JvmMemoryMetrics;
import io.micrometer.core.instrument.binder.jvm.JvmThreadMetrics;
import io.micrometer.core.instrument.binder.system.ProcessorMetrics;
import io.micrometer.prometheus.PrometheusConfig;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;

public class RestVerticle extends AbstractVerticle {

  @Override
  public void start() throws Exception {

    Router router = Router.router(vertx);
    PrometheusMeterRegistry registry = new PrometheusMeterRegistry(PrometheusConfig.DEFAULT);
    Metrics.addRegistry(registry);
    JvmGcMetrics jvmGcMetrics = new JvmGcMetrics();
    JvmMemoryMetrics jvmMemoryMetrics = new JvmMemoryMetrics();
    jvmMemoryMetrics.bindTo(registry);

    ProcessorMetrics processorMetrics = new ProcessorMetrics();
    processorMetrics.bindTo(registry);

    JvmThreadMetrics jvmThreadMetrics = new JvmThreadMetrics();
    jvmThreadMetrics.bindTo(registry);

    jvmGcMetrics.bindTo(registry);
    Counter llamadas = Counter.builder("llamadas").register(registry);
    // Setup a route for metrics
    router.route("/metrics").handler(ctx -> {
      String response = registry.scrape();
      ctx.response().end(response);
    });
    router.get("/example/:name/:age").handler(ctx -> {
      String age = ctx.request().getParam("age");
      String name = ctx.request().getParam("name");
      Dummy dummy = DummyBuilder.aDummy().withAge(Integer.valueOf(age)).withName(name).build();
      llamadas.increment();

      this.vertx.eventBus().send("dummy", JsonObject.mapFrom(dummy), handler -> {
        ctx.response().end(handler.result().body().toString());
      });

    });

    vertx.createHttpServer().requestHandler(router).listen(8080);
  }

  @Override
  public void stop() throws Exception {
    super.stop();
  }

}
