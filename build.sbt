ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.1"

lazy val root = (project in file("."))
  .settings(
    name := "rational-coffee-homepage",
    idePackagePrefix := Some("org.rational.coffee")
  )

libraryDependencies += "dev.zio" %% "zio" % "2.0.18"
libraryDependencies += "dev.zio" %% "zio-http" % "3.0.0-RC2"
libraryDependencies += "com.google.code.gson" % "gson" % "2.10.1"
libraryDependencies += "org.yaml" % "snakeyaml" % "2.0"
libraryDependencies ++= Seq(
  // Syncronous JDBC Modules
  "io.getquill" %% "quill-jdbc" % "4.7.3",
  // Or ZIO Modules
  "io.getquill" %% "quill-jdbc-zio" % "4.7.3"
)
libraryDependencies += "mysql" % "mysql-connector-java" % "8.0.26"
