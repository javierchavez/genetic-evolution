package vcreature.Algorithms;


import vcreature.genotype.NeuralNode;
import vcreature.phenotype.EnumOperator;

public class NeuronClimbStrategy<V> implements HillClimbStrategy<NeuralNode, V>
{

  @Override
  public V climb(NeuralNode part)
  {

    //First Position
    EnumOperator pos = part.getOperators().get(NeuralNode.NeuralOperatorPosition.FIRST);

    //Non Unary
    if (pos == EnumOperator.ADD)
    {
      part.getOperators().put(NeuralNode.NeuralOperatorPosition.FIRST, EnumOperator.SUBTRACT);
    }
    else if (pos == EnumOperator.SUBTRACT)
    {
      part.getOperators().put(NeuralNode.NeuralOperatorPosition.FIRST, EnumOperator.MULTIPLY);
    }
    else if (pos == EnumOperator.MULTIPLY)
    {
      part.getOperators().put(NeuralNode.NeuralOperatorPosition.FIRST, EnumOperator.POWER);
    }
    else if (pos == EnumOperator.POWER)
    {
      part.getOperators().put(NeuralNode.NeuralOperatorPosition.FIRST, EnumOperator.MAX);
    }
    else if (pos == EnumOperator.MIN)
    {
      part.getOperators().put(NeuralNode.NeuralOperatorPosition.FIRST, EnumOperator.ARCTAN2);
    }
    //Unary
    else if (pos == EnumOperator.ABS)
    {
      part.getOperators().put(NeuralNode.NeuralOperatorPosition.FIRST, EnumOperator.IDENTITY);
    }
    else if (pos == EnumOperator.IDENTITY)
    {
      part.getOperators().put(NeuralNode.NeuralOperatorPosition.FIRST, EnumOperator.SIN);
    }
    else if (pos == EnumOperator.SIN)
    {
      part.getOperators().put(NeuralNode.NeuralOperatorPosition.FIRST, EnumOperator.SIGN);
    }
    else if (pos == EnumOperator.SIGN)
    {
      part.getOperators().put(NeuralNode.NeuralOperatorPosition.FIRST, EnumOperator.NEGATIVE);
    }
    else if (pos == EnumOperator.NEGATIVE)
    {
      part.getOperators().put(NeuralNode.NeuralOperatorPosition.FIRST, EnumOperator.LOG);
    }
    else if (pos == EnumOperator.LOG)
    {
      part.getOperators().put(NeuralNode.NeuralOperatorPosition.FIRST, EnumOperator.EXP);
    }
    else if (pos == EnumOperator.EXP)
    {
      part.getOperators().put(NeuralNode.NeuralOperatorPosition.FIRST, EnumOperator.ABS);
    }


    //Second Position
    pos = part.getOperators().get(NeuralNode.NeuralOperatorPosition.SECOND);

    //Non Unary
    if (pos == EnumOperator.ADD)
    {
      part.getOperators().put(NeuralNode.NeuralOperatorPosition.SECOND, EnumOperator.SUBTRACT);
    }
    else if (pos == EnumOperator.SUBTRACT)
    {
      part.getOperators().put(NeuralNode.NeuralOperatorPosition.SECOND, EnumOperator.MULTIPLY);
    }
    else if (pos == EnumOperator.MULTIPLY)
    {
      part.getOperators().put(NeuralNode.NeuralOperatorPosition.SECOND, EnumOperator.POWER);
    }
    else if (pos == EnumOperator.POWER)
    {
      part.getOperators().put(NeuralNode.NeuralOperatorPosition.SECOND, EnumOperator.MAX);
    }
    else if (pos == EnumOperator.MIN)
    {
      part.getOperators().put(NeuralNode.NeuralOperatorPosition.SECOND, EnumOperator.ARCTAN2);
    }
    //Unary
    else if (pos == EnumOperator.ABS)
    {
      part.getOperators().put(NeuralNode.NeuralOperatorPosition.SECOND, EnumOperator.IDENTITY);
    }
    else if (pos == EnumOperator.IDENTITY)
    {
      part.getOperators().put(NeuralNode.NeuralOperatorPosition.SECOND, EnumOperator.SIN);
    }
    else if (pos == EnumOperator.SIN)
    {
      part.getOperators().put(NeuralNode.NeuralOperatorPosition.SECOND, EnumOperator.SIGN);
    }
    else if (pos == EnumOperator.SIGN)
    {
      part.getOperators().put(NeuralNode.NeuralOperatorPosition.SECOND, EnumOperator.NEGATIVE);
    }
    else if (pos == EnumOperator.NEGATIVE)
    {
      part.getOperators().put(NeuralNode.NeuralOperatorPosition.SECOND, EnumOperator.LOG);
    }
    else if (pos == EnumOperator.LOG)
    {
      part.getOperators().put(NeuralNode.NeuralOperatorPosition.SECOND, EnumOperator.EXP);
    }
    else if (pos == EnumOperator.EXP)
    {
      part.getOperators().put(NeuralNode.NeuralOperatorPosition.SECOND, EnumOperator.ABS);
    }


    //Third Position
    pos = part.getOperators().get(NeuralNode.NeuralOperatorPosition.THIRD);

    //Non Unary
    if (pos == EnumOperator.ADD)
    {
      part.getOperators().put(NeuralNode.NeuralOperatorPosition.THIRD, EnumOperator.SUBTRACT);
    }
    else if (pos == EnumOperator.SUBTRACT)
    {
      part.getOperators().put(NeuralNode.NeuralOperatorPosition.THIRD, EnumOperator.MULTIPLY);
    }
    else if (pos == EnumOperator.MULTIPLY)
    {
      part.getOperators().put(NeuralNode.NeuralOperatorPosition.THIRD, EnumOperator.POWER);
    }
    else if (pos == EnumOperator.POWER)
    {
      part.getOperators().put(NeuralNode.NeuralOperatorPosition.THIRD, EnumOperator.MAX);
    }
    else if (pos == EnumOperator.MIN)
    {
      part.getOperators().put(NeuralNode.NeuralOperatorPosition.THIRD, EnumOperator.ARCTAN2);
    }
    //Unary
    else if (pos == EnumOperator.ABS)
    {
      part.getOperators().put(NeuralNode.NeuralOperatorPosition.THIRD, EnumOperator.IDENTITY);
    }
    else if (pos == EnumOperator.IDENTITY)
    {
      part.getOperators().put(NeuralNode.NeuralOperatorPosition.THIRD, EnumOperator.SIN);
    }
    else if (pos == EnumOperator.SIN)
    {
      part.getOperators().put(NeuralNode.NeuralOperatorPosition.THIRD, EnumOperator.SIGN);
    }
    else if (pos == EnumOperator.SIGN)
    {
      part.getOperators().put(NeuralNode.NeuralOperatorPosition.THIRD, EnumOperator.NEGATIVE);
    }
    else if (pos == EnumOperator.NEGATIVE)
    {
      part.getOperators().put(NeuralNode.NeuralOperatorPosition.THIRD, EnumOperator.LOG);
    }
    else if (pos == EnumOperator.LOG)
    {
      part.getOperators().put(NeuralNode.NeuralOperatorPosition.THIRD, EnumOperator.EXP);
    }
    else if (pos == EnumOperator.EXP)
    {
      part.getOperators().put(NeuralNode.NeuralOperatorPosition.THIRD, EnumOperator.ABS);
    }




    //Fourth Position
    pos = part.getOperators().get(NeuralNode.NeuralOperatorPosition.FOURTH);

    //Non Unary
    if (pos == EnumOperator.ADD)
    {
      part.getOperators().put(NeuralNode.NeuralOperatorPosition.FOURTH, EnumOperator.SUBTRACT);
    }
    else if (pos == EnumOperator.SUBTRACT)
    {
      part.getOperators().put(NeuralNode.NeuralOperatorPosition.FOURTH, EnumOperator.MULTIPLY);
    }
    else if (pos == EnumOperator.MULTIPLY)
    {
      part.getOperators().put(NeuralNode.NeuralOperatorPosition.FOURTH, EnumOperator.POWER);
    }
    else if (pos == EnumOperator.POWER)
    {
      part.getOperators().put(NeuralNode.NeuralOperatorPosition.FOURTH, EnumOperator.MAX);
    }
    else if (pos == EnumOperator.MIN)
    {
      part.getOperators().put(NeuralNode.NeuralOperatorPosition.FOURTH, EnumOperator.ARCTAN2);
    }
    //Unary
    else if (pos == EnumOperator.ABS)
    {
      part.getOperators().put(NeuralNode.NeuralOperatorPosition.FOURTH, EnumOperator.IDENTITY);
    }
    else if (pos == EnumOperator.IDENTITY)
    {
      part.getOperators().put(NeuralNode.NeuralOperatorPosition.FOURTH, EnumOperator.SIN);
    }
    else if (pos == EnumOperator.SIN)
    {
      part.getOperators().put(NeuralNode.NeuralOperatorPosition.FOURTH, EnumOperator.SIGN);
    }
    else if (pos == EnumOperator.SIGN)
    {
      part.getOperators().put(NeuralNode.NeuralOperatorPosition.FOURTH, EnumOperator.NEGATIVE);
    }
    else if (pos == EnumOperator.NEGATIVE)
    {
      part.getOperators().put(NeuralNode.NeuralOperatorPosition.FOURTH, EnumOperator.LOG);
    }
    else if (pos == EnumOperator.LOG)
    {
      part.getOperators().put(NeuralNode.NeuralOperatorPosition.FOURTH, EnumOperator.EXP);
    }
    else if (pos == EnumOperator.EXP)
    {
      part.getOperators().put(NeuralNode.NeuralOperatorPosition.FOURTH, EnumOperator.ABS);
    }

    return null;
  }
}
