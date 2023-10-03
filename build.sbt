ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.1"

lazy val root = (project in file("."))
  .settings(
    name := "rational-coffee-homepage",
    idePackagePrefix := Some("org.rational.coffee")
  )

libraryDependencies += "dev.zio" %% "zio" % "2.0.18"
libraryDependencies += "dev.zio" %% "zio-http" % "3.0.0-RC2"

