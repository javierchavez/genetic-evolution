package vcreature.genotype;


public class GeneticEngine
{
  public static Genome performCrossOver(Genome mother, Genome father)
  {

    Genome son = new Genome();
    son.setRoot(mother.getRoot().clone());
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


}
