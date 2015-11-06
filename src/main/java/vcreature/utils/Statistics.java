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
  private float firstGenAvgFitness;

}
