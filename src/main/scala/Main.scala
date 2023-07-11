import zio._
import zio.json._

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

def printGrid(sudoku: SudokuGrid): String = {
  val separator = "+------+-------+------+"
  val formattedRows = sudoku.grouped(3).map { bigGroup =>
    bigGroup.map { row =>
      row.grouped(3).map { smallGroup =>
        smallGroup.mkString("  ")
      }.mkString(" | ")
    }.mkString("\n")
  }.mkString("\n\n")
  formattedRows + "\n"
  //println(formattedRows)
}


def parseFile(filePath: String) = {
  val file = Try(Source.fromFile("src/ressources/" + filePath))
  file match {
    case Success(value) => ZIO.succeed(value.mkString)
    case Failure(exception) => ZIO.fail(exception)
  }
}

def buildGrid(jsonString: String) = {
  val sudoku = jsonString.fromJson[SudokuGrid]
  sudoku match {
    case Right(value) => ZIO.succeed(value)
    case Left(exception) => ZIO.fail(exception)
  }
}



object Main extends ZIOAppDefault {
  def run =
    for {
      _ <- Console.printLine("Enter the path to the JSON file containing the Sudoku problem:")
      path <- Console.readLine
      _ <-  Console.printLine(s"You entered: $path")

      jsonString <- parseFile(path)

      _ <- Console.printLine(s"JSON String: $jsonString")
    
      grid <- buildGrid(jsonString)
      _ <- Console.printLine(printGrid(grid))

      _ <- solveSudoku(grid) match {
        case Some(value) => Console.printLine(printGrid(value))
        case None => Console.printLine("No solution found")
      }
    } yield ()
}