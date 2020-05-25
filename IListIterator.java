// Assignment 9
// Yusuf Hasanat
// yusufh
// Cheng Jessica
// jcheng

import java.util.Iterator;

//to represent a ListIterator class
class ListIterator<T> implements Iterator<T> {
  IList<T> l;

  ListIterator(IList<T> l) {
    this.l = l;
  }

  // determines if the IList<T> has a next item. it has one if the next item is a
  // cons
  public boolean hasNext() {
    return this.l.isCons();
  }

  // gets the next item of the IList<T>
  // Effect: makes the rest of the IList<T> cons.rest
  public T next() {
    ConsList<T> cons = this.l.asCons(); // alternate way is to cast

    T tmp = cons.first;
    this.l = cons.rest;
    return tmp;
  }
}