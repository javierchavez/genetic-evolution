package vcreature.morphology;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import vcreature.Being;
import vcreature.genotype.Effector;
import vcreature.genotype.Gene;
import vcreature.genotype.Genome;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;


public class CrossoverStrategy implements GeneticStrategy <Being, ArrayList<Being>>
{
  private Random rnd = new Random();

  @Override
  public ArrayList<Being> run(Being ... part)
  {
    if (part.length == 2)
    {
      return crossover(part[0], part[1]);
    }
    else
    {
      throw new NotImplementedException();
    }
  }

  ////////ORIGINAL CROSSOVER//// other one....
  private ArrayList<Being> crossover(Being parent1ARG, Being parent2ARG)
  {
    Being parent1 = parent1ARG.clone();
    Being parent2 = parent2ARG.clone();

    ArrayList<Being> children = new ArrayList<>();
    children.add(parent1);
    children.add(parent2);
    if (parent1.getGenotype().getGenes().size() < 2 || parent2.getGenotype().getGenes().size() < 2)
    {
      // System.out.println("CROSSOVER not performed; crossover not implemented for parent size 1");
      return children;
    }



    //index of first gene to be swapped on each parent
    int crossoverPoint1 = 1 + rnd.nextInt((parent1.getGenotype().getGenes().size() - 1));
    int crossoverPoint2 = 1 + rnd.nextInt((parent2.getGenotype().getGenes().size() - 1));
    Genome genotype1 = parent1.getGenotype();
    Gene gene1 = genotype1.getGenes().get(crossoverPoint1);
    Genome genotype2 = parent2.getGenotype();
    Gene gene2 = parent2.getGenotype().getGenes().get(crossoverPoint2);

    //make copies of parents
    Being parent1CLONE = parent1.clone();
    Being parent2CLONE = parent2.clone();

    Genome genotype1CLONE = parent1CLONE.getGenotype();
    Gene gene1CLONE = genotype1CLONE.getGenes().get(crossoverPoint1);
    Genome genotype2CLONE = parent2CLONE.getGenotype();
    Gene gene2CLONE = parent2CLONE.getGenotype().getGenes().get(crossoverPoint2);


    ArrayList<Integer> neighbors1 = gene1.getEdges();
    ArrayList<Integer> neighbors2 = gene2.getEdges();


    int parentIndex1 = getParentIndex(genotype1, crossoverPoint1);
    int parentIndex2 = getParentIndex(genotype2, crossoverPoint2);


    if (neighbors1.size() == 0 && neighbors2.size() == 0)
    {

      //swap genes at crossover points; update parent gene to remove this edge; add new edge from that parent to this new gene
      genotype1.getGenes().remove(crossoverPoint1);
      genotype1.getGenes().add(crossoverPoint1, gene2CLONE);
      setParentJoint(genotype1.getGenes().get(parentIndex1), gene2CLONE);

      genotype2.getGenes().remove(crossoverPoint2);
      genotype2.getGenes().add(crossoverPoint2, gene1CLONE);
      setParentJoint(genotype2.getGenes().get(parentIndex2), gene1CLONE);


    }
    else
    {

      crossParent(genotype1, genotype2CLONE, crossoverPoint1, crossoverPoint2, parentIndex1, gene2);
      crossParent(genotype2, genotype1CLONE, crossoverPoint2, crossoverPoint1, parentIndex2, gene1);

    }

    children.add(0, parent1);
    children.add(1, parent2);
    return children;
  }

  //returns the index of the parent of a gene in a genotype
  private int getParentIndex(Genome genotype, int geneIndex)
  {

    int parentIndex = 0;
    Gene possibleParent;
    for (int i = 0; i < genotype.getGenes().size(); i++)
    {
      possibleParent = genotype.getGenes().get(i);
      for (int j = 0; j < possibleParent.getEdges().size(); j++)
      {
        if (possibleParent.getEdges().get(j) == geneIndex)
        {
          return i;
        }
      }
    }
    return parentIndex;
  }

  //sets joint position on parent after crossover
  private void setParentJoint(Gene parent, Gene child)
  {

    Effector eChild = child.getEffector();
    Random rnd = new Random();
    float x = Math.signum(eChild.getParentX());
    float y = Math.signum(eChild.getParentY());
    float z = Math.signum(eChild.getParentZ());

    if (eChild.getPivotAxisX() == 1.0)
    {
      eChild.setParentX(x * (parent.getLengthX() - (rnd.nextFloat() * parent.getLengthX())));
      eChild.setParentY(y * parent.getHeightY() / 2f);
      eChild.setParentZ(z * parent.getWidthZ() / 2f);
    }
    else if (eChild.getPivotAxisY() == 1.0)
    {
      eChild.setParentY(y * (parent.getHeightY() - (rnd.nextFloat() * parent.getHeightY())));
      eChild.setParentX(x * parent.getLengthX() / 2f);
      eChild.setParentZ(z * parent.getWidthZ() / 2f);
    }
    else if (eChild.getPivotAxisZ() == 1.0)
    {
      eChild.setParentZ(z * (parent.getWidthZ() - (rnd.nextFloat() * parent.getWidthZ())));
      eChild.setParentY(y * parent.getHeightY() / 2f);
      eChild.setParentX(x * parent.getLengthX() / 2f);
    }

  }

  //helper function for crossover
  private void crossParent(Genome genotype1, Genome genotype2, int crossoverPoint1, int crossoverPoint2, int parentIndex1, Gene gene2)
  {

    //update parent 1
    ArrayList<Integer> genesToSwap1dummy = new ArrayList<>();
    ArrayList<Integer> genesToSwap2dummy = new ArrayList<>();
    ArrayList<Integer> genesToSwap1 = getDescendants(genotype1, crossoverPoint1, genesToSwap1dummy);
    ArrayList<Integer> genesToSwap2 = getDescendants(genotype2, crossoverPoint2, genesToSwap2dummy);


    //Create Hash Map for swap1 parent locations//
    HashMap<Integer, Integer> swapParents1 = new HashMap<>();

    for (int i = 0; i < genesToSwap1.size(); i++)
    {
      swapParents1.put( genesToSwap1.get(i), getParentIndex(genotype1,  (genesToSwap1.get(i))));
    }

    //Create Hash Map for swap2 parent locations//
    HashMap<Integer, Integer> swapParents2 = new HashMap<>();

    for (int i = 0; i < genesToSwap2.size(); i++)
    {
      swapParents2.put( genesToSwap2.get(i), getParentIndex(genotype2,  (genesToSwap2.get(i))));
    }


    genotype1.getGenes().remove((int) genesToSwap1.get(0));
    genotype1.getGenes().add( genesToSwap1.get(0), genotype2.getGenes().get(genesToSwap2.get(0)));
    setParentJoint(genotype1.getGenes().get(parentIndex1), gene2);

    //remove all children of the gene at crossoverPoint1 from genotype1 and remove all edges to those nodes
    if (genesToSwap1.size() > 1)
    {
      for (int k = genesToSwap1.size() - 1; k >= 1; k--)
      {
        int parentIdx = swapParents1.get(( genesToSwap1.get(k)));
        genotype1.getGenes().get(parentIdx).removeEdge(genesToSwap1.get(k));
        genotype1.getGenes().remove((int) genesToSwap1.get(k));

      }

      HashMap<Integer, ArrayList<Integer>> updateEdges = new HashMap<>();
      HashMap<Integer, ArrayList<Integer>> removeEdges = new HashMap<>();
      for (int m = 0; m < genotype1.getGenes().size(); m++)
      {
        if (m != crossoverPoint1)
        {
          ArrayList<Integer> newEdges = new ArrayList<>();
          ArrayList<Integer> oldEdges = new ArrayList<>();
          for (int edge : genotype1.getGenes().get(m).getEdges())
          {
            int count = 0;

            for (int i = 1; i < genesToSwap1.size(); i++)
            {
              if (edge >  genesToSwap1.get(i))
              {
                count++;
              }
            }

            oldEdges.add(edge);
            newEdges.add(edge - count);

          }
          removeEdges.put(m, oldEdges);
          updateEdges.put(m, newEdges);

        }
      }

      for (int m = 0; m < removeEdges.size(); m++)
      {
        if (m != crossoverPoint1)
        {
          for (int n : removeEdges.get(m))
          {
            genotype1.getGenes().get(m).removeEdge(n);
          }
        }
      }

      for (int m = 0; m < updateEdges.size(); m++)
      {
        if (m != crossoverPoint1)
        {

          for (int n : updateEdges.get(m))
          {
            genotype1.getGenes().get(m).addEdge(n);
          }
        }
      }
    }
    //remove all edges from genes swapped in

    genotype1.getGenes().get(crossoverPoint1).getEdges().clear();

    for (int i = 0; i < genesToSwap2.size(); i++)
    {
      genotype2.getGenes().get( genesToSwap2.get(i)).getEdges().clear();
    }


    if (genesToSwap2.size() > 1)
    {


      int parent;
      HashMap<Integer, Integer> addedVerticesMap = new HashMap<>(); //map index of genesToSwap to new index
      addedVerticesMap.put(genesToSwap2.get(0), crossoverPoint1);
      for (int i = 1; i < genesToSwap2.size(); i++)
      {

        parent = addedVerticesMap.get(swapParents2.get( (genesToSwap2.get(i))));
        genotype1.getGenes().add(genotype2.getGenes().get(genesToSwap2.get(i)));
        genotype1.getGenes().get(parent).addEdge(genotype1.getGenes().size() - 1);
        addedVerticesMap.put(genesToSwap2.get(i), genotype1.getGenes().size() - 1);


      }

    }

  }

  //helper function for crossover
  private ArrayList<Integer> getDescendants(Genome genotype, int geneIndex, ArrayList<Integer> descendants)
  {
    descendants.add(geneIndex);
    ArrayList<Integer> neighbors = genotype.getGenes().get(geneIndex).getEdges();
    if (neighbors.size() == 0)
    {
      return descendants;
    }
    for (int neighbor : neighbors)
    {
      getDescendants(genotype, neighbor, descendants);
    }
    return descendants;
  }

}
