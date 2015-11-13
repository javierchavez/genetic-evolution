package vcreature.utils;


import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import vcreature.Being;
import vcreature.collections.Population;
import vcreature.genotype.Gene;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class Statistics implements Savable
{
  private volatile Population population;
  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd-HH:mm");
  private Logger statsLogger = new Logger("population-" + formatter.format(LocalDateTime.now()) + ".txt");
  private Logger populationLogger = new Logger();



  private volatile double fitnessSumTotal = 0;
  private volatile Being bestBeing = null;
  private int initPopulation = 0;
  private int initGene = 0;
  private int initBeing = 0;
  private volatile int generationNumber = 1;
  private volatile float bestFitness = 0;
  private volatile long lifetimeHillClimbs = 0;
  private volatile float averageFitness = 0;;
  private volatile int lifetimeCrosses = 0;
  private volatile double _past = 0;
  private volatile double _current = 0;
  private volatile float tenMinCounter = 0;
  private volatile float minCounter = 0;
  private volatile long timesCalled;
  private float elapsedTime=0;
  private long startTime=0;


  public Statistics(Population population)
  {
    this.population = population;
    startTime = System.currentTimeMillis();
  }


  public float getFirstGenAvgFitness()
  {
    return firstGenAvgFitness;
  }

  public void setFirstGenAvgFitness(float firstGenAvgFitness)
  {
    this.firstGenAvgFitness = firstGenAvgFitness;
  }

  public void init()
  {
    initGene = Gene.TOTAL;
    initPopulation = initBeing = Being.TOTAL;
  }

  public Being getBestBeing()
  {
    return bestBeing;
  }

  public void setBestBeing(Being bestBeing)
  {
    this.bestBeing = bestBeing;
    this.setBestFitness(bestBeing.getFitness());
  }

  public void addFitnessToSum(float fitness)
  {
    fitnessSumTotal += fitness;
    timesCalled += 1;
  }

  public void addHillClimbToSum(int hc)
  {
    lifetimeHillClimbs += hc;
  }

  public void addCrossesToSum(int cc)
  {
    lifetimeCrosses += cc;
  }

  public void addGenerationToSum(int g)
  {
    generationNumber += g;
  }

  public float getBestFitness()
  {
    return bestFitness;
  }

  private void setBestFitness(float bestFitness)
  {
    this.bestFitness = bestFitness;
  }

  public double getAverageFitness()
  {
    float d = 0f;
    for (Being o : population.getBeings())
    {
      d += o.getFitness();
    }
    averageFitness = d / population.size();
    return averageFitness;
  }


  public int getGenerationNumber()
  {
    return generationNumber;
  }


  public int getPopulationSize()
  {
    return population.size();
  }


  public double getFitnessSumTotal()
  {
    return fitnessSumTotal;
  }


  private float firstGenAvgFitness;

  public void update(float delta)
  {
    elapsedTime += delta;
    tenMinCounter += delta;
    minCounter += delta;

    if (minCounter >= 60)
    {
      if (_past == 0)
      {
        _past = getFitnessSumTotal();
      }
      _current = getFitnessSumTotal();
      _past = (_past + _current) / timesCalled;
    }
    if (tenMinCounter >= 600.0f)
    {
      statsLogger.export(this);
      populationLogger.setFileName("genomes" + formatter.format(LocalDateTime.now()) + ".txt");
      populationLogger.export(population);
      tenMinCounter = 0;
    }

  }

  public double getAverageFitnessMin()
  {
    return _past;
  }

  @Override
  public void write(StringBuilder s)
  {
    s.append(this.toString());
  }

  @Override
  public void read(StringBuilder s)
  {
    throw new NotImplementedException();
  }

  @Override
  public String toString()
  {
    StringBuilder s = new StringBuilder();
    s.append("-------- Generation " + getGenerationNumber() + " ---------\n");
    s.append("Time:\t" + TimeUnit.MILLISECONDS.toMinutes(System.currentTimeMillis()- startTime)).append("\n");
    s.append("Init Being:\t" + initBeing).append("\n");
    s.append("Init Genes:\t" + initGene).append("\n");
    s.append("Init Population:\t" + initPopulation).append("\n");
    s.append("Population:\t" + getPopulationSize()).append("\n");
    s.append("Genes:\t" + Gene.TOTAL).append("\n");
    s.append("Beings:\t" + Being.TOTAL).append("\n");
    s.append("Average fitness:\t" + getAverageFitness()).append("\n");
    s.append("Best fitness:\t" + getBestFitness()).append("\n");
    s.append("Lifetime HillClimbs:\t" + lifetimeHillClimbs).append("\n");
    s.append("Lifetime Crossovers:\t" + lifetimeCrosses).append("\n");
    s.append("Average fitness/min:\t" + _past).append("\n");
    s.append("Diversity:\t" + getDiversity()).append("\n\n");
    return s.toString();
  }

  public double getDiversity()
  {
    Collections.sort(population);
    int diff = 0;
    ArrayList<Integer> ints = new ArrayList<>(population.size());
    for (int i = 1; i < population.size(); i++)
    {
      if (population.get(i).getAge() != population.get(i - 1).getAge())
      {
        ints.add(diff);
        diff = 0;
      }
      else
      {
        if (population.get(i).getGenotype().size() != population.get(i - 1).getGenotype().size())
        {
          diff++;
        }
      }
    }

    double change = 0.0;
    for (Integer anInt : ints)
    {
      if (anInt > 0)
      {
        change += ((double) anInt) * ((double) anInt);
      }
    }

    return 1 / change;
  }
}
