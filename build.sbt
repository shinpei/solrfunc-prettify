lazy val commonSettings = Seq(
  organization := "com.github.shinpei",
  version := "0.1.0",
  scalaVersion := "2.10.4"
)



import AssemblyKeys._ // put this at the top of the file
libraryDependencies += "org.scalatest" % "scalatest_2.10" % "2.2.4" % "test"

assemblySettings