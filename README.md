# Sudoku Solver in Scala

## Group

Guillaume Théret - Pablo Sanchez - Pablo Ratouit - Gwendal Roux

## Project Description

The goal of this project was to design a sudoku solver in Scala.
In order to do so, we will use ZIO library for functional programming and asynchronous effects.

## Data structure
The input must be a 2-dimensional array of integers, to represent the rows and columns of the sudoku : 
type SudokuGrid = Array[Array[Int]]

Expected format for json sudoku grid ([test.json][test.json], [1.json][1.json]) : 
[
  [0, 9, 0, 8, 6, 5, 2, 0, 0],
  [0, 0, 5, 0, 1, 2, 0, 6, 8],
  [0, 0, 0, 0, 0, 0, 0, 4, 0],
  [0, 0, 0, 0, 0, 8, 0, 5, 6],
  [0, 0, 8, 0, 0, 0, 4, 0, 0],
  [4, 5, 0, 9, 0, 0, 0, 0, 0],
  [0, 8, 0, 0, 0, 0, 0, 0, 0],
  [2, 4, 0, 1, 7, 0, 5, 0, 0],
  [0, 0, 7, 2, 8, 3, 0, 9, 0]
]

## Functionality

Here are listed the functions we created in the [Main.scala][Main.scala] file :

| Function | Purpose |
| ------ | ------ |
| solveSudoku | The solveSudoku function takes a Sudoku grid as input and attempts to solve it. It uses a recursive helper function solve that implements the backtracking algorithm. The algorithm tries different values for each empty cell until a solution is found or all possibilities have been exhausted.  |
| isValid | The isValid function checks if a number can be placed in a particular cell without violating the rules of Sudoku. |
| printGrid | The printGrid function formats and prints the Sudoku grid in a readable format. |
| parseFile | The parseFile function receives a string from the user, add it to a path string, and try the path. It returns a file if the path exists, launching an exception if the file doesn't exist. |
| buildGrid | The buildGrid function reads a json file and creates a sudoku grid if a sudoku grid format is read from the file. It launches an exception if the format is not recognized. |
| main | The main function initializes a Sudoku puzzle grid, calls solveSudoku to solve it, and prints the solution if one is found. If there is no solution, the user is notified. |

Here are listed the functions we created in the [MySuite.scala][MySuite.scala] file :

| Function | Purpose |
| ------ | ------ |
| spec | The spec function allows us to test our program with sudoku grid from which we expect specific results (sudoku completion, ssudoku solvability).  |
| areArraysEqual | The areArraysEqual function compares an the grid given by our program for a specific sudoku grid, with an expected result, to check the validity of our program. |

## External libraries used

Can be found in the file [build.sbt][build.sbt]. 

### ZIO
ZIO is a library for building concurrent and resilient applications in Scala. It provides an expressive and typesafe way to manage asynchronous and concurrent operations, handle errors and failures, and compose complex programs. ZIO promotes functional programming principles and immutability, making it easier to reason about and test code.

### ZIO Console
This module of the ZIO library provides utilities for working with the console, such as reading and writing input/output.

### ZIO NIO
The ZIO NIO module is an abstraction over Java NIO (New I/O) that provides  programming abstractions for working with non-blocking I/O operations. It includes features like channels, file operations, and networking.

### ZIO NIO Channels
This module provides abstractions for working with channels, which are bidirectional communication pipes between a source and a sink. Channels can be used for non-blocking reading and writing of data.

### ZIO NIO File
The ZIO NIO File module provides utilities for working with files and file systems, including operations like reading, writing, copying, moving, and deleting files.

### ZIO JSON
The ZIO JSON module is a lightweight JSON library for parsing and encoding JSON data. It provides a type-safe API for working with JSON.

### ZIO Stream
ZIO Stream is a module of the ZIO library that offers functional streaming capabilities. It allows you to work with streams of data.

### ZIO test
ZIO Stream is a module of the ZIO library that offers functional streaming capabilities. It allows you to work with streams of data.

### scala.io.Source
This is a module that it used to create an iterable representation of a source file.

### scala.util.{Failure, Success, Try}
This is a module that it used for error handling.

### scala.util.control.Breaks._
This is a module that it used to return from a computation.

## Files description

Our program is made of 2 scala files, 1 build.sbt file, and 2 json files :
- [Main.scala][Main.scala]
- [MySuite.scala][MySuite.scala]
- [build.sbt][build.sbt]
- [test.json][test.json]
- [1.json][1.json]


[Main.scala]: <src/main/scala/Main.scala>
[MySuite.scala]: <src/test/scala/MySuite.scala>
[build.sbt]: <build.sbt>
[test.json]: <src/ressources/test.json>
[1.json]: <src/ressources/1.json>
