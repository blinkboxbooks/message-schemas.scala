package com.blinkbox.books.schemas.ingestion.file.pending.v2

import java.net.URI

import com.blinkbox.books.messaging._
import org.joda.time.{DateTime, DateTimeZone}
import org.scalatest.FunSuite

class FilePendingTest extends FunSuite {
  val now = DateTime.now(DateTimeZone.UTC)
  val uri = new URI("bbbmap:label:whatever")
  val publisher = "publisher"
  val filename = "path/to/a/file.epub"
  val contentType = "application/epub+zip"
  val system = "Marvin/watcher"
  val version = "0.0.0"
  val role = "publisher_ftp"

  test("Construct and destruct FilePending.Details message") {
    JsonEventBody(
      FilePending.Details(
        FilePending.FileSource(
          now,
          uri,
          publisher,
          filename,
          contentType,
          FilePending.SystemDetails(system, version),
          role
        )
      )
    ) match {
      case FilePending.Details(
        FilePending.FileSource(
          altNow,
          altUri,
          altPublisher,
          altFilename,
          altContentType,
          FilePending.SystemDetails(altSystem, altVersion),
          altRole
        )
      ) =>
        assert(altNow == now)
        assert(altUri == uri)
        assert(altPublisher == publisher)
        assert(altFilename == filename)
        assert(altContentType == contentType)
        assert(altSystem == system)
        assert(altVersion == version)
        assert(altRole == role)
    }
  }
}
