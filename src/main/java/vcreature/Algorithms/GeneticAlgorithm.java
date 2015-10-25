package vcreature.Algorithms;

import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.PhysicsSpace;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.shadow.DirectionalLightShadowRenderer;
import com.jme3.system.AppSettings;
import com.jme3.texture.Texture;
import vcreature.*;
import vcreature.genotype.Genome;
import vcreature.genotype.Gene;
import vcreature.phenotype.Block;
import vcreature.phenotype.PhysicsConstants;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.Vector;

/**
 * @author Cari
 */
public class GeneticAlgorithm
{



  private int populationSize;
  private int pctMutations;
  private int pctCrossover;
  private int generationNumber;
  private double bestFitness;
  private Being bestBeing;
  private Population initialPopulation;




  GeneticAlgorithm(Population initPop)
  {
    this.populationSize = 200;
    this.pctCrossover = 90;
    this.pctMutations = 0;
    this.generationNumber = 0;
    this.bestFitness = 0;
    this.bestBeing = null;
    this.initialPopulation = initPop;

  }

  public Population getInitialPopulation()
  {
    return initialPopulation;
  }

  //TODO: make real fitness function
  //test fitness function; maximize surface area of all blocks; this is to test that the GA works to solve an optimization problem
  protected double calcFitness(Being individual)
  {
    double fitness = 0.0;
    double surfaceArea = 0.0;
    Genome genotype = individual.getGenotype();
    LinkedList<Gene> genes = genotype.getGenes();
    for(Gene gene : genes)
    {
      surfaceArea = surfaceArea +  2 * gene.getHeightY()*gene.getLengthX() + 2 * gene.getHeightY() * gene.getWidthZ() + 2 * gene.getLengthX() * gene.getWidthZ();
    }

    return surfaceArea;
  }


  private Vector<Being> selection (Vector<Being> population)
  {
    Vector<Being> newParents = new Vector();
    return newParents;
  }

  private void crossover(Being parent1, Being parent2)
  {

  }

  protected Being mutation(Being individual)
  {
    Genome genotype = individual.getGenotype();
    LinkedList<Gene> genes = genotype.getGenes();
    Gene testGene = genes.get(0);
    Random rnd = new Random();
    int x = rnd.nextInt(2);
    switch(x)
    {
      case 0: testGene.setHeightY(10.0f);
        break;
      case 1: testGene.setLengthX(10.0f);
        break;
      case 2: testGene.setWidthZ(10.0f);
        break;
    }
    return individual;
  }


  private Vector<Being> createNextGeneration(Vector<Being>population)
  {

    Vector<Being>nextPopulation = new Vector();

    return nextPopulation;
  }

  private void evolvePopulation()
  {

    double genBestFitness; //best fitness from current generation
    Being genBestBeing; ///most fit creature from current generation
    Vector<Being> currentGeneration= this.initialPopulation.getBeings();
    Vector<Being> nextGeneration = new Vector();
    while(generationNumber < 1)
    {
      double summedFitness = 0;
      double averageFitness = 0;
      genBestFitness = 0;
      genBestBeing = null;
      nextGeneration = createNextGeneration(currentGeneration);
      this.generationNumber++;
      for(Being individual : nextGeneration)
      {
        double fitness = calcFitness(individual);
        summedFitness = summedFitness + fitness;
        if(fitness > genBestFitness)
        {
          genBestFitness = fitness;
        }
        if(fitness > this.bestFitness)
        {
          this.bestFitness = fitness;
        }
      }

      averageFitness = summedFitness/nextGeneration.size();
    }
  }

}


