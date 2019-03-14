package com.masmovil.lumiere.basic.service;

import com.masmovil.lumiere.basic.domain.Dummy;
import com.masmovil.lumiere.basic.domain.DummyBuilder;
import com.masmovil.lumiere.basic.domain.DummyOut;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;

public class MyServiceVerticle extends AbstractVerticle {


  MyServiceImpl myService = new MyServiceImpl();

  @Override
  public void start() throws Exception {



    vertx.eventBus().<JsonObject>localConsumer("dummy", msg -> {

      DummyOut process = this.myService.process(DummyBuilder.aDummy().withAge(msg.body().getInteger("age")).withName(msg.body().getString("name")).build());
      msg.reply(JsonObject.mapFrom(process));
    });
  }

  @Override
  public void stop() throws Exception {
    super.stop();
  }
}
