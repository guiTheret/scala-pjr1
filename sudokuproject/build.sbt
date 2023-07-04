val scala3Version = "3.3.0"

lazy val root = project
  .in(file("."))
  .settings(
    name := "SudokuProject",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala3Version,

    libraryDependencies += "org.scalameta" %% "munit" % "0.7.29" % Test
    libraryDependencies += "dev.zio" %% "zio" % "2.0.15"
    libraryDependencies += "dev.zio" %% "zio-streams" % "2.0.15"
    libraryDependencies += "dev.zio" %% "zio-nio" % "2.0.0"
    libraryDependencies += "dev.zio" %% "zio-json" % "0.6.0"
  )
