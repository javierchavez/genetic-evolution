package vcreature.Algorithms;

/**
 * @author Javier Chavez
 * @author Alex Baker
 * @author Dominic Salas
 * @author Carrie Martinez
 * <p>
 * Date November 4, 2015
 * CS 351
 * Genetic Evolution
 * <p>
 * Module description here
 */


import vcreature.Being;
import vcreature.Environment;
import vcreature.Population;
import vcreature.genotype.Gene;

import java.util.*;


public class HillClimbing
{
  private final Environment environment;
  private Population population;
  private double amount;

  private float lowest = 0;
  private float highest = 7;
  private int fails = 0;
  private int currentTotal = 0;

  private double currentOptimizedFitness, currentFitnessValue;

  private Random rnd = new Random();

  private HashMap<HillClimbStrategy.Strategies, Float> hillClimbMapStrats = new HashMap<>();
  private int fitness = 0;
  private int lifetimeRuns = 0;
  private Being bestBeing;
  private ArrayList<Being> kill = new ArrayList<>();
  // private Subpopulation subPopulation;
  private float bestFitness;
  private float sumFitness=0f;
  private float lifeTime = 0f;
  private float genAvgFitness;


  public HillClimbing (Environment environment)
  {
    this.environment = environment;

    hillClimbMapStrats.put(HillClimbStrategy.Strategies.EFFECTOR, .02f);
    hillClimbMapStrats.put(HillClimbStrategy.Strategies.NEURAL_NET, .02f);
    hillClimbMapStrats.put(HillClimbStrategy.Strategies.ROOT_GENE, 0.75f);
    hillClimbMapStrats.put(HillClimbStrategy.Strategies.LIMB_GENE, 1f);

  }

  /**
   * Pass in a being and iterate through that being testing if there are any
   * improvements to the fitness by making changes to the genes.
   * @param individual
   */

  private boolean hillClimbingEvaluation(Being individual)
  {
    HillClimbStrategy.Strategies currentStrategy = HillClimbStrategy.Strategies.ROOT_GENE;
    float currentFitneesBeing = individual.getFitness();
    int tries = 0;
    for (int i = 0; i < individual.getGenotype().size(); i++)
    {
      currentTotal++;

      lifetimeRuns++;
      lowest = Math.min(individual.getFitness(), lowest);
      highest = Math.max(individual.getFitness(), highest);

      Gene gene= individual.getGenotype().get(i);
      tries++;
      for (Map.Entry<HillClimbStrategy.Strategies, Float> strategiesFloatEntry : hillClimbMapStrats.entrySet())
      {
        currentStrategy = null;
        if (strategiesFloatEntry.getValue()/lifetimeRuns > .25f)
        {
          currentStrategy = strategiesFloatEntry.getKey();
          break;
        }
      }
      if (currentStrategy == null)
      {
        int x = rnd.nextInt(HillClimbStrategy.Strategies.values().length);
        currentStrategy = HillClimbStrategy.Strategies.values()[x];
      }
      switch (currentStrategy)
      {
        case EFFECTOR:
          System.out.println("hill climbing on " + currentStrategy);
          new EffectorClimbStrategy<>().climb(gene.getEffector());
          break;
        case NEURAL_NET:
          System.out.println("hill climbing on " + currentStrategy);
          new NeurlNetClimbStrategey<>().climb(gene.getEffector().getNeuralNet());
          break;
        case ROOT_GENE:
          System.out.println("hill climbing on " + currentStrategy);
          new RootGeneDimensionClimbStrategy<>().climb(individual.getGenotype());
          break;
        case LIMB_GENE:
          System.out.println("hill climbing on " + currentStrategy);
          new LimbGeneDimensionClimbStrategy<>().climb(individual.getGenotype());
          break;
        default:
          currentStrategy = HillClimbStrategy.Strategies.LIMB_GENE;
          System.out.println("hill climbing on " + currentStrategy);
          new LimbGeneDimensionClimbStrategy<>().climb(individual.getGenotype());
          break;
      }

      currentFitnessValue = individual.getFitness();
      environment.beginEvaluation(individual);
      while (true)
      {
        if (!individual.isUnderEvaluation())
        {
          break;
        }
      }

      currentOptimizedFitness = individual.getFitness();
      sumFitness += currentOptimizedFitness;
      lifeTime +=currentOptimizedFitness;
      if (currentFitnessValue < 1 && currentOptimizedFitness < 1)
      {
        kill.add(individual);
        if (tries > 4)
        {
          System.out.println("Killing individual");
          tries=0;
          return false;
        }
      }

      if (currentFitnessValue >= currentOptimizedFitness)
      {
        float factor = .10f;
        if (currentFitnessValue <= lowest)
        {
          factor = .30f;
          fails++;
        }
        hillClimbMapStrats.put(currentStrategy, hillClimbMapStrats.get(currentStrategy) - factor);
        HillClimbing.sortValue(hillClimbMapStrats);

      }
      else
      {
        float factor = .10f;
        if (currentOptimizedFitness >= highest)
        {
          factor = .55f;
          bestBeing = individual;
          population.setBestFitness((float) currentOptimizedFitness);
          population.setBestBeing(individual);

        }
        fitness += currentOptimizedFitness;
        hillClimbMapStrats.put(currentStrategy, hillClimbMapStrats.get(currentStrategy) + factor);
        HillClimbing.sortValue(hillClimbMapStrats);
        if (tries > 4)
        {
          System.out.println("Local max");
          tries=0;
          return false;
        }
        --i;
      }

    }
    tries=0;
    System.out.println("Individual done");
    return false;
  }

  /**
   * Will iterate through beings within the population and will returned
   * an evolved population.
   * @return and evolved population.
   */

  public Vector<Being> evolvePopulation(Population population)
  {

    this.population = population;
    Vector<Being> current = new Vector<>();
    current.addAll(population.getBeings());
    for (int i = 0; i < 4; i++)
    {

      hillClimbingEvaluation(current.get(i));

    }

    population.getBeings().clear();
    population.getBeings().addAll(current);
    population.getBeings().removeIf(kill::contains);


    System.out.println("Hill climbing complete on current individuals");


    population.setCurrentFailedHillClimbs(fails);
    population.setCurrentRejectedCreatures(kill.size());
    population.setTotalLifetimeFitness(population.getTotalLifetimeFitness() + fitness);
    population.setPastAverageFitness(population.getAverageFitness());
    population.setAverageFitness(population.getTotalLifetimeFitness() / population.getLifetimeOffspring());

    currentTotal=0;
    sumFitness = 0;
    return new Vector<Being>();

  }


  public int getFitness()
  {
    return fitness;
  }


  public static void sortValue(HashMap<?, Float> t){

    //Transfer as List and sort it
    ArrayList<Map.Entry<?, Float>> l = new ArrayList(t.entrySet());
    Collections.sort(l, new Comparator<Map.Entry<?, Float>>(){

      public int compare(Map.Entry<?, Float> o1, Map.Entry<?, Float> o2) {
        return o1.getValue().compareTo(o2.getValue());
      }});
  }

  public float getBestFitness()
  {
    return bestFitness;
  }

  public float getGenAvgFitness()
  {
    return sumFitness/currentTotal;
  }
}
