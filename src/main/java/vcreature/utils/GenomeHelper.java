package vcreature.utils;

import com.jme3.bullet.PhysicsSpace;
import com.jme3.collision.CollisionResults;
import com.jme3.scene.Node;
import vcreature.GenomeSynthesizer;
import vcreature.genotype.Genome;
import vcreature.phenotype.Block;
import vcreature.phenotype.Creature;


public final class GenomeHelper
{


  public static boolean isValid(PhysicsSpace physicsSpace, Node rootNode, Genome genon)
  {
    GenomeSynthesizer synthesizer = new GenomeSynthesizer(physicsSpace, rootNode);
    Creature creature = synthesizer.encode(genon);

    Block block1;
    Block block2;
    for (int i = 0; i < creature.getNumberOfBodyBlocks(); i++)
    {
      block1 = creature.getBlockByID(i);
      for(int j = 0; j < creature.getNumberOfBodyBlocks(); j++)
      {
        if (i != j)
        {
          block2 = creature.getBlockByID(j);
          if (getCollision(block1, block2).size() > 0)
          {
            creature.remove();
            return false;
          }
        }
      }
    }
    creature.remove();
    return true;
  }

  private static CollisionResults getCollision(Block block1, Block block2)
  {
    CollisionResults results = new CollisionResults();
    block1.getGeometry().collideWith(block2.getGeometry().getWorldBound(), results);
    return results;
  }
}
