package edu.auburn.sigma.breeders;

import java.math.BigInteger;

public class BinaryEncoder implements Encoder {

  private long multiplier = 0;
  private int numBits = 0;

  public BinaryEncoder(double maxValue, int decimalCount) {
    this.multiplier = (long) Math.floor(Math.pow(10, decimalCount));
    numBits = 0;
    for(; 1 << numBits < maxValue*multiplier; numBits++);
  }
  
  @Override
  public BigInteger encode(Double d) {
    
    return BigInteger.valueOf((int)(multiplier * d));
    
  }

  public int bitsInEncoding() {
    return numBits;
  }

  public Double decode(BigInteger child) {
    return child.doubleValue() / multiplier;
  }
}
