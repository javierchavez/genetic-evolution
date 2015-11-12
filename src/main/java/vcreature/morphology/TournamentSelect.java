package vcreature.morphology;

import vcreature.Being;
import vcreature.collections.Population;
import vcreature.utils.Statistics;

import java.util.ArrayList;
import java.util.Random;


public class TournamentSelect implements SelectionStrategy<Population, ArrayList<Being>>
{

  private final Statistics statistics;
  private final Random rnd = new Random();
  ArrayList<Being> newParents;

  protected TournamentSelect(Statistics statistics)
  {
    this.statistics = statistics;
  }



  @Override
  public ArrayList<Being> select(Population collection)
  {
    // choose what whatone to use???? probably in constructor or as a setter
    //TODO choose what type of selection
    // converges too quick tourment size be samller or avg is smaller.

    if (GeneticAlgorithmParams.SELECTION == 2)
    {
      return select2(collection);
    }
    else
    {
      return selection3(collection);
    }

  }

  private ArrayList<Being> select2(Population collection)
  {
    helper(collection);

    //tournament selection; tournament size 2; take random pairs from population; compare fitness; take better Being into next generation
    //need to keep population size consistent; take elitism and randomly added beings into account
    for (int i = 0; i < collection.size() - 2; i++)
    {
      int rndIndex1 = rnd.nextInt(collection.size());
      int rndIndex2 = rnd.nextInt(collection.size());

      Being being1 = collection.get(rndIndex1);

      while (rndIndex2 == rndIndex1)
      {
        rndIndex2 = rnd.nextInt(collection.size());
      }
      Being being2 = collection.get(rndIndex2);

      if (being1.getFitness() >= being2.getFitness())
      {
        newParents.add(being1.clone());
      }
      else
      {
        newParents.add(being2.clone());
      }

    }

    return newParents;
  }

  //Takes a population of Beings as an argument; uses tournament selection (tournament size 3)
  private ArrayList<Being> selection3(Population collection)
  {

    helper(collection);

    //tournament selection; tournament size 2; take random pairs from population; compare fitness; take better Being into next generation
    //need to keep population size consistent; take elitism and randomly added beings into account
    for (int i = 0; i < collection.size() - 2; i++)
    {
      int rndIndex1 = rnd.nextInt(collection.size());
      int rndIndex2 = rnd.nextInt(collection.size());
      int rndIndex3 = rnd.nextInt(collection.size());

      Being being1 = collection.get(rndIndex1);

      while (rndIndex2 == rndIndex1)
      {
        rndIndex2 = rnd.nextInt(collection.size());
      }
      Being being2 = collection.get(rndIndex2);


      while (rndIndex3 == rndIndex2 )
      {
        rndIndex3 = rnd.nextInt(collection.size());
      }
      Being being3 = collection.get(rndIndex2);

      if (being1.getFitness() >= being2.getFitness() && being1.getFitness() >= being3.getFitness())
      {
        newParents.add(being1.clone());
      }
      else if(being2.getFitness() >= being3.getFitness())
      {
        newParents.add(being2.clone());
      }

    }

    return newParents;
  }

  private void helper(Population collection)
  {
    newParents = new ArrayList<>();
    float currentGenBestFitness = 0f;
    Being currentGenBestBeing = collection.get(0);

    //get fitness for every member of population
    for (Being being : collection)
    {
      float fitness = being.getFitness();
      //being.setFitness(fitness);
      if (fitness > currentGenBestFitness)
      {
        currentGenBestBeing = being;
        currentGenBestFitness = fitness;
      }
    }

    if (this.statistics.getBestBeing() == null || currentGenBestBeing.getFitness() > this.statistics.getBestFitness())
    {
      statistics.setBestBeing(currentGenBestBeing);
    }
    //elitism;take the best individual unchanged into the next generation
    newParents.add(currentGenBestBeing);
    //take one random member of the population to meet spac that requires every member of population has chance of being selected
    newParents.add(collection.get(rnd.nextInt(collection.size())));

  }
}