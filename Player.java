
// Assignment 9
// Yusuf Hasanat
// yusufh
// Cheng Jessica
// jcheng

import javalib.worldimages.FromFileImage;
import javalib.worldimages.WorldImage;

//represents the player class
class Player {
  int x;
  int y;

  Player(int x, int y) {
    this.x = x;
    this.y = y;
  }

  // drawers the player
  WorldImage drawPerson() {
    return new FromFileImage("pilot-icon.png");
  }

  // moves the player by increments of 1 cell
  void move(String ke, ForbiddenIslandWorld f) {
    if (ke.equals("up") && f.canMoveTo(x, y - 1)) {
      this.y = this.y - 1;
    }
    else if (ke.equals("down") && f.canMoveTo(x, y + 1)) {
      this.y = this.y + 1;
    }
    else if (ke.equals("left") && f.canMoveTo(x - 1, y)) {
      this.x = this.x - 1;
    }
    else if (ke.equals("right") && f.canMoveTo(x + 1, y)) {
      this.x = this.x + 1;
    }
    else {
      return;
    }
  }
}

class Player2 {
  int x;
  int y;

  Player2(int x, int y) {
    this.x = x;
    this.y = y;
  }

  // drawers the player
  WorldImage drawPerson2() {
    return new FromFileImage("pilot-icon.png");
  }

  // moves the player by increments of 1 cell
  void move2(String ke, ForbiddenIslandWorld f) {
    if (ke.equals("w") && f.canMoveTo(x, y - 1)) {
      this.y = this.y - 1;
    }
    else if (ke.equals("s") && f.canMoveTo(x, y + 1)) {
      this.y = this.y + 1;
    }
    else if (ke.equals("a") && f.canMoveTo(x - 1, y)) {
      this.x = this.x - 1;
    }
    else if (ke.equals("d") && f.canMoveTo(x + 1, y)) {
      this.x = this.x + 1;
    }
    else {
      return;
    }
  }
}