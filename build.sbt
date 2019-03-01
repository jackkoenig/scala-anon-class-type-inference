
lazy val scala212source211 = project
  .in(file("scala212source211"))
  .settings(
    scalaVersion := "2.12.8",
    scalacOptions := Seq("-Xsource:2.11")
  )
lazy val scala212failing = project
  .in(file("scala212failing"))
  .settings(
    scalaVersion := "2.12.8"
  )
lazy val scala212working = project
  .in(file("scala212working"))
  .settings(
    scalaVersion := "2.12.8"
  )

lazy val dottyfailing = project
  .in(file("dottyfailing"))
  .settings(
    scalaVersion := "0.12.0-RC1"
  )
