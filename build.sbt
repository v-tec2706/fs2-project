ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.10"

lazy val root = (project in file("."))
  .settings(
    name := "fs2-task"
  )

val fs2Version = "3.4.0"
libraryDependencies ++= Seq(
  "co.fs2" %% "fs2-core" % fs2Version,
  "co.fs2" %% "fs2-io" % fs2Version,
  "com.github.fd4s" %% "fs2-kafka" % "3.0.0-M8",
  "com.github.pureconfig" %% "pureconfig" % "0.17.2",
  "org.scalatest" %% "scalatest" % "3.2.14" % "test",
  "org.typelevel" %% "cats-effect-testing-scalatest" % "1.5.0" % "test"
)

