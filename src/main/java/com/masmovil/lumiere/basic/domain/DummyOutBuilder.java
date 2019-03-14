package com.masmovil.lumiere.basic.domain;

public final class DummyOutBuilder {
  private String nameAge;

  private DummyOutBuilder() {
  }

  public static DummyOutBuilder aDummyOut() {
    return new DummyOutBuilder();
  }

  public DummyOutBuilder withNameAge(String nameAge) {
    this.nameAge = nameAge;
    return this;
  }

  public DummyOut build() {
    DummyOut dummyOut = new DummyOut();
    dummyOut.setNameAge(nameAge);
    return dummyOut;
  }
}
