package com.masmovil.lumiere.basic.domain;

public final class DummyBuilder {
  private String name;
  private int age;

  private DummyBuilder() {
  }

  public static DummyBuilder aDummy() {
    return new DummyBuilder();
  }

  public DummyBuilder withName(String name) {
    this.name = name;
    return this;
  }

  public DummyBuilder withAge(int age) {
    this.age = age;
    return this;
  }

  public Dummy build() {
    Dummy dummy = new Dummy();
    dummy.setName(name);
    dummy.setAge(age);
    return dummy;
  }
}
