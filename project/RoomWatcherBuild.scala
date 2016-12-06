import sbt._
import Keys._
import org.scalajs.sbtplugin.ScalaJSPlugin
import ScalaJSPlugin._
import autoImport._
import sbtassembly.AssemblyPlugin.autoImport._

object RoomWatcherBuild extends Build {
  lazy val scalaV = "2.12.1"
  lazy val akkaV = "2.4.14"
  lazy val akkaHttpV = "10.0.0"

  lazy val root =
    Project("root", file("."))
      .aggregate(frontend, backend)

  // Scala-Js frontend
  lazy val frontend =
    Project("frontend", file("frontend"))
      .enablePlugins(ScalaJSPlugin)
      .settings(commonSettings: _*)
      .settings(
        persistLauncher in Compile := true,
        persistLauncher in Test := false,
        testFrameworks += new TestFramework("utest.runner.Framework"),
        libraryDependencies ++= Seq(
          "org.scala-js" %%% "scalajs-dom" % "0.9.1"
        )
      )

  // Akka Http based backend
  lazy val backend =
    Project("backend", file("backend"))
      .settings(commonSettings: _*)
      .settings(
        assemblyJarName in assembly := "rw-backend.jar",
        libraryDependencies ++= Seq(
          "com.typesafe.akka" %% "akka-http" % akkaHttpV
        ),
        (resourceGenerators in Compile) <+=
          (fastOptJS in Compile in frontend, packageScalaJSLauncher in Compile in frontend)
            .map((f1, f2) => Seq(f1.data, f2.data)),
        watchSources <++= (watchSources in frontend)
      )

  lazy val shared = (crossProject.crossType(CrossType.Pure) in file ("shared")).
    settings(
      scalaVersion:=scalaV
    )

  def commonSettings = Seq(
    scalaVersion := scalaV,
    version := "0.1"
  )
}
