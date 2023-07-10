import zio._
import zio.Console

import java.io.FileNotFoundException
import scala.io.Source
import scala.util.{Failure, Success, Try}


type SudokuGrid = Array[Array[Int]]

def solveSudoku(grid: SudokuGrid): Option[SudokuGrid] = {
  //Recursive backtracking function
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




  def isValid(row: Int, col: Int, num: Int): Boolean = {
    // check if the number already exists in the row
    if (grid(row).contains(num))
      false
    else {
      // check if the number already exists in the column
      if (grid.exists(r => r(col) == num))
        false
      else {
        // check if the number already exists in the 3x3 sub-grid
        val subGridStartRow = (row / 3) * 3
        val subGridStartCol = (col / 3) * 3
        if (grid.slice(subGridStartRow, subGridStartRow + 3).exists(row => row.slice(subGridStartCol, subGridStartCol + 3).contains(num)))
          false
        else
          true
      }
    }
  }
  
  //reinitializing solve to values (0,0) in case the branch of the backtracking was wrong
  solve(0, 0)
}

def printGrid(sudoku: SudokuGrid): Unit = {
  val separator = "+------+-------+------+"
  val formattedRows = sudoku.grouped(3).map { bigGroup =>
    bigGroup.map { row =>
      row.grouped(3).map { smallGroup =>
        smallGroup.mkString("  ")
      }.mkString(" | ")
    }.mkString("\n")
  }.mkString("\n\n")

  println(formattedRows)
}

def parseFile(filePath: String): ZIO[Any, Throwable, String] = {
  ZIO.fromTry(Try(Source.fromFile(filePath).mkString))
}

// build a sudoku grid
def buildSudoku(jsonString: String): Unit = {
  val sudokuGrid = jsonString.split("\n").map(_.split(" ").map(_.toInt))
  sudokuGrid
}

object Main extends ZIOAppDefault {
  def run = appLogic

  private val appLogic = for {
    _ <- Console.printLine("Sudoku Solver")
    _ <- Console.printLine("-------------")
    jsonFilePath <- Console.readLine("Enter the path to the json file: ")
    /*
    fileContent <- parseFile(jsonFilePath)
    _ <- Console.printLine(fileContent)
    sudokuGrid <- buildSudoku(fileContent)
    _ <- sudokuGrid match {
      case Right(grid) => printLine(grid.solve())
      case Left(error) => printLine("Invalid grid")
    }
    */
  } yield ()
}





