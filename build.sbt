name := "halmos"

version := "0.1"

scalaVersion := "2.11.6"

crossPaths := false

scalacOptions ++= Seq(
  "-feature"
)

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "2.2.4" % "test"
)
