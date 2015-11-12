package vcreature.morphology;


import vcreature.genotype.Genome;
import vcreature.genotype.NeuralInput;
import vcreature.genotype.NeuralNode;

public class NeuralNodeInputStrategy extends NeuralNetClimbStrategy
{
  public NeuralNodeInputStrategy(float w)
  {
    super(w);
  }

  public void climb(NeuralNode part)
  {
    Genome d = new Genome();
    // change the inputs of the node
    part.getInputs().get(NeuralInput.InputPosition.A);
    part.getInputs().put(NeuralInput.InputPosition.A, d.get(0).getAngleSensor());



  }
}
