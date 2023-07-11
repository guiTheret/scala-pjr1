val scala3Version = "3.3.0"

lazy val root = project
  .in(file("."))
  .settings(
    name := "SudokuProject",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala3Version,

    libraryDependencies ++= Seq(
      // Dependencies
      "org.scalameta" %% "munit" % "0.7.29" % Test,
      "dev.zio" %% "zio" % "2.0.15",
      "dev.zio" %% "zio-nio" % "2.0.1",
      "dev.zio" %% "zio-json" % "0.6.0",
      "dev.zio" %% "zio-test"          % "2.0.15" % Test,
      "dev.zio" %% "zio-test-sbt"      % "2.0.15" % Test,
      "dev.zio" %% "zio-test-magnolia" % "2.0.15" % Test
      // add test libraries here

    )
  )
