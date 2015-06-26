name := "halmos"

version := "0.1"

scalaVersion := "2.11.7"

crossPaths := false

scalacOptions ++= Seq(
  "-feature"
)

libraryDependencies ++= Seq(
  "org.scalacheck" %% "scalacheck" % "1.12.4" % "test"
)
