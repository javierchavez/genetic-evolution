package vcreature;


import java.util.ArrayList;

public class Evolution extends Thread
{
  private ArrayList<Subpopulation> subs;
  private Environment environment;
  private Population population;

  public Evolution(Environment environment)
  {
    this.environment = environment;
    population = new Population(environment);
    population.initPop();
    subs = new ArrayList<>();
    int chunkSize = 50;
    int numOfChunks = (int)Math.ceil((double)population.size() / chunkSize);

    for (int i = 0; i < numOfChunks; i++)
    {
      int start = i * chunkSize;
      int length = Math.min(population.size()- start, chunkSize);

      subs.add(new Subpopulation(String.valueOf(i)+ "k",population, start, length));
    }

    for (Subpopulation subpopulation : subs)
    {
      subpopulation.start();
    }
  }

  public Population getPopulation()
  {
    return population;
  }

  public Being getBeing(int i)
  {
    return population.get(i);
  }
}
