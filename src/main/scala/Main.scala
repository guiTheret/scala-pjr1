import zio._
import zio.Console._
import zio.nio._
import zio.nio.channels._
import zio.nio.file.Path
import java.io.IOException
import zio.json._

@main def hello: Unit =
  println("Hello world!")
  println(msg)

def msg = "I was compiled by Scala 3. :)"
