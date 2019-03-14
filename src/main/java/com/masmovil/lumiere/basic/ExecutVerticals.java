package com.masmovil.lumiere.basic;

import com.masmovil.lumiere.basic.rest.api.RestVerticle;
import com.masmovil.lumiere.basic.service.MyServiceVerticle;
import io.vertx.config.ConfigRetriever;
import io.vertx.config.ConfigRetrieverOptions;
import io.vertx.config.ConfigStoreOptions;
import io.vertx.config.impl.spi.JsonConfigStore;
import io.vertx.config.impl.spi.JsonConfigStoreFactory;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

public class ExecutVerticals {

  public static void main(String[] args) throws Exception {

    final Vertx vertx = Vertx.vertx();
    ConfigStoreOptions file = new ConfigStoreOptions().setType("file").setConfig(new JsonObject().put("path", "app.conf"));

 /*   ConfigRetriever retriever = ConfigRetriever.create(vertx, new ConfigRetrieverOptions().addStore(file));
    retriever.getConfig(conf -> {
      System.out.println("puerto -->" +  conf.result().getString("port"));

    });

  */
    vertx.deployVerticle(new MyServiceVerticle());
    vertx.deployVerticle(new RestVerticle());
  }
 }
