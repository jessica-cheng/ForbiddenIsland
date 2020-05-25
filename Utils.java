// Assignment 9
// Yusuf Hasanat
// yusufh
// Cheng Jessica
// jcheng

import java.util.ArrayList;
import java.util.Random;

// to represent the utils Class
class Utils {
  ArrayList<ArrayList<Double>> hieghts;

  Utils(ArrayList<ArrayList<Double>> hieghts) {
    this.hieghts = hieghts;
    randomize(0, ForbiddenIslandWorld.ISLAND_SIZE, 0, ForbiddenIslandWorld.ISLAND_SIZE,
        this.hieghts);
  }

  // Effect: randomizes the doubles in the given array list
  void randomize(int x1, int x2, int y1, int y2, ArrayList<ArrayList<Double>> list) {
    if (list.get((x1 + x2) / 2).get(y1) == 0) {
      list.get((x1 + x2) / 2).set(y1, this.average(list.get(x1).get(y1), list.get(x2).get(y1)));
    }
    if (list.get(x1).get((y1 + y2) / 2) == 0) {
      list.get(x1).set((y1 + y2) / 2, this.average(list.get(x1).get(y1), list.get(x1).get(y2)));
    }
    if (list.get((x1 + x2) / 2).get(y2) == 0) {
      list.get((x1 + x2) / 2).set(y2, this.average(list.get(x1).get(y2), list.get(x2).get(y2)));
    }
    if (list.get(x2).get((y1 + y2) / 2) == 0) {
      list.get(x2).set((y1 + y2) / 2, this.average(list.get(x2).get(y1), list.get(x2).get(y2)));
    }
    if (list.get((x1 + x2) / 2).get((y1 + y2) / 2) == 0) {
      list.get((x1 + x2) / 2).set((y1 + y2) / 2, this.averageMid(list.get(x1).get(y1),
          list.get(x2).get(y1), list.get(x2).get(y1), list.get(x2).get(y2)));
    }
    if (x1 + 1 >= x2 || y1 + 1 >= y2) {
      return;
    }
    this.randomize(x1, (x1 + x2) / 2, y1, (y1 + y2) / 2, list);
    this.randomize((x1 + x2) / 2, x2, y1, (y1 + y2) / 2, list);
    this.randomize(x1, (x1 + x2) / 2, (y1 + y2) / 2, y2, list);
    this.randomize((x1 + x2) / 2, x2, (y1 + y2) / 2, y2, list);

  }

  // averages the two doubles and adds a random integer afterwards
  double average(double a, double b) {
    return (a + b) / 2 + new Random().nextDouble() - 2;
  }

  // averages the four doubles and adds a random integer afterwards
  double averageMid(double a, double b, double c, double d) {
    return (a + b + c + d) / 4 + new Random().nextDouble() - 2;
  }

}