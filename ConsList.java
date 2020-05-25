// Assignment 9
// Yusuf Hasanat
// yusufh
// Cheng Jessica
// jcheng

import java.util.Iterator;

//to represent a non-empty list of T
class ConsList<T> implements IList<T> {
  T first;
  IList<T> rest;

  ConsList(T first, IList<T> rest) {
    this.first = first;
    this.rest = rest;
  }

  // makes this ConsList<T> iterable
  public Iterator<T> iterator() {
    return new ListIterator<T>(this);
  }

  // determines if this ConsList<T> is a ConsList<T>
  public boolean isCons() {
    return true;
  }

  // casts this Conslist<T> as a ConsList
  public ConsList<T> asCons() {
    return this;
  }

  // adds the given item to this list
  public IList<T> add(T t) {
    return new ConsList<T>(t, this);
  }

}