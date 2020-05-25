// Assignment 9
// Yusuf Hasanat
// yusufh
// Cheng Jessica
// jcheng

// to represent a list interface
interface IList<T> extends Iterable<T> {
  // determines if the IList is a ConsList
  boolean isCons();

  // casts the IList as a cons
  ConsList<T> asCons();

  // adds the given item to this list
  IList<T> add(T t);
}