package vcreature;


import java.util.ArrayList;


/**
 * This is the wrapper for actually splitting the large population up
 * into smaller ones so that threads can handle scaling.
 *
 */
public class Evolution extends Thread
{
  public ArrayList<Subpopulation> getSubs()
  {
    return subs;
  }

  private ArrayList<Subpopulation> subs;

  private Population population;

  public Evolution(Population population)
  {
    ;
    this.population = population;
    // population.initPop();
    subs = new ArrayList<>();
    int chunkSize = 10;
    int numOfChunks = (int) Math.ceil((double) population.size() / chunkSize);

    for (int i = 0; i < numOfChunks; i++)
    {
      int start = i * chunkSize;
      int length = Math.min(population.size() - start, chunkSize);

      subs.add(new Subpopulation(String.valueOf(i) + "k",
                                 population,
                                 start,
                                 length+start));
    }

    for (Subpopulation subpopulation : subs)
    {
      subpopulation.start();
    }
  }


  public int generations()
  {
    return population.getGenerations();
  }

  public Population getPopulation()
  {
    return population;
  }
//
//  public Being getBeing(int i)
//  {
//    return population.get(i);
//  }


}
