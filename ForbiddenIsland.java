
// Assignment 9
// Yusuf Hasanat
// yusufh
// Cheng Jessica
// jcheng

import java.util.ArrayList;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

import javalib.impworld.World;
import javalib.impworld.WorldScene;
import javalib.worldimages.WorldEnd;

//Represents a ForbiddenIslandWorld
class ForbiddenIslandWorld extends World {
  // Defines an int constant
  static final int ISLAND_SIZE = 64;

  // All the cells of the game, including the ocean
  IList<Cell> board;

  // the current height of the ocean
  int waterHeight;

  // indicates the type of board. 1 is mountain, 2 is random heights, 3 is random
  // terrain
  int type;

  // tick count
  int count;

  // player
  Player player;

  // player 2
  Player2 player2;

  // list of helicopter pieces
  ArrayList<Target> helicopterpieces;

  // determines how many helicopter pieces are collected
  int collected;

  // represents a helicopter piece
  Target helicopterpiece1;
  Target helicopterpiece2;
  Target helicopterpiece3;
  Target helicopterpiece4;
  Target helicopterpiece5;

  // represents helicopter
  HelicopterTarget helicopter;

  ForbiddenIslandWorld(int type) {
    this.count = 0;
    this.waterHeight = 0;
    this.type = type;
    if (this.type == 1) {
      this.board = this.updateCells(assignedCells(this.cells(this.heights())));
    }
    else if (this.type == 2) {
      this.board = this.updateCells(assignedCells(this.randomCells(this.cells(this.heights()))));
    }
    else {
      this.board = this.updateCells(
          assignedCells(this.cells(new Utils(this.initrandomTerrainHeights()).hieghts)));
    }
    Cell c = this.randomCell();
    this.player = new Player(c.x, c.y);
    c = this.randomCell();
    this.player2 = new Player2(c.x, c.y);
    Cell h = this.highestCell();
    this.helicopter = new HelicopterTarget(h.x, h.y);
    c = this.randomCell();
    this.helicopterpiece1 = new Target(c.x, c.y);
    c = this.randomCell();
    this.helicopterpiece2 = new Target(c.x, c.y);
    c = this.randomCell();
    this.helicopterpiece3 = new Target(c.x, c.y);
    c = this.randomCell();
    this.helicopterpiece4 = new Target(c.x, c.y);
    c = this.randomCell();
    this.helicopterpiece5 = new Target(c.x, c.y);
    this.helicopterpieces = new ArrayList<Target>(
        Arrays.asList(this.helicopterpiece1, this.helicopterpiece2, this.helicopterpiece3,
            this.helicopterpiece4, this.helicopterpiece5, this.helicopter));
    this.collected = 6;

  }

  // gets the highest cell in the board
  public Cell highestCell() {
    for (Cell c : this.board) {
      if (c.height == ForbiddenIslandWorld.ISLAND_SIZE / 2 && !c.isCoastCell()) {
        return c;
      }
    }
    return highestCell();
  }

  // returns a random cell
  public Cell randomCell() {
    ArrayList<Cell> available = this.randomCellHelper();
    return available.get(new Random().nextInt(available.size()));

  }

  // helper for randomCell
  ArrayList<Cell> randomCellHelper() {
    ArrayList<Cell> temp = new ArrayList<Cell>();
    for (Cell c : this.board) {
      if (!c.isFlooded) {
        temp.add(c);
      }
    }
    return temp;
  }

  // represents the heights of every cell in the game
  ArrayList<ArrayList<Double>> heights() {
    ArrayList<ArrayList<Double>> result1 = new ArrayList<ArrayList<Double>>();
    for (int i = 0; i < ForbiddenIslandWorld.ISLAND_SIZE; i = i + 1) {
      ArrayList<Double> result = new ArrayList<Double>();
      for (int j = 0; j < ForbiddenIslandWorld.ISLAND_SIZE; j = j + 1) {
        result.add(ForbiddenIslandWorld.ISLAND_SIZE * 1.0 / 2
            - ((Math.abs(ForbiddenIslandWorld.ISLAND_SIZE * 1.0 / 2 - i))
                + (Math.abs(ForbiddenIslandWorld.ISLAND_SIZE * 1.0 / 2 - j))));
      }
      result1.add(result);
    }
    return result1;
  }

  // represents every cell in the mountain game
  ArrayList<ArrayList<Cell>> cells(ArrayList<ArrayList<Double>> d) {
    ArrayList<ArrayList<Cell>> result1 = new ArrayList<ArrayList<Cell>>();
    for (int i = 0; i < d.size(); i = i + 1) {
      ArrayList<Cell> result = new ArrayList<Cell>();
      for (int j = 0; j < d.get(i).size(); j = j + 1) {
        double height = d.get(i).get(j);
        if (height <= 0.0) {
          result.add(new OceanCell(height, i, j));
        }
        else {
          result.add(new Cell(height, i, j));
        }
      }
      result1.add(result);
    }
    return result1;
  }

  // makes a list of cells with random heights
  ArrayList<ArrayList<Cell>> randomCells(ArrayList<ArrayList<Cell>> c) {
    ArrayList<ArrayList<Cell>> result1 = new ArrayList<ArrayList<Cell>>();
    for (int i = 0; i < c.size(); i = i + 1) {
      ArrayList<Cell> result = new ArrayList<Cell>();
      for (int j = 0; j < c.get(i).size(); j = j + 1) {
        double height = heights().get(i).get(j);
        Cell a = c.get(i).get(j);
        if (height <= 0.0) {
          result.add(a);
        }
        else {
          a.height = new Random().nextInt(ForbiddenIslandWorld.ISLAND_SIZE / 2) + 1 * 1.0;
          result.add(a);
        }
      }
      result1.add(result);
    }
    return result1;
  }

  // initializes the heights of the random terrain
  ArrayList<ArrayList<Double>> initrandomTerrainHeights() {
    ArrayList<ArrayList<Double>> result = new ArrayList<ArrayList<Double>>();
    for (int i = 0; i < ForbiddenIslandWorld.ISLAND_SIZE + 1; i = i + 1) {
      ArrayList<Double> result2 = new ArrayList<Double>();
      for (int j = 0; j < ForbiddenIslandWorld.ISLAND_SIZE + 1; j = j + 1) {
        result2.add(0.0);
      }
      result.add(result2);
    }
    result.get(0).set(ForbiddenIslandWorld.ISLAND_SIZE / 2, 1.0);
    result.get(ForbiddenIslandWorld.ISLAND_SIZE / 2).set(0, 1.0);
    result.get(ForbiddenIslandWorld.ISLAND_SIZE / 2).set(ForbiddenIslandWorld.ISLAND_SIZE / 2,
        (double) (ForbiddenIslandWorld.ISLAND_SIZE / 2));
    result.get(ForbiddenIslandWorld.ISLAND_SIZE / 2).set(ForbiddenIslandWorld.ISLAND_SIZE, 1.0);
    result.get(ForbiddenIslandWorld.ISLAND_SIZE).set(ForbiddenIslandWorld.ISLAND_SIZE / 2, 1.0);
    return result;
  }

  // Assigns the neighboring cells
  ArrayList<ArrayList<Cell>> assignedCells(ArrayList<ArrayList<Cell>> links) {
    for (int i = 0; i < links.size(); i = i + 1) {
      for (int j = 0; j < links.get(i).size(); j = j + 1) {
        Cell c = links.get(i).get(j);
        if (i <= 0) {
          c.left = c;
        }
        else {
          c.left = links.get(i - 1).get(j);
        }
        if (i >= ForbiddenIslandWorld.ISLAND_SIZE - 1) {
          c.right = c;
        }
        else {
          c.right = links.get(i + 1).get(j);
        }
        if (j <= 0) {
          c.top = c;
        }
        else {
          c.top = links.get(i).get(j - 1);
        }
        if (j >= ForbiddenIslandWorld.ISLAND_SIZE - 1) {
          c.bottom = c;
        }
        else {
          c.bottom = links.get(i).get(j + 1);
        }
      }
    }
    return links;
  }

  // Effect: makes the board contain the cells in this game
  IList<Cell> updateCells(ArrayList<ArrayList<Cell>> cells) {
    IList<Cell> result = new MtList<Cell>();
    for (int i = 0; i < cells.size(); i = i + 1) {
      for (int j = 0; j < cells.get(i).size(); j = j + 1) {
        result = new ConsList<Cell>(cells.get(i).get(j), result);
      }
    }
    return result;
  }

  // creates a scene of the game
  public WorldScene makeScene() {
    WorldScene ws = new WorldScene(ForbiddenIslandWorld.ISLAND_SIZE,
        ForbiddenIslandWorld.ISLAND_SIZE);
    for (Cell c : this.board) {
      ws.placeImageXY(c.drawCell(waterHeight), c.x * 10, c.y * 10);
    }
    for (Target t : this.helicopterpieces) {
      ws.placeImageXY(t.drawPiece(), t.x * 10, t.y * 10);
    }
    ws.placeImageXY(this.player.drawPerson(), player.x * 10, player.y * 10);
    ws.placeImageXY(this.player2.drawPerson2(), player2.x * 10, player2.y * 10);
    return ws;
  }

  // sets the game to a specific terrain
  public void onKeyEvent(String ke) {
    if (ke.equals("m")) {
      ForbiddenIslandWorld f = new ForbiddenIslandWorld(1);
      this.count = 0;
      this.waterHeight = 0;
      this.type = f.type;
      this.board = f.board;
      this.player = f.player;
      this.player2 = f.player2;
      this.helicopterpieces = f.helicopterpieces;
    }
    else if (ke.equals("r")) {
      ForbiddenIslandWorld f = new ForbiddenIslandWorld(2);
      this.count = 0;
      this.waterHeight = 0;
      this.type = f.type;
      this.board = f.board;
      this.player = f.player;
      this.player2 = f.player2;
      this.helicopterpieces = f.helicopterpieces;
    }
    else if (ke.equals("t")) {
      ForbiddenIslandWorld f = new ForbiddenIslandWorld(3);
      this.count = 0;
      this.waterHeight = 0;
      this.type = f.type;
      this.board = f.board;
      this.player = f.player;
      this.player2 = f.player2;
      this.helicopterpieces = f.helicopterpieces;
    }
    if (this.canMoveTo(this.player.x, this.player.y)
        && this.canMoveTo(this.player2.x, this.player2.y)) {
      this.player.move(ke, this);
      this.player2.move2(ke, this);
    }
    collide();

  }

  // determines if the player can move to the x and y position of the island
  // position
  public boolean canMoveTo(int x, int y) {
    for (Cell c : this.board) {
      if (c.x == x && c.y == y) {
        return !c.isFlooded;
      }
    }
    return false;
  }

  // floods the cells every 10 ticks
  public void onTick() {
    this.count = count + 1;
    if (this.count % 10 == 0) {
      this.waterHeight = waterHeight + 1;
    }
    floodCells();
    worldEnds();

  }

  // ends the world if you win or loose
  public WorldEnd worldEnds() {
    if (this.collected == 0 && this.helicopter.isCollected) {
      return new WorldEnd(true, this.makeScene());
    }
    for (Cell c : this.board) {
      if (c.isFlooded && (c.isTouching(this.player) || c.isTouching2(this.player2))) {
        return new WorldEnd(true, this.makeScene());
      }
    }
    return new WorldEnd(false, this.makeScene());
  }

  // produces an array list of cells that are at the coast
  public IList<Cell> coast() {
    IList<Cell> coastline = new MtList<Cell>();
    Iterator<Cell> iter = this.board.iterator();
    while (iter.hasNext()) {
      Cell coastCell = iter.next();
      if (coastCell.isCoastCell()) {
        coastline = coastline.add(coastCell);
      }
    }
    return coastline;
  }

  // floods the cells
  void floodCells() {
    Iterator<Cell> iterate = this.coast().iterator();
    while (iterate.hasNext()) {
      Cell c = iterate.next();
      c.flood(waterHeight);
    }
  }

  // determines if the player hits a helicopter piece, is so, it turns to being
  // collected
  void collide() {
    for (Target t : this.helicopterpieces) {
      if (!t.isHelicopter && ((t.x == this.player.x && t.y == this.player.y)
          || (t.x == this.player2.x && t.y == this.player2.y))) {
        this.collected--;
        t.isCollected = true;
      }
      if (t.isHelicopter && this.collected == 1
          && ((t.x == this.player.x && t.y == this.player.y) || (t.isHelicopter
              && this.collected == 1 && t.x == this.player2.x && t.y == this.player2.y))) {
        this.collected = 0;
        this.helicopter.isCollected = true;
      }
    }
  }
}