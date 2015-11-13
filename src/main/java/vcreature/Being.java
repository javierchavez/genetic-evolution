package vcreature;

/**
 * @author Javier Chavez
 * @author Alex Baker
 * @author Dominic Salas
 * @author Carrie Martinez
 * <p>
 * Date November 4, 2015
 * CS 351
 * Genetic Evolution
 */


import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import vcreature.genotype.Genome;
import vcreature.utils.Savable;


/**
 * Mainly to house the Genome and data about the Being.
 * This class describes an object which gets evolved in GA
 */
public class Being implements Comparable<Being>, Savable
{
  public static int TOTAL = 0;

  // private String name;
  private int age;
  private Genome genotype;
  private int timesHillClimbed;
  private int children;
  private float fitness = 0;
  private boolean underEvaluation = false;

  public int getAge()
  {
    return age;
  }

  public void setAge(int age)
  {
    this.age = age;
  }

  public int getTimesHillClimbed()
  {
    return timesHillClimbed;
  }

  public void setTimesHillClimbed(int timesHillClimbed)
  {
    this.timesHillClimbed = timesHillClimbed;
  }

  public int getChildren()
  {
    return children;
  }

  public void setChildren(int children)
  {
    this.children = children;
  }


  /**
   * Create a new being and update the total number of beings
   */
  public Being()
  {
    TOTAL++;
  }

  /**
   * Create a being with the given genotype
   *
   * @param bird genome to initialize the being to
   */
  public Being(Genome bird)
  {
    this();
    this.genotype = bird;
  }

  /**
   * Returns whether this being is being evaluated
   *
   * @return true | false depending on whether the being in under evaluation
   */
  public synchronized boolean isUnderEvaluation()
  {
    return underEvaluation;
  }

  /**
   * Set whether the being is under evaluation or not
   *
   * @param underEvaluation true | false to set the evaluation state of the being
   */
  public synchronized void setUnderEvaluation(boolean underEvaluation)
  {
    this.underEvaluation = underEvaluation;
  }

  /**
   * Get the genotype of the being
   *
   * @return current genotype of the being
   */
  public Genome getGenotype()
  {
    return genotype;
  }

  /**
   * Set the genotype of the being to a new genotype
   *
   * @param genotype new genotype to apply to the being
   */
  public void setGenotype(Genome genotype)
  {
    this.genotype = genotype;
  }

  /**
   * Get the fitness of the being
   *
   * @return the fitness of this being
   */
  public float getFitness()
  {
    return fitness;
  }

  /**
   * set the fitness for the being
   *
   * @param fitness fitness to apply to the being
   */
  public void setFitness(float fitness)
  {
    this.fitness = fitness;
  }

  @Override
  public Being clone()
  {
    Being newBeing = new Being();
    newBeing.setGenotype(genotype.clone());
    newBeing.setFitness(fitness);
    newBeing.setAge(age);
    newBeing.setChildren(children);
    newBeing.setTimesHillClimbed(timesHillClimbed);
    return newBeing;
  }

  @Override
  public boolean equals(Object o)
  {
    if (this == o)
    {
      return true;
    }
    if (o == null || getClass() != o.getClass())
    {
      return false;
    }

    Being being = (Being) o;

    if (age != being.age)
    {
      return false;
    }
    if (timesHillClimbed != being.timesHillClimbed)
    {
      return false;
    }
    if (children != being.children)
    {
      return false;
    }
    if (Float.compare(being.fitness, fitness) != 0)
    {
      return false;
    }
    if (underEvaluation != being.underEvaluation)
    {
      return false;
    }
    return !(genotype != null ? !genotype.equals(being.genotype) : being.genotype != null);

  }

  @Override
  public int hashCode()
  {
    int result = age;
    result = 31 * result + (genotype != null ? genotype.hashCode() : 0);
    result = 31 * result + timesHillClimbed;
    result = 31 * result + children;
    result = 31 * result + (fitness != +0.0f ? Float.floatToIntBits(fitness) : 0);
    result = 31 * result + (underEvaluation ? 1 : 0);
    return result;
  }

  @Override
  public int compareTo(Being o)
  {
    if (fitness < o.getFitness())
    {
      return -1;
    }
    else if (fitness > o.getFitness())
    {
      return 1;
    }


    return 0;
  }

  @Override
  public void write(StringBuilder s)
  {
    s.append("START\n");
    s.append(getFitness()).append("\n");
    s.append(getAge()).append("\n");
    s.append(getTimesHillClimbed()).append("\n");
    getGenotype().write(s);
  }

  @Override
  public void read(StringBuilder s)
  {
    throw new NotImplementedException();
  }
}
