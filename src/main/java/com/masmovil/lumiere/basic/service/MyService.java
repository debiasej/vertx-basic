package com.masmovil.lumiere.basic.service;

import com.masmovil.lumiere.basic.domain.Dummy;
import com.masmovil.lumiere.basic.domain.DummyOut;

public interface MyService {

  DummyOut process(Dummy dummy) throws InterruptedException;

}
