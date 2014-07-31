name := "message-schemas"

organization := "com.blinkbox.books.hermes"

version := scala.util.Try(scala.io.Source.fromFile("VERSION").mkString.trim).getOrElse("0.0.0")

scalaVersion  := "2.10.4"

scalacOptions := Seq("-unchecked", "-deprecation", "-feature", "-encoding", "utf8", "-target:jvm-1.7")

libraryDependencies ++= Seq(
  "com.blinkbox.books"  %%  "common-messaging"  % "0.5.0",
  "com.blinkbox.books"  %%  "common-scala-test" % "0.2.0"   % "test"
)
