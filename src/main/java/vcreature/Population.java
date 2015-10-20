package vcreature;


import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Population implements List<Being>
{

  @Override
  public int size()
  {
    return 0;
  }

  @Override
  public boolean isEmpty()
  {
    return false;
  }

  @Override
  public boolean contains(Object o)
  {
    return false;
  }

  @Override
  public Iterator<Being> iterator()
  {
    return null;
  }

  @Override
  public Object[] toArray()
  {
    return new Object[0];
  }

  @Override
  public <T> T[] toArray(T[] a)
  {
    return null;
  }

  @Override
  public boolean add(Being being)
  {
    return false;
  }

  @Override
  public boolean remove(Object o)
  {
    return false;
  }

  @Override
  public boolean containsAll(Collection<?> c)
  {
    return false;
  }

  @Override
  public boolean addAll(Collection<? extends Being> c)
  {
    return false;
  }

  @Override
  public boolean addAll(int index, Collection<? extends Being> c)
  {
    return false;
  }

  @Override
  public boolean removeAll(Collection<?> c)
  {
    return false;
  }

  @Override
  public boolean retainAll(Collection<?> c)
  {
    return false;
  }

  @Override
  public void clear()
  {

  }

  @Override
  public Being get(int index)
  {
    return null;
  }

  @Override
  public Being set(int index, Being element)
  {
    return null;
  }

  @Override
  public void add(int index, Being element)
  {

  }

  @Override
  public Being remove(int index)
  {
    return null;
  }

  @Override
  public int indexOf(Object o)
  {
    return 0;
  }

  @Override
  public int lastIndexOf(Object o)
  {
    return 0;
  }

  @Override
  public ListIterator<Being> listIterator()
  {
    return null;
  }

  @Override
  public ListIterator<Being> listIterator(int index)
  {
    return null;
  }

  @Override
  public List<Being> subList(int fromIndex, int toIndex)
  {
    return null;
  }
}
