package vcreature.Algorithms;

import com.jme3.math.Vector3f;
import vcreature.Being;
import vcreature.Environment;
import vcreature.Population;
import vcreature.Subpopulation;
import vcreature.genotype.Effector;
import vcreature.genotype.Gene;
import vcreature.genotype.Genome;
import vcreature.phenotype.Block;
import vcreature.utils.GenomeHelper;

import java.util.*;

/**
 * This class evolves a population of beings using a genetic algorithm (GA). The algorithms main functions are:
 * fitness calculation; selection; crossover; and mutation
 * @author Javier Chavez
 * @author Alex Baker
 * @author Dominic Salas
 * @author Cari Martinez
 */

public class GeneticAlgorithm
{


  public static final int MAX_ITERATIONS = 50;
  private int pctMutations = 0;
  private int pctCrossover = 100;

  public float getBestFitness()
  {
    return bestFitness;
  }

  public float getCurrentGenBestFitness()
  {
    return currentGenBestFitness;
  }

  public float getCurrentGenAverageFitness()
  {
    return currentGenAverageFitness;
  }

  private float currentGenBestFitness;
  private Being currentGenBestBeing;
  private int populationSize;
  private int generationNumber;
  private float bestFitness;
  private Being bestBeing;
  private float currentGenAverageFitness;
  private float firstGenAvgFitness = 0f;
  private Environment simulation;

  public float getFirstGenAvgFitness()
  {
    return firstGenAvgFitness;
  }

  /**
   *
   * @param simulation reference to physics environment
   */
  public GeneticAlgorithm(Environment simulation)
  {
    this.simulation = simulation;
    this.generationNumber = 0;
    this.bestFitness = 0;
    this.bestBeing = null;
  }




  private void printFitnessStats(Vector<Being> beings)
  {
    float currentBeingFitness;
    int index = 0;
    for (Being being : beings)
    {
      currentBeingFitness = being.getFitness();
      System.out.println("being " + index+ " fitness: " + being.getFitness());
      System.out.println("being " + index+ " age: " + being.getAge());
      System.out.println("being " + index+ " children: " + being.getChildren());
      if (currentBeingFitness > this.currentGenBestFitness)
      {

        this.currentGenBestFitness = being.getFitness();
      }
      if (this.currentGenBestFitness > this.bestFitness)
      {

        this.bestFitness = currentGenBestFitness;
      }


      index++;
    }

    System.out.println("Population size: " + beings.size());
    System.out.println("Generation: " + generationNumber);
    System.out.println("Overall Best fitness " + this.bestFitness);
    System.out.println("Current gen Avg fitness " + this.currentGenAverageFitness);
    System.out.println("Current Gen Best fitness " + this.currentGenBestFitness);

  }

  //For testing purposes only
  private void printBeing(Being being)
  {

    int geneNumber = -1;
    for (Gene gene : being.getGenotype().getGenes())
    {
      geneNumber++;

      System.out.println("Gene " + geneNumber);
      System.out.println("h " + gene.getHeightY() + " w " + gene.getWidthZ() + " l " + gene.getLengthX());
      System.out.println("# of genes in this being " + being.getGenotype().getGenes().size());
      System.out.println("edges " + gene.getEdges());
      // System.out.println("fitness " + being.getFitness());

    }
  }

  //Takes a population of Beings as an argument; uses tournament selection (tournament size 2)
  private Vector<Being> selection(Vector<Being> population)
  {

    Vector<Being> newParents = new Vector();
    Random rnd = new Random();

    float currentGenBestFitness = 0f;
    Being currentGenBestBeing = population.get(0);

    //get fitness for every member of population
    for (Being being : population)
    {
      float fitness = being.getFitness();
      //being.setFitness(fitness);
      if (fitness > currentGenBestFitness)
      {
        currentGenBestBeing = being;
        currentGenBestFitness = fitness;
      }
    }

    if (this.bestBeing == null || currentGenBestBeing.getFitness() > this.bestBeing.getFitness())
    {
      this.bestBeing = currentGenBestBeing;
      this.bestFitness = currentGenBestBeing.getFitness();
    }
    //elitism;take the best individual unchanged into the next generation
    newParents.add(currentGenBestBeing);
    //take one random member of the population to meet spac that requires every member of population has chance of being selected
    newParents.add(population.get(rnd.nextInt(population.size())));

    //tournament selection; tournament size 2; take random pairs from population; compare fitness; take better Being into next generation
    //need to keep population size consistent; take elitism and randomly added beings into account
    for (int i = 0; i < populationSize - 2; i++)
    {
      int rndIndex1 = rnd.nextInt(population.size());
      int rndIndex2 = rnd.nextInt(population.size());

      Being being1 = population.get(rndIndex1);

      while (rndIndex2 == rndIndex1)
      {
        rndIndex2 = rnd.nextInt(population.size());
      }
      Being being2 = population.get(rndIndex2);

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
  private Vector<Being> selection3(Vector<Being> population)
  {

    Vector<Being> newParents = new Vector();
    Random rnd = new Random();

    float currentGenBestFitness = 0f;
    Being currentGenBestBeing = population.get(0);

    //get fitness for every member of population
    for (Being being : population)
    {
      float fitness = being.getFitness();
      //being.setFitness(fitness);
      if (fitness > currentGenBestFitness)
      {
        currentGenBestBeing = being;
        currentGenBestFitness = fitness;
      }
    }

    if (this.bestBeing == null || currentGenBestBeing.getFitness() > this.bestBeing.getFitness())
    {
      this.bestBeing = currentGenBestBeing;
      this.bestFitness = currentGenBestBeing.getFitness();
    }
    //elitism;take the best individual unchanged into the next generation
    newParents.add(currentGenBestBeing);
    //take one random member of the population to meet spac that requires every member of population has chance of being selected
    newParents.add(population.get(rnd.nextInt(population.size())));

    //tournament selection; tournament size 2; take random pairs from population; compare fitness; take better Being into next generation
    //need to keep population size consistent; take elitism and randomly added beings into account
    for (int i = 0; i < populationSize - 2; i++)
    {
      int rndIndex1 = rnd.nextInt(population.size());
      int rndIndex2 = rnd.nextInt(population.size());
      int rndIndex3 = rnd.nextInt(population.size());

      Being being1 = population.get(rndIndex1);

      while (rndIndex2 == rndIndex1)
      {
        rndIndex2 = rnd.nextInt(population.size());
      }
      Being being2 = population.get(rndIndex2);


      while (rndIndex3 == rndIndex2 )
      {
        rndIndex3 = rnd.nextInt(population.size());
      }
      Being being3 = population.get(rndIndex2);

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
      eChild.setParentX(x * (parent.getLengthX() - (rnd.nextFloat() * parent.getLengthX()/2f)));
      eChild.setParentY(y * parent.getHeightY() / 2f);
      eChild.setParentZ(z * parent.getWidthZ() / 2f);
    }
    else if (eChild.getPivotAxisY() == 1.0)
    {
      eChild.setParentY(y * (parent.getHeightY() - (rnd.nextFloat() * parent.getHeightY()/2f)));
      eChild.setParentX(x * parent.getLengthX() / 2f);
      eChild.setParentZ(z * parent.getWidthZ() / 2f);
    }
    else if (eChild.getPivotAxisZ() == 1.0)
    {
      eChild.setParentZ(z * (parent.getWidthZ() - (rnd.nextFloat() * parent.getWidthZ()/2f)));
      eChild.setParentY(y * parent.getHeightY() / 2f);
      eChild.setParentX(x * parent.getLengthX() / 2f);
    }

  }

  //for testing only
  private void printDimensions(Gene g)
  {
    System.out.println(" X " + g.getLengthX() + " Y " + g.getHeightY() + " Z " + g.getWidthZ());
  }


  //for testing only
  private void printEffector(Effector e)
  {
    System.out.println(" ParentX " + e.getParentX() + " ParentY " + e.getParentY() + " ParentZ " + e.getParentZ());
    System.out.println(" ChildX " + e.getChildX() + " ChildY " + e.getChildY() + " ChildZ " + e.getChildZ());
    System.out.println(" PivotX " + e.getPivotAxisX() + " PivotY " + e.getPivotAxisY() + " PivotZ " + e.getPivotAxisZ());
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


  ////////ORIGINAL CROSSOVER////
  //returns 2 crossed over beings
  private Being[] crossover(Being parent1ARG, Being parent2ARG)
  {


    Being parent1 = parent1ARG.clone();
    Being parent2 = parent2ARG.clone();

    Being[] children = {parent1, parent2};
    if (parent1.getGenotype().getGenes().size() < 2 || parent2.getGenotype().getGenes().size() < 2)
    {
     // System.out.println("CROSSOVER not performed; crossover not implemented for parent size 1");
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


    children[0] = parent1;
    children[1] = parent2;

    return children;
  }

  //helper function for crossover
  private void crossParent(Genome genotype1, Genome genotype2, int crossoverPoint1, int crossoverPoint2, int parentIndex1, Gene gene2)
  {

    //update parent 1
    ArrayList<Integer> genesToSwap1dummy = new ArrayList();
    ArrayList<Integer> genesToSwap2dummy = new ArrayList();
    ArrayList<Integer> genesToSwap1 = getDescendants(genotype1, crossoverPoint1, genesToSwap1dummy);
    ArrayList<Integer> genesToSwap2 = getDescendants(genotype2, crossoverPoint2, genesToSwap2dummy);


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

  //simpler crossover that can quickly add blocks to creatures
  private Being[] crossover2(Being parent1ARG, Being parent2ARG)
  {

    Being parent1 = parent1ARG.clone();
    Being parent2 = parent2ARG.clone();

    Being[] children = {parent1, parent2};
    if (parent1.getGenotype().getGenes().size() < 2 || parent2.getGenotype().getGenes().size() < 2)
    {
      // System.out.println("CROSSOVER not performed; crossover not implemented for parent size 1");
      return children;
    }


    Random rnd = new Random();
    if (rnd.nextInt(100) > 90)
    {
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


      //take a random gene from each parent and append to other parent at random location


      genotype1.getGenes().add(gene2CLONE);
      genotype1.getGenes().get(crossoverPoint1).addEdge(genotype1.getGenes().size() - 1);
      setParentJoint(genotype1.getGenes().get(crossoverPoint1), gene2CLONE);


      genotype2.getGenes().add(gene1CLONE);
      genotype2.getGenes().get(crossoverPoint2).addEdge(genotype2.getGenes().size() - 1);
      setParentJoint(genotype2.getGenes().get(crossoverPoint2), gene1CLONE);


      children[0] = parent1;
      children[1] = parent2;
    }

      return children;
    }


  //use mutations to improve
  private void mutation(Being individual)
{
  Vector3f vector3f = new Vector3f();
  Random rand = new Random();
  float scaleFactor = rand.nextBoolean() ? 1.05f : 0.95f;
  // only mutate diminution
  individual.getGenotype().getRoot().getDimensions(vector3f);

  vector3f.x *= scaleFactor;
  vector3f.y *= scaleFactor;
  vector3f.z *= scaleFactor;

  if (Block.min(vector3f) < 0.5f)
  {
    return;
  }

  if (Block.max(vector3f) > 10 * Block.min(vector3f))
  {
    return;
  }

  individual.getGenotype().getRoot().setDimensions(vector3f);

  for (Gene neighbor : individual.getGenotype().neighbors(individual.getGenotype().getRoot()))
  {
    neighbor.getEffector().getParent(vector3f);
    vector3f.x *= scaleFactor;
    vector3f.y *= scaleFactor;
    vector3f.z *= scaleFactor;
    neighbor.getEffector().setParent(vector3f);
  }

}


  //Runs through all phases of GA
  private Vector<Being> createNextGeneration(Vector<Being> population)
  {
    Vector<Being> nextGeneration = new Vector();
    //select parents for next generation
    Vector<Being> newParents = selection3(population); //use selection3 method for tournament size 3 instead of 2
    Random rnd = new Random();
    Being parent1;
    Being parent2;


    //elitism: put one copy of unaltered most fit being and one mutated version of most fit being directly into nextGen
    //pick random pairs of parents from population pool

    nextGeneration.add(this.bestBeing);
    this.bestBeing.setAge(this.bestBeing.getAge() + 1);

    Being mutatedBest = this.bestBeing.clone();
    mutation(mutatedBest);
    nextGeneration.add(mutatedBest);
    mutatedBest.setAge(mutatedBest.getAge() + 1);

    //Pick pairs of parents randomly for breeding
    while (newParents.size() > 2)
    {
      int parent1index = rnd.nextInt(newParents.size());

      parent1 = newParents.get(parent1index);

      newParents.remove(newParents.get(parent1index));

      int parent2index = rnd.nextInt(newParents.size());

      parent2 = newParents.get(parent2index);


      newParents.remove(newParents.get(parent2index));

      //Perform crossover on selected parents (pctCrossover percent of the time) and replace parents with children;
      //crossover produces 2 children per set of parents
      if (rnd.nextInt(100) < this.pctCrossover)
      {
        Being[] children = crossover(parent1, parent2);  //use crossover2 method for simpler, but fast-growing crossover
        parent1 = children[0];
        parent2 = children[1];

        parent1.setChildren(parent1.getChildren() + 1);
        parent2.setChildren(parent1.getChildren() + 1);

      }
      //If no crossover for this pair, these parents move on to the next generation
      else
      {
        parent1.setAge(parent1.getAge() + 1);
        parent2.setAge(parent1.getAge() + 1);

      }
      //Perform mutation operation on parent1 pctMutation percent of the time
      if (rnd.nextInt(100) < this.pctMutations)
      {
        mutation(parent1);
      }

      //Perform mutation operation on parent2 pctMutation percent of the time
      if (rnd.nextInt(100) < this.pctMutations)
      {
        mutation(parent2);
      }

      //add two resulting individuals to next generation
      nextGeneration.add(parent1);
      nextGeneration.add(parent2);
    }


    return nextGeneration;
  }

  //getter
  public int currGen()
  {
    return generationNumber;
  }


  /**
   *
   * @param beings  Portion of population of Beings GA is currently evolving
   * @param population  Full population of Beings available to be evolved
   * @return  evolved population of Beings
   */
  public Population evolvePopulation(Subpopulation beings, Population population)
  {
    boolean firstBeing = true;
    System.out.println("EVOLVE POPULATION, Generation: " + this.generationNumber);
    generationNumber++;
    float averageFitness;
    beings.getPopulation().setGenerations(beings.getPopulation().getGenerations() + 1);


    Vector<Being> currentGeneration = new Vector<>();
    // Need to do this //
    currentGeneration.addAll(beings.getPopulation().getBeings());
   // currentGeneration.addAll(population.getBeings());


    this.populationSize = beings.getPopulation().size();
   // this.populationSize = population.size();

    for(Being being: currentGeneration)
    {
      being.setAge(0);
      being.setChildren(0);
     // simulation.beginEvaluation(being);
     // while (true)
     // {
     //   if (!being.isUnderEvaluation())
     //   {
     //     break;
     //   }
     // }


      // printBeing(individual);
     // float fitness = being.getFitness();
     // System.out.println("Evaluation complete...");
    }

    Vector<Being> nextGeneration;
    float summedFitness;
    System.out.println("Next gen from " + beings.getName());

    do
    {
      summedFitness = 0f;
      averageFitness = 0f;

      this.currentGenBestBeing  = currentGeneration.get(0);
      nextGeneration = createNextGeneration(currentGeneration);
      System.out.println("Generation: " + this.generationNumber);
      this.generationNumber++;
 //     for (int i = 0; i < 10; i++)
 //     {
//        Being individual = nextGeneration.get(i);

     for (Being individual : nextGeneration)
     {

        simulation.beginEvaluation(individual);
        while (true)
        {
          if (!individual.isUnderEvaluation())
          {
            break;
          }
        }


       // printBeing(individual);
        float fitness = individual.getFitness();
        System.out.println("Evaluation complete...");

         if(firstBeing)
         {
           this.firstGenAvgFitness = fitness;
           firstBeing = false;

         }

        summedFitness = summedFitness + fitness;
        if (fitness > this.currentGenBestFitness)
        {
          this.currentGenBestFitness = fitness;
          this.currentGenBestBeing = individual;
        }
        if (fitness > this.bestFitness)
        {
          this.bestFitness = fitness;
          this.bestBeing = individual;
        }
        beings.getPopulation().setBestFitness(Math.max(fitness, beings.getPopulation().getBestFitness()));
        population.setBestFitness(Math.max(fitness, population.getBestFitness()));

       if(beings.getPopulation().getBestBeing() == null || fitness > beings.getPopulation().getBestBeing().getFitness())
       {
         beings.getPopulation().setBestBeing(individual);
       }
       if(population.getBestBeing() == null || fitness > population.getBestBeing().getFitness())
       {
         population.setBestBeing(individual);
       }


       beings.getPopulation().setLifetimeOffspring(beings.getPopulation().getLifetimeOffspring() + 1);
       beings.getPopulation().setTotalLifetimeFitness(beings.getPopulation().getTotalLifetimeFitness() + fitness);
       beings.getPopulation().setPastAverageFitness(beings.getPopulation().getAverageFitness());
       beings.getPopulation().setAverageFitness(beings.getPopulation().getTotalLifetimeFitness() / beings.getPopulation().getLifetimeOffspring());


       population.setLifetimeOffspring(population.getLifetimeOffspring() + 1);
       population.setTotalLifetimeFitness(population.getTotalLifetimeFitness() + fitness);
       population.setPastAverageFitness(population.getAverageFitness());
       population.setAverageFitness(population.getTotalLifetimeFitness() / population.getLifetimeOffspring());

     }

      System.out.println("Beings best fitness " + population.getBestFitness());
      System.out.println("Beings total offspring " + population.getLifetimeOffspring());
      System.out.println("Beings total fitness " + population.getTotalLifetimeFitness());
      System.out.println("Beings average fitness" + population.getAverageFitness());
      System.out.println("Beings best being ");
      //printBeing(beings.getPopulation().getBestBeing());
      this.currentGenAverageFitness = summedFitness / nextGeneration.size();
      if(generationNumber == 1)
      {
        this.firstGenAvgFitness = this.currentGenAverageFitness;
      }
      printFitnessStats(nextGeneration);

      currentGeneration.clear();

      // Need to do this //
      currentGeneration.addAll(nextGeneration);


    }
    while (generationNumber < MAX_ITERATIONS);

    //for (int i = 0; i < Math.min(population.size(), generationNumber); i++)
    for (int i = 0; i < Math.min(population.size(), nextGeneration.size()); i++)
    {
      if (population.get(i).getFitness() < nextGeneration.get(i).getFitness())
      {
        System.out.println("Replacing with more fit");
        population.replace(i, nextGeneration.get(i));
      }
    }
    System.out.println("/*-*-*-Evolved population complete-*-*-*/");
    //population.setAverageFitness(this.currentGenAverageFitness);
    //population.setBestFitness(this.bestFitness);

    generationNumber = 0;
    simulation.setGenerationSpawn(false);
    return null;
  }

}


