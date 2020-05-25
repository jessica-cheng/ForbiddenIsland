// Assignment 9
// Yusuf Hasanat
// yusufh
// Cheng Jessica
// jcheng

import java.util.Iterator;

// to represent a empty list of T
class MtList<T> implements IList<T> {

  // makes this MtList<T> iterable
  public Iterator<T> iterator() {
    return new ListIterator<T>(this);
  }

  // determines if this MtList<T> is a ConsList
  public boolean isCons() {
    return false;
  }

  // casts this MtList<T> as a ConsList
  public ConsList<T> asCons() {
    throw new ClassCastException("You can't cast an MtList as Cons");
  }

  // adds the given item to this list
  public IList<T> add(T t) {
    return new ConsList<T>(t, this);
  }

}