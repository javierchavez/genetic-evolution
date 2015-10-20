package vcreature.genotype;




public class HeightAllele extends AbstractAllele<HeightAllele, Float>
{
  public HeightAllele()
  {
    super.setAlleleType(this);
  }

  @Override
  public HeightAllele clone()
  {
    return null;
  }
}
