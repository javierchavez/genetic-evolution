package vcreature.genotype;


public class GeneticEngine
{
  public static Genome performCrossOver(Genome mother, Genome father)
  {

    Genome son = new Genome(); son.setRoot(mother.getRoot());
    son.getGenes().add(son.getRoot());

    return son;
  }


  public static Genome[] singlePoint(Genome mom, Genome dad)
  {
    return null;
  }


}
