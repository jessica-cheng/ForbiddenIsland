
// Assignment 9
// Yusuf Hasanat
// yusufh
// Cheng Jessica
// jcheng

import java.util.ArrayList;
import java.awt.Color;

import java.util.Arrays;
import java.util.Iterator;

import javalib.worldimages.CircleImage;
import javalib.worldimages.EmptyImage;
import javalib.worldimages.FromFileImage;
import javalib.worldimages.OutlineMode;
import javalib.worldimages.RectangleImage;
import javalib.worldimages.WorldImage;
import tester.Tester;

//tests the game
class ExampleForbiddenIslandGame {
  // private static final int ISLAND_SIZE = 64;

  void testGame(Tester t) {
    ForbiddenIslandWorld g = new ForbiddenIslandWorld(3);
    g.bigBang((ForbiddenIslandWorld.ISLAND_SIZE - 1) * 10,
        (ForbiddenIslandWorld.ISLAND_SIZE - 1) * 10, 0.5);
    System.out.println("test");
  }
}

// To represent examples of forbidden Islands
class ExamplesIslands {

  ForbiddenIslandWorld island1;
  ForbiddenIslandWorld island2;
  ForbiddenIslandWorld island3;

  Cell cell1;
  Cell cell2;
  Cell cell3;

  ArrayList<Cell> cells;

  ArrayList<ArrayList<Cell>> totalcells;

  IList<Cell> board;
  IList<Cell> cells1;
  IList<Cell> cells2;

  WorldImage drawcell;
  WorldImage drawcell2;
  WorldImage drawcell3;

  Iterator<Cell> iter;

  Player playeratcenter;
  Player playerattop;
  Player playeratbottom;
  Player playeratleft;
  Player playeratright;
  Player player;
  Player2 player2;

  Target helicopterpiece1;
  HelicopterTarget helicopterpiece2;
  Target helicopterpiece3;
  HelicopterTarget helicopterpiece4;

  // initializes the data
  void initData() {

    // random height island
    this.island1 = new ForbiddenIslandWorld(2);

    // normal island
    this.island2 = new ForbiddenIslandWorld(1);

    // random terrain island
    this.island3 = new ForbiddenIslandWorld(3);

    this.drawcell = new RectangleImage(10, 10, OutlineMode.SOLID, Color.BLUE);
    this.drawcell2 = new RectangleImage(10, 10, OutlineMode.SOLID, new Color(80, 180, 80));
    this.drawcell3 = new RectangleImage(10, 10, OutlineMode.SOLID, new Color(230, 185, 50));

    this.cell1 = new OceanCell(0.0, 0, 0);
    this.cell2 = new Cell(16.0, 0, 1);
    this.cell3 = new Cell(10.0, 0, 1);

    this.cell2.top = this.cell1;
    this.cell2.bottom = this.cell1;
    this.cell2.left = this.cell1;
    this.cell2.right = this.cell1;

    this.cell1.top = this.cell2;
    this.cell1.bottom = this.cell2;
    this.cell1.left = this.cell2;
    this.cell1.right = this.cell2;

    this.cell3.top = this.cell2;
    this.cell3.bottom = this.cell2;
    this.cell3.left = this.cell2;
    this.cell3.right = this.cell2;

    this.cells = new ArrayList<Cell>(Arrays.asList(this.cell1, this.cell2, this.cell3));
    this.totalcells = new ArrayList<ArrayList<Cell>>(
        Arrays.asList(this.cells, this.cells, this.cells));

    this.board = new ConsList<Cell>(this.cell3,
        new ConsList<Cell>(this.cell2,
            new ConsList<Cell>(this.cell1,
                new ConsList<Cell>(this.cell3,
                    new ConsList<Cell>(this.cell2,
                        new ConsList<Cell>(this.cell1,
                            new ConsList<Cell>(this.cell3, new ConsList<Cell>(this.cell2,
                                new ConsList<Cell>(this.cell1, new MtList<Cell>())))))))));

    this.cells1 = new ConsList<Cell>(this.cell3, new MtList<Cell>());
    this.cells2 = new ConsList<Cell>(this.cell1,
        new ConsList<Cell>(this.cell3, new MtList<Cell>()));

    this.iter = this.board.iterator();

    this.playeratcenter = new Player(32, 32);
    this.playerattop = new Player(32, 1);
    this.playeratbottom = new Player(32, 64);
    this.playeratleft = new Player(32, 1);
    this.playeratright = new Player(32, 1);
    this.player = new Player(0, 1);
    this.player2 = new Player2(32, 32);

    this.helicopterpiece1 = new Target(1, 1);
    this.helicopterpiece2 = new HelicopterTarget(1, 1);
    this.helicopterpiece3 = new Target(1, 1);
    this.helicopterpiece4 = new HelicopterTarget(1, 1);

    this.helicopterpiece1.isCollected = true;
    this.helicopterpiece2.isCollected = true;
    this.helicopterpiece3.isCollected = false;
    this.helicopterpiece4.isCollected = false;

  }

  // tests drawCell
  boolean testDrawCell(Tester t) {
    this.initData();
    return t.checkExpect(this.cell1.drawCell(0), this.drawcell)
        && t.checkExpect(this.cell2.drawCell(0), this.drawcell2)
        && t.checkExpect(this.cell3.drawCell(15), this.drawcell3);
  }

  // tests isCoastCell
  void testCoastCell(Tester t) {
    this.initData();
    t.checkExpect(this.cell1.isCoastCell(), false);
    t.checkExpect(this.cell2.isCoastCell(), true);
    t.checkExpect(this.cell3.isCoastCell(), false);
  }

  // test flood
  void testFlood(Tester t) {
    this.initData();
    this.cell2.flood(0);
    this.cell3.flood(15);
    this.cell1.flood(0);
    t.checkExpect(this.cell2.isFlooded, false);
    t.checkExpect(this.cell3.isFlooded, false);
    t.checkExpect(this.cell1.isFlooded, true);
  }

  // test isTouching
  boolean textIsTouching(Tester t) {
    this.initData();
    return t.checkExpect(this.cell1.isTouching(this.playeratcenter), false)
        && t.checkExpect(this.cell2.isTouching(this.player), true);

  }

  // tests draw Piece
  boolean testDrawPeice(Tester t) {
    return t.checkExpect(this.helicopterpiece1.drawPiece(), new EmptyImage())
        && t.checkExpect(this.helicopterpiece2.drawPiece(), new EmptyImage())
        && t.checkExpect(this.helicopterpiece3.drawPiece(),
            new CircleImage(5, OutlineMode.SOLID, Color.GRAY))
        && t.checkExpect(this.helicopterpiece4.drawPiece(), new FromFileImage("helicopter.png"));
  }

  // tests highest cell
  void testHighestCell(Tester t) {
    this.initData();
    t.checkExpect(this.island1.highestCell().height, (double) ForbiddenIslandWorld.ISLAND_SIZE / 2);
    t.checkExpect(this.island2.highestCell().height, (double) ForbiddenIslandWorld.ISLAND_SIZE / 2);
    t.checkExpect(this.island3.highestCell().height, (double) ForbiddenIslandWorld.ISLAND_SIZE / 2);
  }

  // tests random cell
  void testRandomCell(Tester t) {
    this.initData();
    this.island1.randomCell();
    t.checkExpect(this.island1.randomCell().isFlooded, false);
    for (Cell c : this.island1.randomCellHelper()) {
      t.checkExpect(c.isFlooded, false);
    }
  }

  // tests hieghts
  void testHieghts(Tester t) {
    this.initData();
    for (int i = 0; i < this.island1.heights().size(); i = i + 1) {
      for (int j = 0; j < this.island1.heights().get(i).size(); j = j + 1) {
        t.checkExpect(this.island1.heights().get(i).get(j),
            ForbiddenIslandWorld.ISLAND_SIZE * 1.0 / 2
            - (Math.abs(ForbiddenIslandWorld.ISLAND_SIZE * 1.0 / 2 - i)
                + (Math.abs(ForbiddenIslandWorld.ISLAND_SIZE * 1.0 / 2 - j))));
      }
    }
  }

  // tests cells
  void testCells(Tester t) {
    this.initData();
    for (int i = 0; i < this.island1.cells(this.island1.heights()).size(); i = i + 1) {
      for (int j = 0; j < this.island1.cells(this.island1.heights()).get(i).size(); j = j + 1) {
        Cell c = this.island1.cells(this.island1.heights()).get(i).get(j);
        t.checkExpect(c.x, i);
        t.checkExpect(c.y, j);
      }
    }

  }

  // tests randomCells
  void testRandomCells(Tester t) {
    this.initData();
    for (int i = 0; i < this.island1.randomCells(this.island1.cells(this.island1.heights()))
        .size(); i = i + 1) {
      for (int j = 0; j < this.island1.randomCells(this.island1.cells(this.island1.heights()))
          .get(i).size(); j = j + 1) {
        Cell c = this.island1.randomCells(this.island1.cells(this.island1.heights())).get(i).get(j);
        t.checkNumRange(c.height, -ForbiddenIslandWorld.ISLAND_SIZE * 1.0 / 2,
            ForbiddenIslandWorld.ISLAND_SIZE * 1.0 / 2);
        t.checkExpect(c.x, i);
        t.checkExpect(c.y, j);
      }
    }
  }

  // tests initrandomTerrainHeights
  void testInitrandomTerrainHeights(Tester t) {
    this.initData();
    for (int i = 0; i < this.island1.initrandomTerrainHeights().size(); i = i + 1) {
      for (int j = 0; j < this.island1.initrandomTerrainHeights().get(i).size(); j = j + 1) {
        Double d = this.island1.initrandomTerrainHeights().get(i).get(j);
        if (i == 0 && j == ForbiddenIslandWorld.ISLAND_SIZE / 2) {
          d = 1.0;
        }
        if (i == ForbiddenIslandWorld.ISLAND_SIZE / 2 && j == 0) {
          d = 1.0;
        }
        if (i == ForbiddenIslandWorld.ISLAND_SIZE / 2
            && j == ForbiddenIslandWorld.ISLAND_SIZE / 2) {
          d = (double) ForbiddenIslandWorld.ISLAND_SIZE / 2;
        }
        if (i == ForbiddenIslandWorld.ISLAND_SIZE / 2 && j == ForbiddenIslandWorld.ISLAND_SIZE) {
          d = 1.0;
        }
        if (i == ForbiddenIslandWorld.ISLAND_SIZE && j == ForbiddenIslandWorld.ISLAND_SIZE / 2) {
          d = 1.0;
        }
        else {
          d = 0.0;
        }
      }
    }
  }

  // tests assignedCells
  void testAssignedCells(Tester t) {
    this.initData();
    for (int i = 0; i < this.island1.assignedCells(this.island1.cells(this.island1.heights()))
        .size(); i = i + 1) {
      for (int j = 0; j < this.island1.assignedCells(this.island1.cells(this.island1.heights()))
          .get(i).size(); j = j + 1) {
        Cell c = this.island1.assignedCells(this.island1.cells(this.island1.heights())).get(i)
            .get(j);
        if (c.x <= 0) {
          c.left = c;
        }
        else {
          c.left = this.island1.assignedCells(this.island1.cells(this.island1.heights())).get(i - 1)
              .get(j);
        }
        if (c.x >= ForbiddenIslandWorld.ISLAND_SIZE - 1) {
          c.right = c;
        }
        else {
          c.right = this.island1.assignedCells(this.island1.cells(this.island1.heights()))
              .get(i + 1).get(j);
        }
        if (c.y <= 0) {
          c.top = c;
        }
        else {
          c.top = this.island1.assignedCells(this.island1.cells(this.island1.heights())).get(i)
              .get(j - 1);
        }
        if (c.y >= ForbiddenIslandWorld.ISLAND_SIZE - 1) {
          c.bottom = c;
        }
        else {
          c.bottom = this.island1.assignedCells(this.island1.cells(this.island1.heights())).get(i)
              .get(j + 1);
        }
      }
    }
  }

  // tests updateCells
  void testUpdateCell(Tester t) {
    this.initData();
    t.checkExpect(this.island1.updateCells(this.totalcells), this.board);
  }

  // tests makeScene *** Professor Leena said we don't need to ***

  // tests onKey
  void testOnKeyEvent(Tester t) {
    this.initData();
    this.island1.onKeyEvent("m");
    t.checkExpect(this.island1, new ForbiddenIslandWorld(1));
    this.island1.onKeyEvent("r");
    t.checkExpect(this.island1, new ForbiddenIslandWorld(2));
    this.island1.onKeyEvent("m");
    t.checkExpect(this.island1, new ForbiddenIslandWorld(1));
    this.island1.onKeyEvent("t");
    t.checkExpect(this.island1, new ForbiddenIslandWorld(3));
    this.island1.onKeyEvent("i");
    t.checkExpect(this.island1, new ForbiddenIslandWorld(2));
  }

  // test canMoveTo
  void testCanMoveTo(Tester t) {
    this.initData();
    t.checkExpect(this.island1.canMoveTo(1, 1), false);
    t.checkExpect(this.island2.canMoveTo(32, 32), true);
    t.checkExpect(this.island3.canMoveTo(0, 0), false);

  }

  // test move
  void testMove(Tester t) {
    this.initData();
    this.playeratcenter.move("up", this.island1);
    t.checkExpect(this.playeratcenter.x, 32);
    t.checkExpect(this.playeratcenter.y, 31);
    this.playeratcenter.move("down", this.island1);
    t.checkExpect(this.playeratcenter.x, 32);
    t.checkExpect(this.playeratcenter.y, 32);
    this.playeratcenter.move("left", this.island1);
    t.checkExpect(this.playeratcenter.x, 31);
    t.checkExpect(this.playeratcenter.y, 32);
    this.playeratcenter.move("right", this.island1);
    t.checkExpect(this.playeratcenter.x, 32);
    t.checkExpect(this.playeratcenter.y, 32);
    this.playerattop.move("up", this.island1);
    t.checkExpect(this.playerattop.x, 32);
    t.checkExpect(this.playerattop.y, 1);
    this.playeratbottom.move("bottom", this.island1);
    t.checkExpect(this.playeratbottom.x, 32);
    t.checkExpect(this.playeratbottom.y, 64);
    this.playeratleft.move("left", this.island1);
    t.checkExpect(this.playeratleft.x, 32);
    t.checkExpect(this.playeratleft.y, 1);
    this.playeratright.move("right", this.island1);
    t.checkExpect(this.playeratright.x, 32);
    t.checkExpect(this.playeratright.y, 1);
    this.player2.move2("w", this.island1);
    t.checkExpect(this.player2.y, 31);
    this.player2.move2("s", this.island1);
    t.checkExpect(this.player2.y, 32);
    this.player2.move2("a", this.island1);
    t.checkExpect(this.player2.x, 31);
    this.player2.move2("d", this.island1);
    t.checkExpect(this.player2.y, 32);
  }

  // tests collide
  void testCollide(Tester t) {
    this.initData();
    for (Target h : this.island1.helicopterpieces) {
      if (!h.isHelicopter && this.island1.player.x == h.x && this.island1.player.y == h.y) {
        t.checkExpect(h.isCollected, true);
        t.checkExpect(this.island1.collected, this.island1.collected - 1);
      }
      if (h.isHelicopter && this.island1.collected == 1 && h.x == this.island1.player.x
          && h.y == this.island1.player.y) {
        t.checkExpect(h.isCollected, true);
        t.checkExpect(this.island1.collected, this.island1.collected == 0);
      }
    }
  }

  // test onTick
  void testOnTick(Tester t) {
    this.initData();
    for (int i = 0; i < 20; i++) {
      if (i % 10 == 0) {
        this.island1.waterHeight = 1;
      }
    }
  }

  // tests coast
  void testCoast(Tester t) {
    this.initData();
    for (Cell c : this.island1.coast()) {
      t.checkExpect(c.top.isFlooded || c.bottom.isFlooded || c.left.isFlooded || c.right.isFlooded,
          true);
    }
  }

  // tests isCons
  boolean testIsCons(Tester t) {
    this.initData();
    return t.checkExpect(this.board.isCons(), true)
        && t.checkExpect(new MtList<Cell>().isCons(), false);
  }

  // tests asCons
  void testAsCons(Tester t) {
    this.initData();
    t.checkExpect(this.board.asCons(), this.board);
    t.checkException(new ClassCastException("You can't cast an MtList as Cons"), new MtList<Cell>(),
        "asCons");
  }

  // tests hasNext
  void testHasNext(Tester t) {
    this.initData();
    t.checkExpect(this.iter.hasNext(), true);
    t.checkExpect(this.iter.next(), this.cell3);
    t.checkExpect(this.iter.hasNext(), true);
    t.checkExpect(this.iter.next(), this.cell2);
    t.checkExpect(this.iter.hasNext(), true);
    t.checkExpect(this.iter.next(), this.cell1);
    t.checkExpect(this.iter.hasNext(), true);
    t.checkExpect(this.iter.next(), this.cell3);
    t.checkExpect(this.iter.hasNext(), true);
    t.checkExpect(this.iter.next(), this.cell2);
    t.checkExpect(this.iter.hasNext(), true);
    t.checkExpect(this.iter.next(), this.cell1);
    t.checkExpect(this.iter.hasNext(), true);
    t.checkExpect(this.iter.next(), this.cell3);
    t.checkExpect(this.iter.hasNext(), true);
    t.checkExpect(this.iter.next(), this.cell2);
    t.checkExpect(this.iter.hasNext(), true);
    t.checkExpect(this.iter.next(), this.cell1);
    t.checkExpect(this.iter.hasNext(), false);
  }

  // tests Next
  void testNext(Tester t) {
    this.initData();
    t.checkExpect(this.iter.next(), this.cell3);
    t.checkExpect(this.iter.next(), this.cell2);
    t.checkExpect(this.iter.next(), this.cell1);
    t.checkExpect(this.iter.next(), this.cell3);
    t.checkExpect(this.iter.next(), this.cell2);
    t.checkExpect(this.iter.next(), this.cell1);
    t.checkExpect(this.iter.next(), this.cell3);
    t.checkExpect(this.iter.next(), this.cell2);
    t.checkExpect(this.iter.next(), this.cell1);
    t.checkException(new ClassCastException("You can't cast an MtList as Cons"),
        new ListIterator<Cell>(new MtList<Cell>()), "next");
  }

  // tests drawPerson
  void testDrawPerson(Tester t) {
    this.initData();
    t.checkExpect(this.playeratbottom.drawPerson(), new FromFileImage("pilot-icon.png"));
    t.checkExpect(this.player2.drawPerson2(), new FromFileImage("pilot-icon.png"));
  }

  // tests randomize
  void testRandomize(Tester t) {
    this.initData();
    for (Cell c : this.island3.board) {
      t.checkNumRange(c.height, 0.0, (double) ForbiddenIslandWorld.ISLAND_SIZE / 2 + 1);
    }
  }

  // tests average
  void testAverage(Tester t) {
    this.initData();
    Double d = new Utils(this.island1.initrandomTerrainHeights()).average(1, 2);
    t.checkNumRange(d, -0.5, 0.5);
  }

  // tests averageMid
  void testAverageMid(Tester t) {
    this.initData();
    Double d = new Utils(this.island1.initrandomTerrainHeights()).averageMid(1, 2, 3, 4);
    t.checkNumRange(d, 0.5, 1.5);
  }

  // tests add
  boolean testAdd(Tester t) {
    this.initData();
    return t.checkExpect(this.cells1.add(this.cell1), this.cells2)
        && t.checkExpect(new MtList<Cell>().add(this.cell3), this.cells1);
  }

}
