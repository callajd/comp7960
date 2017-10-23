package edu.auburn.geneticalgorithm;

import java.util.List;

public interface PopulationGenerator<T> {
  List<T> generatePopulation();
}
