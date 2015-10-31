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
//    for (int i = 0; i < 10; i++)
//    {
//
//      subs.add(new Subpopulation(String.valueOf(i)+ "k",population, i*10, (i*10)+11));
//    }
//
//    for (Subpopulation subpopulation : subs)
//    {
//      subpopulation.start();
//    }
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
