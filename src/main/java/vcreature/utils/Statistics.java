package vcreature.utils;


import vcreature.Being;

public abstract class Statistics
{
  float fitnessCurrentEvolingBeing;
  double fitnessSumTotal;
  private Being currentGenBestBeing;
  private float currentGenBestFitness;
  private int populationSize;
  private int generationNumber;
  private float bestFitness;
  private Being bestBeing;
  private float currentGenAverageFitness;
  private long lifetimeOffspring;
  private long lifetimeHillClimbs;
  private long currentRejectedCreatures;
  private long currentFailedHillClimbs;
  private long lifetimeRejectedCreatures;
  private long lifetimeFailedHillClimbs;
  private int generations = 0;
  private float averageFitness = 1;

  public float getFirstGenAvgFitness()
  {
    return firstGenAvgFitness;
  }

  public void setFirstGenAvgFitness(float firstGenAvgFitness)
  {
    this.firstGenAvgFitness = firstGenAvgFitness;
  }

  public float getCurrentGenAverageFitness()
  {
    return currentGenAverageFitness;
  }

  public void setCurrentGenAverageFitness(float currentGenAverageFitness)
  {
    this.currentGenAverageFitness = currentGenAverageFitness;
  }

  public Being getBestBeing()
  {
    return bestBeing;
  }

  public void setBestBeing(Being bestBeing)
  {
    this.bestBeing = bestBeing;
  }

  public float getBestFitness()
  {
    return bestFitness;
  }

  public void setBestFitness(float bestFitness)
  {
    this.bestFitness = bestFitness;
  }

  public int getGenerationNumber()
  {
    return generationNumber;
  }

  public void setGenerationNumber(int generationNumber)
  {
    this.generationNumber = generationNumber;
  }

  public int getPopulationSize()
  {
    return populationSize;
  }

  public void setPopulationSize(int populationSize)
  {
    this.populationSize = populationSize;
  }

  public float getCurrentGenBestFitness()
  {
    return currentGenBestFitness;
  }

  public void setCurrentGenBestFitness(float currentGenBestFitness)
  {
    this.currentGenBestFitness = currentGenBestFitness;
  }

  public Being getCurrentGenBestBeing()
  {
    return currentGenBestBeing;
  }

  public void setCurrentGenBestBeing(Being currentGenBestBeing)
  {
    this.currentGenBestBeing = currentGenBestBeing;
  }

  public double getFitnessSumTotal()
  {
    return fitnessSumTotal;
  }

  public void setFitnessSumTotal(double fitnessSumTotal)
  {
    this.fitnessSumTotal = fitnessSumTotal;
  }

  public float getFitnessCurrentEvolingBeing()
  {
    return fitnessCurrentEvolingBeing;
  }

  public void setFitnessCurrentEvolingBeing(float fitnessCurrentEvolingBeing)
  {
    this.fitnessCurrentEvolingBeing = fitnessCurrentEvolingBeing;
  }

  private float firstGenAvgFitness;

}
