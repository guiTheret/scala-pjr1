// For more information on writing tests, see
// https://scalameta.org/munit/docs/getting-started.html

import zio._
import zio.Console._
import zio.nio._
import zio.nio.channels._
import zio.nio.file.Path
import java.io.IOException
import zio.json._

class MySuite extends munit.FunSuite {
  test("example test that succeeds") {
    val obtained = 42
    val expected = 42
    assertEquals(obtained, expected)
  }
}

private def writeAndThenRead(file: Path)(bytes: Chunk[Byte]) =
  Files.writeBytes(file, bytes) *> Files.readAllBytes(file)
