/*import zio._
import zio.Console._
import zio.nio._
import zio.nio.channels._
import zio.nio.file.Path
import java.io.IOException
import zio.json._*/

type SudokuGrid = Array[Array[Int]]

def solveSudoku(grid: SudokuGrid): Option[SudokuGrid] = {
  // Recursive backtracking function
  def solve(row: Int, col: Int): Option[SudokuGrid] = {
    // if all cells have been filled, then the solution is found
    if (row == 9) {
      Some(grid)
    // if we reached the end of a row, we move to the next row
    } else if (col == 9) {
      solve(row + 1, 0)
    // if a cell is already filled, we move to the next cell
    } else if (grid(row)(col) != 0) {
      solve(row, col + 1)
    // trying values from 1 to 9 for the last cell of value 0 value
    } else {
      // try all possible values for the current cell
      for (num <- 1 to 9) {
        // isValid to check that the current value is the correct one
        if (isValid(row, col, num)) {
          grid(row)(col) = num
          solve(row, col + 1) match {
            // if a solution has been been, it is returned
            case Some(solution) => return Some(solution)
            // if no solution is found, the cell is reset to 0
            case None => grid(row)(col) = 0
          }
        }
      }
      // if no solution is found, "None" is returned, to try a different value for the previous cell
      None
    }
  }

  // check the validity of a number for a given cell
  def isValid(row: Int, col: Int, num: Int): Boolean = {
    // check if the number already exists in the row
    for (c <- 0 until 9) {
      if (grid(row)(c) == num)
        return false
    }
    // check if the number already exists in the column
    for (r <- 0 until 9) {
      if (grid(r)(col) == num)
        return false
    }
    // check if the number already exists in the 3x3 sub-grid
    val subGridStartRow = (row / 3) * 3
    val subGridStartCol = (col / 3) * 3
    for (r <- 0 until 3) {
      for (c <- 0 until 3) {
        if (grid(subGridStartRow + r)(subGridStartCol + c) == num)
          return false
      }
    }
    // return True if all three checks didn't return False, meaning the proposed number doesn't exist in the row, the column, or the sub-grid
    true
  }

  //reinitializing solve to values (0,0) in case the branch of the backtracking was wrong
  solve(0, 0)
}

def printGrid(grid: SudokuGrid): Unit = {
  for (row <- grid) {
    for (cell <- row) {
      print(cell + " ")
    }
    println()
  }
}


@main def hello: Unit =
  println("Hello world!")
  println(msg)
  val puzzle: SudokuGrid = Array(
    Array(5, 3, 0, 0, 7, 0, 0, 0, 0),
    Array(6, 0, 0, 1, 9, 5, 0, 0, 0),
    Array(0, 9, 8, 0, 0, 0, 0, 6, 0),
    Array(8, 0, 0, 0, 6, 0, 0, 0, 3),
    Array(4, 0, 0, 8, 0, 3, 0, 0, 1),
    Array(7, 0, 0, 0, 2, 0, 0, 0, 6),
    Array(0, 6, 0, 0, 0, 0, 2, 8, 0),
    Array(0, 0, 0, 4, 1, 9, 0, 0, 5),
    Array(0, 0, 0, 0, 8, 0, 0, 7, 9)
  )
  solveSudoku(puzzle) match {
    case Some(solution) =>
      println("Solution:")
      printGrid(solution)
    case None =>
      println("No solution found.")
  }

def msg = "I was compiled by Scala 3. :)"

