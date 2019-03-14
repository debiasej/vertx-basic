package com.masmovil.lumiere.basic.service;

import com.masmovil.lumiere.basic.domain.Dummy;
import com.masmovil.lumiere.basic.domain.DummyOut;
import com.masmovil.lumiere.basic.domain.DummyOutBuilder;

import java.util.concurrent.atomic.AtomicLong;

public class MyServiceImpl implements MyService {

  AtomicLong atomicLong = new AtomicLong(0);


  @Override
  public DummyOut process(Dummy dummy) {

    System.out.println("processssss ->" + atomicLong.addAndGet(1) + " --> " + dummy);
    return DummyOutBuilder.aDummyOut().withNameAge(dummy.getName() + dummy.getAge()).build();

  }
}
