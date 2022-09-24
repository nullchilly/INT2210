import java.util.Scanner;

enum CellStatus {
  EMPTY, X, O;
}

class Cell {
  void change_status(CellStatus a) {
  }
}

class Board {
  // CellStatus[][] a = new CellStatus[3][3];
  CellStatus[][] a = new CellStatus[3][3];

  Board() {
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        a[i][j] = CellStatus.EMPTY;
      }
    }
  }

  void place(boolean turn) {
    Scanner scanner = new Scanner(System.in);
    int x = scanner.nextInt(), y = scanner.nextInt();
    --x;
    --y;
    if (x < 0 || y < 0 || x >= 3 || y >= 3 || a[x][y] != CellStatus.EMPTY) {
      System.out.print("Invalid move, input again: ");
      place(turn);
      return;
    }
    a[x][y] = turn ? CellStatus.X : CellStatus.O;
  }

  void display() {
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        // System.out.print("[" + a[x][y] + "]");
        System.out.print("[" + a[i][j] + "]");
      }
      System.out.println();
    }
  }

  boolean check() {
    for (int i = 0; i < 3; i++) {
      boolean row = true;
      for (int j = 1; j < 3; j++)
        if (a[i][j] != a[i][j - 1])
          row = false;
      if (a[i][0] != CellStatus.EMPTY && row)
        return true;
    }
    for (int j = 0; j < 3; j++) {
      boolean column = true;
      for (int i = 1; i < 3; i++)
        if (a[i][j] != a[i - 1][j])
          column = false;
      if (a[0][j] != " " && column)
        return true;
    }
    boolean diagonal1 = true, diagonal2 = true;
    for (int i = 1; i < 3; i++) {
      if (a[i][i] != a[i - 1][i - 1])
        diagonal1 = false;
      if (a[i][3 - i - 1] != a[i - 1][3 - i])
        diagonal2 = false;
    }
    if (a[0][0] != " " && a[0][2] != " " && (diagonal1 || diagonal2))
      return true;
    return false;
  }
}

public class Game {
  public static void main(String[] args) {
    System.out.println("Tic tac toe");
    Cell cell = new Cell();
    boolean turn = false;
    for (int i = 0; i < 9; i++) {
      turn = turn != true;
      if (turn)
        System.out.println("X turn");
      else
        System.out.println("O turn");
      cell.place(turn);
      cell.display();
      if (cell.check()) {
        System.out.println(turn ? "X win" : "O win");
        break;
      }
    }
  }
}