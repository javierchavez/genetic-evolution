package vcreature.Algorithms;

import vcreature.Being;
import vcreature.Environment;
import vcreature.Population;
import vcreature.Subpopulation;
import vcreature.genotype.Effector;
import vcreature.genotype.Gene;
import vcreature.genotype.Genome;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author Cari
 */
public class GeneticAlgorithm
{


  private int totalGenerations = 10;
  private int populationSize;
  private int pctMutations = 0;
  private int pctCrossover = 100;
  private int generationNumber;
  private double bestFitness;
  private Being bestBeing;
//  private Population initialPopulation;
  private Environment simulation;
  private ConcurrentLinkedQueue<Being> queue;
//  private Timer timer = new Timer();



  public GeneticAlgorithm(Environment simulation)
  {
    this.simulation = simulation;
    this.generationNumber = 0;
    this.bestFitness = 0;
    this.bestBeing = null;
    queue = new ConcurrentLinkedQueue<>();
//    this.initialPopulation = initPop;
//    this.populationSize = initPop.size();

  }

//  public Population getInitialPopulation()
//  {
//    return initialPopulation;
//  }

  //TODO: make real fitness function
  //test fitness function; maximize surface area of all blocks; this is to test that the GA works to solve an optimization problem
 /* protected float calcFitness(Being individual)
  {


    float surfaceArea = 0.0f;
    Genome genotype = individual.getGenotype();


    LinkedList<Gene> genes = genotype.getGenes();
    for(Gene gene : genes)
    {
      surfaceArea = surfaceArea +  2 * gene.getHeightY()*gene.getLengthX() + 2 * gene.getHeightY() * gene.getWidthZ() + 2 * gene.getLengthX() * gene.getWidthZ();
    }

    return surfaceArea;
  }
*/
  protected void printFitnessStats(Vector<Being> beings)
  {
    float bestFitness = 0f;
    float avgFitness = 0f;
    float summedFitness = 0f;
    float currentBeingFitness;
    for(Being being : beings)
    {
      currentBeingFitness = being.getFitness();
      System.out.println("current being fitness: " + currentBeingFitness);
      if(currentBeingFitness > bestFitness)
      {

        bestFitness = being.getFitness();
      }
      summedFitness = summedFitness + currentBeingFitness;
    }
    avgFitness = summedFitness / beings.size();
    System.out.println(beings.size());
    System.out.println("Generation: " + generationNumber);
    System.out.println("Best fitness " + bestFitness);
    System.out.println("Avg fitness " + avgFitness);
   /* System.out.println("Best being");
    if(this.bestBeing != null)
    {
      printBeing(this.bestBeing);
    }
    */
  }

  protected void printBeing(Being being)
  {


    int geneNumber = -1;


    for(Gene gene : being.getGenotype().getGenes())
    {
      geneNumber++;

      System.out.println("Gene " + geneNumber);
      System.out.println("h " + gene.getHeightY() + " w " + gene.getWidthZ() + " l " + gene.getLengthX());
      System.out.println("# of genes in this being " + being.getGenotype().getGenes().size());
      System.out.println("edges " + gene.getEdges());
     // System.out.println("fitness " + being.getFitness());

    }
  }

  protected Vector<Being> selection (Vector<Being> population)
  {
    System.out.println("SELECTION");
    Vector<Being> newParents = new Vector();
    Random rnd = new Random();

    float currentGenBestFitness = 0f;
    Being currentGenBestBeing = population.get(0);

    //get fitness for every member of population
    for(Being being : population)
    {
      float fitness = being.getFitness();
      //being.setFitness(fitness);
      if(fitness > currentGenBestFitness)
      {
        currentGenBestBeing = being;
        currentGenBestFitness = fitness;
      }
    }

    if(this.bestBeing == null || currentGenBestBeing.getFitness() > this.bestBeing.getFitness())
    {
      this.bestBeing = currentGenBestBeing;
      this.bestFitness = currentGenBestBeing.getFitness();
    }
    //elitism;take the best individual unchanged into the next generation
    newParents.add(currentGenBestBeing.clone());
    //take one random member of the population to meet spac that requires every member of population has chance of being selected
    newParents.add(population.get(rnd.nextInt(population.size())).clone());

    //tournament selection; tournament size 2; take random pairs from population; compare fitness; take better Being into next generation
    //need to keep population size consistent; take elitism and randomly added beings into account
    for(int i = 0; i < populationSize - 2; i++)
    {
      int rndIndex1 = rnd.nextInt(population.size());
      int rndIndex2 = rnd.nextInt(population.size());

      Being being1 = population.get(rndIndex1);

      while(rndIndex2 == rndIndex1)
      {
        rndIndex2 = rnd.nextInt(population.size());
      }
      Being being2 = population.get(rndIndex2);

      if(being1.getFitness() >= being2.getFitness())
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

//
  protected void setParentJoint (Gene parent, Gene child)
  {

    Effector eChild = child.getEffector();
    Random rnd = new Random();
    float x = Math.signum(eChild.getParentX());
    float y = Math.signum(eChild.getParentY());
    float z = Math.signum(eChild.getParentZ());

    if(eChild.getPivotAxisX() == 1.0)
    {
      eChild.setParentX(x*(parent.getLengthX() - (rnd.nextFloat()*parent.getLengthX())));
      eChild.setParentY(y * parent.getHeightY() / 2f);
      eChild.setParentZ(z * parent.getWidthZ() / 2f);
    }
    else if(eChild.getPivotAxisY() == 1.0)
    {
      eChild.setParentY(y * (parent.getHeightY() - (rnd.nextFloat() * parent.getHeightY())));
      eChild.setParentX(x * parent.getLengthX() / 2f);
      eChild.setParentZ(z * parent.getWidthZ() / 2f);
    }
    else if(eChild.getPivotAxisZ() == 1.0)
    {
      eChild.setParentZ(z * (parent.getWidthZ() - (rnd.nextFloat() * parent.getWidthZ())));
      eChild.setParentY(y * parent.getHeightY() / 2f);
      eChild.setParentX(x * parent.getLengthX() / 2f);
    }

  }

  protected void printDimensions(Gene g)
  {
    System.out.println(" X " + g.getLengthX() + " Y " + g.getHeightY() + " Z " + g.getWidthZ());
  }

  private void printEffector(Effector e)
  {
    System.out.println(" ParentX " + e.getParentX() + " ParentY " + e.getParentY() + " ParentZ " + e.getParentZ());
    System.out.println(" ChildX " + e.getChildX() + " ChildY " + e.getChildY() + " ChildZ " + e.getChildZ());
    System.out.println(" PivotX " + e.getPivotAxisX() + " PivotY " + e.getPivotAxisY() + " PivotZ " + e.getPivotAxisZ());
  }

  protected int getParentIndex(Genome genotype, int geneIndex)
  {

    int parentIndex = 0;
    Gene possibleParent;
    for(int i = 0; i < genotype.getGenes().size(); i++)
    {
      possibleParent = genotype.getGenes().get(i);
      for(int j = 0; j < possibleParent.getEdges().size(); j++)
      {
        if(possibleParent.getEdges().get(j) == geneIndex)
        {
          return i;
        }
      }
    }
    return parentIndex;
  }



  protected Being[] crossover(Being parent1ARG, Being parent2ARG)
  {

    System.out.println("CROSSOVER");
    Being parent1 = parent1ARG.clone();
    Being parent2 = parent2ARG.clone();

    Being[] children = {parent1, parent2};
    if (parent1.getGenotype().getGenes().size() < 2 || parent2.getGenotype().getGenes().size() < 2)
    {
      System.out.println("CROSSOVER not performed; crossover not implemented for parent size 1");
      return children;
    }


    Random rnd = new Random();
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

    System.out.println("PARENT1: ");
    printBeing(parent1);
    System.out.println("PARENT2: ");
    printBeing(parent2);

    ArrayList<Integer> neighbors1 = gene1.getEdges();
    ArrayList<Integer> neighbors2 = gene2.getEdges();


    int parentIndex1 = getParentIndex(genotype1, crossoverPoint1);
    int parentIndex2 = getParentIndex(genotype2, crossoverPoint2);


    if (neighbors1.size() == 0 && neighbors2.size() == 0)
    {

      //swap genes at crossover points; update parent gene to remove this edge; add new edge from that parent to this new gene
      genotype1.getGenes().remove(crossoverPoint1);
      genotype1.getGenes().add(crossoverPoint1, gene2);
      setParentJoint(genotype1.getGenes().get(parentIndex1), gene2);

      genotype2.getGenes().remove(crossoverPoint2);
      genotype2.getGenes().add(crossoverPoint2, gene1);
      setParentJoint(genotype2.getGenes().get(parentIndex2), gene1);


    } else
    {

      crossParent(genotype1, genotype2CLONE, crossoverPoint1, crossoverPoint2, parentIndex1, gene2);
      crossParent(genotype2, genotype1CLONE, crossoverPoint2, crossoverPoint1, parentIndex2, gene1);

    }
    System.out.println("CP1 = " + crossoverPoint1 + "; CP2 = " + crossoverPoint2);
    System.out.println("PARENT1 AFTER CROSSOVER:");
    printBeing(parent1);
    System.out.println("PARENT2 AFTER CROSSOVER:");
    printBeing(parent2);

    children[0] = parent1.clone();
    children[1] = parent2.clone();

    return children;
  }



  private void crossParent(Genome genotype1, Genome genotype2, int crossoverPoint1, int crossoverPoint2, int parentIndex1, Gene gene2)
  {

    //update parent 1
    ArrayList<Integer> genesToSwap1dummy = new ArrayList();
    ArrayList<Integer> genesToSwap2dummy = new ArrayList();
    ArrayList<Integer> genesToSwap1 = getDescendants(genotype1, crossoverPoint1, genesToSwap1dummy);
    ArrayList<Integer> genesToSwap2 = getDescendants(genotype2, crossoverPoint2, genesToSwap2dummy);

    if (genesToSwap2.size() < genesToSwap1.size())
    {
    //Create Hash Map for swap1 parent locations//
    HashMap<Integer, Integer> swapParents1 = new HashMap();

    for (int i = 0; i < genesToSwap1.size(); i++)
    {
      swapParents1.put((int) genesToSwap1.get(i), getParentIndex(genotype1, (int) (genesToSwap1.get(i))));
    }

    //Create Hash Map for swap2 parent locations//
    HashMap<Integer, Integer> swapParents2 = new HashMap();

    for (int i = 0; i < genesToSwap2.size(); i++)
    {
      swapParents2.put((int) genesToSwap2.get(i), getParentIndex(genotype2, (int) (genesToSwap2.get(i))));
    }


    genotype1.getGenes().remove((int) genesToSwap1.get(0));
    genotype1.getGenes().add((int) genesToSwap1.get(0), genotype2.getGenes().get(genesToSwap2.get(0)));
    setParentJoint(genotype1.getGenes().get(parentIndex1), gene2);

    //remove all children of the gene at crossoverPoint1 from genotype1 and remove all edges to those nodes
    if (genesToSwap1.size() > 1)
    {
      for (int k = genesToSwap1.size() - 1; k >= 1; k--)
      {
        int parentIdx = swapParents1.get(((int) genesToSwap1.get(k)));
        genotype1.getGenes().get(parentIdx).removeEdge(genesToSwap1.get(k));
        genotype1.getGenes().remove((int) genesToSwap1.get(k));

      }

      HashMap<Integer, ArrayList<Integer>> updateEdges = new HashMap();
      HashMap<Integer, ArrayList<Integer>> removeEdges = new HashMap();
      for (int m = 0; m < genotype1.getGenes().size(); m++)
      {
        if (m != crossoverPoint1)
        {
          ArrayList<Integer> newEdges = new ArrayList();
          ArrayList<Integer> oldEdges = new ArrayList();
          for (int edge : genotype1.getGenes().get(m).getEdges())
          {
            int count = 0;

            for (int i = 1; i < genesToSwap1.size(); i++)
            {
              if (edge > (int) genesToSwap1.get(i))
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
      genotype2.getGenes().get((int) genesToSwap2.get(i)).getEdges().clear();
    }


    if (genesToSwap2.size() > 1)
    {


      int parent;
      HashMap<Integer, Integer> addedVerticesMap = new HashMap(); //map index of genesToSwap to new index
      addedVerticesMap.put(genesToSwap2.get(0), crossoverPoint1);
      for (int i = 1; i < genesToSwap2.size(); i++)
      {

        parent = addedVerticesMap.get(swapParents2.get((int) (genesToSwap2.get(i))));
        genotype1.getGenes().add(genotype2.getGenes().get(genesToSwap2.get(i)));
        genotype1.getGenes().get(parent).addEdge(genotype1.getGenes().size() - 1);
        addedVerticesMap.put(genesToSwap2.get(i), genotype1.getGenes().size() - 1);


      }

    }
  }
  }


  private ArrayList<Integer> getDescendants(Genome genotype, int geneIndex, ArrayList<Integer> descendants)
  {

    System.out.println("geneidx in descendants = " + geneIndex);
    descendants.add(geneIndex);
    ArrayList<Integer> neighbors = genotype.getGenes().get(geneIndex).getEdges();
    if(neighbors.size() == 0)
    {
      return descendants;
    }
    for (int neighbor : neighbors)
    {
      System.out.println("neighbor in descendants = " + neighbor);
      getDescendants(genotype, neighbor, descendants);
    }
    return descendants;
  }


  protected void mutation(Being individual)
  {
    Genome genotype = individual.getGenotype();
    LinkedList<Gene> genes = genotype.getGenes();
    Random rnd = new Random();
    Gene mutatedGene = genes.get(rnd.nextInt(genes.size()));

    int x = rnd.nextInt(3);
    switch(x)
    {
      case 0:
        if(0.5f + mutatedGene.getHeightY() <=10.0f)
        {
          mutatedGene.setHeightY(0.5f + mutatedGene.getHeightY());
        }
        break;
      case 1:
        if(0.5f + mutatedGene.getLengthX() <=10.0f)
        {
          mutatedGene.setLengthX(0.5f + mutatedGene.getLengthX());
        }
        break;
      case 2:
        if(0.5f + mutatedGene.getWidthZ() <=10.0f)
        {
          mutatedGene.setWidthZ(0.5f + mutatedGene.getWidthZ());
        }
        break;
    }

  }


  protected Vector<Being> createNextGeneration(Vector<Being>population)
  {
    Vector<Being> nextGeneration = new Vector();
    Vector<Being> newParents = selection(population);
    Random rnd = new Random();
    Being parent1;
    Being parent2;



    //elitism: put one copy of unaltered most fit being and one mutated version of most fit being directly into nextGen
    //pick random pairs of parents from population pool
    nextGeneration.add(this.bestBeing.clone());
    nextGeneration.add(this.bestBeing.clone());

    while(newParents.size() > 2)
    {
      int parent1index = rnd.nextInt(newParents.size());

      parent1 = newParents.get(parent1index).clone();
      newParents.remove(newParents.get(parent1index));
      int parent2index = rnd.nextInt(newParents.size());
      parent2 = newParents.get(parent2index).clone();
      newParents.remove(newParents.get(parent2index));
      if(rnd.nextInt(100) < this.pctCrossover)
      {
        Being[] children = crossover(parent1.clone(), parent2.clone());
        parent1 = children[0].clone();
        parent2 = children[1].clone();
      }
      if (rnd.nextInt(100) < this.pctMutations)
      {
        mutation(parent1);
      }
      if (rnd.nextInt(100) < this.pctMutations)
      {
        mutation(parent2);
      }
      nextGeneration.add(parent1.clone());
      nextGeneration.add(parent2.clone());
    }


    return nextGeneration;
  }

  public int currGen()
  {
    return generationNumber;
  }

  public Population evolvePopulation(Subpopulation beings, Population population)
  {
    System.out.println("EVOLVE POPULATION, Generation: " + this.generationNumber);

    double currentGenBestFitness; //best fitness from current generation
    Being genBestBeing; ///most fit creature from current generation
    Vector<Being> currentGeneration= beings.getPopulation().getBeings();
    this.populationSize = beings.getPopulation().size();

    Vector<Being> nextGeneration = new Vector();
    double summedFitness;
    double averageFitness;
    System.out.println("Next gen from " + beings.getName());

    do {
      summedFitness = 0;
      averageFitness = 0;
      currentGenBestFitness = 0;
      genBestBeing = currentGeneration.get(0);
      nextGeneration = createNextGeneration(currentGeneration);
      System.out.println("Generation: " + this.generationNumber);
      this.generationNumber++;
      for (Being individual : nextGeneration) {

        simulation.beginEvaluation(individual);
        while (true)
        {
          if (!individual.isUnderEvaluation())
          {
            break;
          }
        }



        double fitness = individual.getFitness();
        System.out.println("Evaluation complete...");
        System.out.println(individual.getFitness());


        summedFitness = summedFitness + fitness;
        if (fitness > currentGenBestFitness) {
          currentGenBestFitness = fitness;
          genBestBeing = individual;
        }
        if (fitness > this.bestFitness) {
          this.bestFitness = fitness;
          this.bestBeing = individual;
        }
      }
      averageFitness = summedFitness / nextGeneration.size();
      printFitnessStats(nextGeneration);

      currentGeneration = nextGeneration;



    }
    while (generationNumber < this.totalGenerations);
    for (int i = 0; i < Math.min(population.size(), generationNumber); i++)
    {
      population.remove(i);
      population.add(nextGeneration.get(i));
    }
    population.setAverageFitness(((float) averageFitness));
    population.setBestFitness((float)this.bestFitness);

    generationNumber = 0;
    return null;
  }

  public ConcurrentLinkedQueue<Being> getQueue()
  {
    return queue;
  }
}


