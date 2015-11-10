package vcreature.morphology;

import vcreature.Environment;
import vcreature.morphology.strategies.CrossoverStrategy;
import vcreature.morphology.strategies.GeneticStrategy;
import vcreature.utils.Statistics;

/**
 * This class evolves a population of beings using a genetic algorithm (GA). The algorithms main functions are:
 * fitness calculation; selection; crossover; and mutation
 * @author Javier Chavez
 * @author Alex Baker
 * @author Dominic Salas
 * @author Cari Martinez
 */

public class GeneticAlgorithm
{
  private Environment simulation;
  private GeneticStrategy ga;

  /**
   *
   * @param simulation reference to physics environment
   */
  public GeneticAlgorithm(Environment simulation, Statistics statistics)
  {
    this.simulation = simulation;
    ga = new CrossoverStrategy(simulation, statistics);
  }




}


