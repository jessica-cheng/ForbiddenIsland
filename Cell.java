// Assignment 9
// Yusuf Hasanat
// yusufh
// Cheng Jessica
// jcheng

import java.awt.Color;

import javalib.worldimages.OutlineMode;
import javalib.worldimages.RectangleImage;
import javalib.worldimages.WorldImage;

// Represents a single square of the game area
class Cell {
  // represents absolute height of this cell, in feet
  double height;
  // In logical coordinates, with the origin at the top-left corner of the screen
  int x;
  int y;
  // the four adjacent cells to this one
  Cell left;
  Cell top;
  Cell right;
  Cell bottom;
  // reports whether this cell is flooded or not
  boolean isFlooded;

  Cell(double height, int x, int y) {
    this.height = height;
    this.x = x;
    this.y = y;
    this.isFlooded = false;
  }

  // draws a cell depending on water height
  WorldImage drawCell(int waterHeight) {
    double ratio = Math.abs((int) (this.height - waterHeight) * 5);
    Color c;

    if (this.isFlooded) {
      c = new Color(0, 0, (int) Math.min(255, 255 - ratio));
    }
    else if (this.height <= waterHeight) {
      c = new Color((int) Math.max(200, Math.abs(255 - ratio)),
          (int) Math.min(200, Math.abs(200 - waterHeight)),
          (int) Math.min(50, Math.abs(255 - waterHeight)));
    }
    else {
      c = new Color((int) Math.min(255, ratio), (int) Math.min(255, 100 + ratio),
          (int) Math.min(255, ratio));
    }
    return new RectangleImage(10, 10, OutlineMode.SOLID, c);

  }

  // determines if this cell is a coast cell
  boolean isCoastCell() {
    return this.left.isFlooded || this.right.isFlooded || this.top.isFlooded
        || this.bottom.isFlooded && !this.isFlooded;
  }

  // floods the cell
  void flood(int waterHeight) {
    if (this.height <= waterHeight && (this.top.isFlooded || this.bottom.isFlooded
        || this.left.isFlooded || this.right.isFlooded)) {
      this.isFlooded = true;
    }

  }

  // determines if the cell is touching the player
  boolean isTouching(Player p) {
    return this.x == p.x && this.y == p.y;
  }
  
  // determines if the cell is touching player2
  boolean isTouching2(Player2 p) {
    return this.x == p.x && this.y == p.y;
  }

}

// Represents an Ocean cell
class OceanCell extends Cell {
  OceanCell(double height, int x, int y) {
    super(height, x, y);
    this.height = 0.0;
    this.isFlooded = true;
  }

  // draws the Ocean cell
  WorldImage drawCell(int waterHeight) {
    return new RectangleImage(10, 10, OutlineMode.SOLID, Color.BLUE);
  }

}