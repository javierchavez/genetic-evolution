package vcreature;


public final class Attributes
{
  public static final int MAX_BLOCKS = 50;
  public static final float BREED_PERCENTAGE = 0.2f;
  public static final int SEED_MAX_BLOCKS = 12;
  public static final int SEED_MAX_SIZE = 10;
  public static final int SEED_MAX_CONSTANT = 20;
  public static final int SEED_MAX_RULES = 10;
  public static final int SEED_NEW_RANDOMS_GAP = 10;
  public static final float RANDOM_RESEED_PERCENTAGE = 0.2f;

  public static final float MAX_SIMILAR_PERCENTAGE = 0.8f;
  // How often to check the whole Population and eliminate excess duplicates.
  public static final float SIMILAR_CHECK_INTERVAL = 50;

  public static final float VERTICAL_OFFSET = 200f; // work around for joel's code
}
