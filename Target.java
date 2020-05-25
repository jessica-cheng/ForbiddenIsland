
// Assignment 9
// Yusuf Hasanat
// yusufh
// Cheng Jessica
// jcheng

import java.awt.Color;
import javalib.worldimages.CircleImage;
import javalib.worldimages.EmptyImage;
import javalib.worldimages.FromFileImage;
import javalib.worldimages.OutlineMode;
import javalib.worldimages.WorldImage;

// represents the helicopter class
//represents the helicopter class
class Target {
  int x;
  int y;
  boolean isHelicopter;
  boolean isCollected;

  Target(int x, int y) {
    this.x = x;
    this.y = y;
    this.isHelicopter = false;
    this.isCollected = false;
  }

  // draws the piece
  WorldImage drawPiece() {
    if (!this.isCollected) {
      return new CircleImage(5, OutlineMode.SOLID, Color.GRAY);
    }
    else {
      return new EmptyImage();
    }
  }
}

// represents the helicopter
class HelicopterTarget extends Target {

  HelicopterTarget(int x, int y) {
    super(x, y);
    this.isHelicopter = true;
  }

  // draws the target
  WorldImage drawPiece() {
    if (!this.isCollected) {
      return new FromFileImage("helicopter.png");
    }
    else {
      return new EmptyImage();
    }

  }
}
