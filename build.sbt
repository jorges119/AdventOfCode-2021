val scala3Version = "3.1.0"

lazy val root = project
  .in(file("."))
  .settings(
    name := "AdventOfCode-2021",
    version := "0.1",

    scalaVersion := scala3Version,

    libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % "test", 
    libraryDependencies += "com.softwaremill.sttp.client3" %% "core" % "3.3.18"
  )
