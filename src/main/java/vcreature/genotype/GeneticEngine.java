package vcreature.genotype;


import java.util.Random;

public class GeneticEngine
{
  public static Genome performCrossOver(Genome mother, Genome father)
  {

    Genome son = new Genome(); son.setRoot(mother.getRoot().clone());
    son.getGenes().add(son.getRoot());


    int motherStart = mother.size();
    int motherEnd = mother.size();
    int fatherStart = father.size();
    int fatherEnd = father.size();

    son.merge(mother, motherStart, motherEnd);
    son.merge(father, fatherStart, fatherEnd);

    // create edges to root node
    for (int i = 0; i < 4; i++)
    {
      if (Math.random() < .02)
      {
        // son.linkRandomGeneTo(0);
      }
    }

    return son;
  }


  public static Genome[] singlePoint(Genome mom, Genome dad)
  {

    Random rand = new Random();

    int size = mom.size();

    // Create the Genomes for the twin children.
    Genome g1 = new Genome();
    Genome g2 = new Genome();


    int interval = size / 5;
    int transition = rand.nextInt(size - (2 * interval)) + interval;


    for (int i = 0; i < size; i++)
    {
      Gene parentAbstractGeneA = mom.getGenes().get(i).clone();
      Gene parentAbstractGeneB = dad.getGenes().get(i).clone();
      Gene childAbstractGeneA;
      Gene childAbstractGeneB;


      try
      {
        if (i < transition)
        {
          childAbstractGeneA = parentAbstractGeneA.clone();
          childAbstractGeneB = parentAbstractGeneB.clone();
        }
        else
        {
          childAbstractGeneA = parentAbstractGeneB.clone();
          childAbstractGeneB = parentAbstractGeneA.clone();
        }

        g1.append(childAbstractGeneA);
        g2.append(childAbstractGeneB);
      }
      catch (IllegalArgumentException ex)
      {
        return null;
      }
    }

    Genome[] children = {g1, g2};
    return children;
  }


}
