lazy val root = (project in file(".")).
  settings(
    name := "message-schemas",
    organization := "com.blinkbox.books.hermes",
    version := scala.util.Try(scala.io.Source.fromFile("VERSION").mkString.trim).getOrElse("0.0.0"),
    scalaVersion := "2.11.4",
    scalacOptions := Seq("-unchecked", "-deprecation", "-feature", "-encoding", "utf8", "-target:jvm-1.7", "-Xfatal-warnings", "-Xfuture"),
    libraryDependencies ++= {
      Seq(
        "com.blinkbox.books" %% "common-messaging"  % "2.1.1",
        "com.blinkbox.books" %% "common-scala-test" % "0.3.0"  % Test
      )
    }
  )
