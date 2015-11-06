package vcreature.phenotype;

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


import com.jme3.bullet.PhysicsSpace;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;

import vcreature.phenotype.Block;
import vcreature.phenotype.Creature;
import vcreature.phenotype.EnumNeuronInput;
import vcreature.phenotype.Neuron;


/**
 * A variation on the flappy bird creature. Used for testing different algorithms and strategies
 */
public class FlappyBird4 extends Creature
{
  public FlappyBird4(PhysicsSpace physicsSpace, Node rootNode)
  {
    super(physicsSpace, rootNode);

    float[] rotations = new float[] {0,0,0};
    Vector3f torsoSize = new Vector3f( 3.0f, 3.0f, 3.0f);
    Vector3f leg1Size  = new Vector3f( 3.0f, 0.5f, 1.0f);
    Vector3f leg2Size  = new Vector3f( 3.0f, 0.5f, 1.0f);
    Vector3f leg3Size  = new Vector3f( 3.0f, 0.5f, 1.0f);
    Vector3f leg4Size  = new Vector3f( 3.0f, 0.5f, 1.0f);
    Vector3f leg5Size  = new Vector3f( 1.0f, 0.5f, 3.0f);
    Vector3f leg6Size  = new Vector3f( 1.0f, 0.5f, 3.0f);
    Vector3f leg7Size  = new Vector3f( 1.0f, 0.5f, 3.0f);
    Vector3f leg8Size  = new Vector3f( 1.0f, 0.5f, 3.0f);

    Block torso = addRoot(new Vector3f(0, 100, 0), torsoSize, rotations);
    torso.setMaterial(Block.MATERIAL_RED);

    Vector3f pivotA = new Vector3f( 3.0f, -3.0f, 1.5f);
    Vector3f pivotB = new Vector3f(-3.0f, 0.5f, 0.0f);
    Vector3f pivotAA = new Vector3f(3.0f, -3.0f, -1.5f);
    Vector3f pivotBB = new Vector3f(-3.0f, 0.5f, 0.0f);
    Block leg1 = addBlock(rotations, leg1Size, torso, pivotA,  pivotB, Vector3f.UNIT_Z);
    Block leg2 = addBlock(rotations, leg2Size, torso, pivotAA, pivotBB, Vector3f.UNIT_Z);

    Vector3f pivotC = new Vector3f(-3.0f, -3.0f, 1.5f);
    Vector3f pivotD = new Vector3f(3.0f, 0.5f, 0.0f);
    Vector3f pivotCC = new Vector3f(-3.0f, -3.0f, -1.5f);
    Vector3f pivotDD = new Vector3f(3.0f, 0.5f, 0.0f);
    Block leg3 = addBlock(rotations, leg3Size, torso, pivotC,  pivotD, Vector3f.UNIT_Z);
    Block leg4 = addBlock(rotations, leg4Size, torso, pivotCC, pivotDD, Vector3f.UNIT_Z);

    Vector3f pivotE = new Vector3f(1.5f, -3.0f, -3.0f);
    Vector3f pivotF = new Vector3f(0.0f, 0.5f, 3.0f);
    Vector3f pivotEE = new Vector3f(-1.5f, -3.0f, -3.0f);
    Vector3f pivotFF = new Vector3f(0.0f, 0.5f, 3.0f);
    Block leg5 = addBlock(rotations, leg5Size, torso, pivotE, pivotF, Vector3f.UNIT_X);
    Block leg6 = addBlock(rotations, leg6Size, torso, pivotEE, pivotFF, Vector3f.UNIT_X);

    Vector3f pivotG = new Vector3f(1.5f, -3.0f, 3.0f);
    Vector3f pivotH = new Vector3f(0.0f, 0.5f, -3.0f);
    Vector3f pivotGG = new Vector3f(-1.5f, -3.0f, 3.0f);
    Vector3f pivotHH = new Vector3f(0.0f, 0.5f, -3.0f);
    Block leg7 = addBlock(rotations, leg7Size, torso, pivotG, pivotH, Vector3f.UNIT_X);
    Block leg8 = addBlock(rotations, leg8Size, torso, pivotGG, pivotHH, Vector3f.UNIT_X);


    Neuron leg1Neuron1 = new Neuron(EnumNeuronInput.TIME, null, null, null, null);
    leg1Neuron1.setInputValue(Neuron.C, 5f);
    leg1Neuron1.setInputValue(Neuron.D, -Float.MAX_VALUE);

    Neuron leg1Neuron2 = new Neuron(EnumNeuronInput.TIME, null, null, null, null);
    leg1Neuron2.setInputValue(Neuron.C, 4f);
    leg1Neuron2.setInputValue(Neuron.D, Float.MAX_VALUE);

    leg1.addNeuron(leg1Neuron1);
    leg1.addNeuron(leg1Neuron2);

    Neuron leg2Neuron1 = new Neuron(EnumNeuronInput.TIME, null, null, null, null);
    leg2Neuron1.setInputValue(Neuron.C, 5f);
    leg2Neuron1.setInputValue(Neuron.D, -Float.MAX_VALUE);

    Neuron leg2Neuron2 = new Neuron(EnumNeuronInput.TIME, null, null, null, null);
    leg2Neuron2.setInputValue(Neuron.C, 4f);
    leg2Neuron2.setInputValue(Neuron.D, Float.MAX_VALUE);

    leg2.addNeuron(leg2Neuron1);
    leg2.addNeuron(leg2Neuron2);

    Neuron leg3Neuron1 = new Neuron(EnumNeuronInput.TIME, null, null, null, null);
    leg3Neuron1.setInputValue(Neuron.C, 5f);
    leg3Neuron1.setInputValue(Neuron.D, Float.MAX_VALUE);

    Neuron leg3Neuron2 = new Neuron(EnumNeuronInput.TIME, null, null, null, null);
    leg3Neuron2.setInputValue(Neuron.C, 4f);
    leg3Neuron2.setInputValue(Neuron.D, -Float.MAX_VALUE);

    leg3.addNeuron(leg3Neuron1);
    leg3.addNeuron(leg3Neuron2);

    Neuron leg4Neuron1 = new Neuron(EnumNeuronInput.TIME, null, null, null, null);
    leg4Neuron1.setInputValue(Neuron.C, 5f);
    leg4Neuron1.setInputValue(Neuron.D, Float.MAX_VALUE);

    Neuron leg4Neuron2 = new Neuron(EnumNeuronInput.TIME, null, null, null, null);
    leg4Neuron2.setInputValue(Neuron.C, 4f);
    leg4Neuron2.setInputValue(Neuron.D, -Float.MAX_VALUE);

    leg4.addNeuron(leg4Neuron1);
    leg4.addNeuron(leg4Neuron2);

    Neuron leg5Neuron1 = new Neuron(EnumNeuronInput.TIME, null, null, null, null);
    leg5Neuron1.setInputValue(Neuron.C, 5f);
    leg5Neuron1.setInputValue(Neuron.D, -Float.MAX_VALUE);

    Neuron leg5Neuron2 = new Neuron(EnumNeuronInput.TIME, null, null, null, null);
    leg5Neuron2.setInputValue(Neuron.C, 4f);
    leg5Neuron2.setInputValue(Neuron.D, Float.MAX_VALUE);

    leg5.addNeuron(leg5Neuron1);
    leg5.addNeuron(leg5Neuron2);

    Neuron leg6Neuron1 = new Neuron(EnumNeuronInput.TIME, null, null, null, null);
    leg6Neuron1.setInputValue(Neuron.C, 5f);
    leg6Neuron1.setInputValue(Neuron.D, -Float.MAX_VALUE);

    Neuron leg6Neuron2 = new Neuron(EnumNeuronInput.TIME, null, null, null, null);
    leg6Neuron2.setInputValue(Neuron.C, 4f);
    leg6Neuron2.setInputValue(Neuron.D, Float.MAX_VALUE);

    leg6.addNeuron(leg6Neuron1);
    leg6.addNeuron(leg6Neuron2);

    Neuron leg7Neuron1 = new Neuron(EnumNeuronInput.TIME, null, null, null, null);
    leg7Neuron1.setInputValue(Neuron.C, 5f);
    leg7Neuron1.setInputValue(Neuron.D, Float.MAX_VALUE);

    Neuron leg7Neuron2 = new Neuron(EnumNeuronInput.TIME, null, null, null, null);
    leg7Neuron2.setInputValue(Neuron.C, 4f);
    leg7Neuron2.setInputValue(Neuron.D, -Float.MAX_VALUE);

    leg7.addNeuron(leg7Neuron1);
    leg7.addNeuron(leg7Neuron2);

    Neuron leg8Neuron1 = new Neuron(EnumNeuronInput.TIME, null, null, null, null);
    leg8Neuron1.setInputValue(Neuron.C, 5f);
    leg8Neuron1.setInputValue(Neuron.D, Float.MAX_VALUE);

    Neuron leg8Neuron2 = new Neuron(EnumNeuronInput.TIME, null, null, null, null);
    leg8Neuron2.setInputValue(Neuron.C, 4f);
    leg8Neuron2.setInputValue(Neuron.D, -Float.MAX_VALUE);

    leg8.addNeuron(leg8Neuron1);
    leg8.addNeuron(leg8Neuron2);

    placeOnGround();
  }
}
