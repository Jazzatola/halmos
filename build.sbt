name := "halmos"

version := "0.1"

scalaVersion := "2.11.6"

crossPaths := false

scalacOptions ++= Seq(
  "-feature"
)

libraryDependencies ++= Seq(
  "org.scalacheck" %% "scalacheck" % "1.12.2" % "test"
)
