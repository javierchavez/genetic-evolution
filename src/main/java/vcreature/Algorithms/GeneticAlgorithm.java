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
import vcreature.phenotype.Block;
import vcreature.phenotype.PhysicsConstants;

import java.util.ArrayList;
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




  GeneticAlgorithm()
  {
    this.populationSize = 20;
    this.pctCrossover = 90;
    this.pctMutations = 0;
    this.generationNumber = 0;
    this.bestFitness = 0;
    this.bestBeing = null;
  }

  //TODO: decouple physics from graphics
  private double calcFitness(Being individual)
  {
    Random rnd = new Random();
    return 1.0 * (rnd.nextInt(100));
  }
  //Temporary creation of random initial population; final project will take in results of hill-climbing algorithm
  private Vector<Being> genInitPopulation()
  {
    Vector<Being> initPopulation = new Vector();
    return initPopulation;
  }

  private Vector<Being> selection (Vector<Being> population)
  {
    Vector<Being> newParents = new Vector();
    return newParents;
  }

  private void crossover(Being parent1, Being parent2)
  {

  }

  private void mutation(Being individual)
  {

  }


  private Vector<Being> createNextGeneration(Vector<Being>population)
  {
    Vector<Being>initPopulation = new Vector();

    return initPopulation;
  }

  private void evolvePopulation()
  {

    double genBestFitness; //best fitness from current generation
    Being genBestBeing; ///most fit creature from current generation
    Vector<Being> currentGeneration = genInitPopulation();
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


