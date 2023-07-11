import zio.test._
import zio.test.Assertion._
import zio.Console._

import scala.util.control.Breaks._

object SudokuSolverSpec extends ZIOSpecDefault  {
  def spec = suite("Sudoku Solver")(
      test("solveSudoku should solve a valid Sudoku puzzle") {
        val puzzle = Array(
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

        val expectedSolution = Array(
          Array(5, 3, 4, 6, 7, 8, 9, 1, 2),
          Array(6, 7, 2, 1, 9, 5, 3, 4, 8),
          Array(1, 9, 8, 3, 4, 2, 5, 6, 7),
          Array(8, 5, 9, 7, 6, 1, 4, 2, 3),
          Array(4, 2, 6, 8, 5, 3, 7, 9, 1),
          Array(7, 1, 3, 9, 2, 4, 8, 5, 6),
          Array(9, 6, 1, 5, 3, 7, 2, 8, 4),
          Array(2, 8, 7, 4, 1, 9, 6, 3, 5),
          Array(3, 4, 5, 2, 8, 6, 1, 7, 9)
        )

        val result = solveSudoku(puzzle)
        assert(areArraysEqual(result.get, expectedSolution))(isTrue)
      },
      test("solveSudoku should fail") {
        val puzzle = Array(
          Array(5, 3, 0, 0, 7, 0, 0, 0, 0),
          Array(6, 0, 0, 1, 9, 5, 0, 0, 0),
          Array(0, 9, 8, 0, 0, 0, 0, 6, 0),
          Array(8, 0, 0, 0, 6, 0, 0, 0, 3),
          Array(4, 0, 0, 8, 0, 3, 0, 0, 1),
          Array(7, 0, 0, 0, 2, 0, 0, 0, 6),
          Array(0, 6, 0, 0, 0, 0, 2, 8, 0),
          Array(0, 0, 0, 4, 1, 9, 0, 0, 5),
          Array(6, 0, 0, 0, 8, 0, 0, 7, 9)
        )

        val result = solveSudoku(puzzle)
        assert(result)(isNone)
      }
    )
}

def areArraysEqual(arr1: Array[Array[Int]], arr2: Array[Array[Int]]): Boolean = {
  if (arr1.length != arr2.length || arr1(0).length != arr2(0).length)
    return false

  var equal = true

  breakable {
    for (i <- 0 until arr1.length) {
      for (j <- 0 until arr1(0).length) {
        if (arr1(i)(j) != arr2(i)(j)) {
          equal = false
          break
        }
      }
    }
  }

  equal
}
