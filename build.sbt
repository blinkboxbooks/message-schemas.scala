name := "message-schemas"

organization := "com.blinkbox.books.hermes"

version := scala.util.Try(scala.io.Source.fromFile("VERSION").mkString.trim).getOrElse("0.0.0")

crossScalaVersions  := Seq("2.10.4", "2.11.2")

scalacOptions := Seq("-unchecked", "-deprecation", "-feature", "-encoding", "utf8", "-target:jvm-1.7")

libraryDependencies ++= Seq(
  "com.blinkbox.books"  %%  "common-messaging"  % "1.1.3",
  "com.blinkbox.books"  %%  "common-scala-test" % "0.3.0" % "test"
)
