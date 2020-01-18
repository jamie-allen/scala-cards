scalaVersion := "2.13.1"
version := "0.0.1"

lazy val root = (project in file("."))
	.settings(
		name := "scala-cards"
	)

libraryDependencies ++= Seq(
          "ch.qos.logback" % "logback-classic" % "1.2.3",
          "org.scalatest" %% "scalatest" % "3.1.0" % "test"
)
