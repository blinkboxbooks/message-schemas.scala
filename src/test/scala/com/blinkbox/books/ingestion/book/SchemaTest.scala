package com.blinkbox.books.ingestion.book

import java.io.File
import java.nio.file.Files

import com.blinkbox.books.ingestion.book.Schema.{EpubReady, EpubVerified}
import com.blinkbox.books.messaging._
import org.scalatest.FunSuite

class SchemaTest extends FunSuite {

  val epubVerified = getClass.getResource("/epub_verified.json")
  val epubVerifiedBytes = Files.readAllBytes(new File(epubVerified.toURI).toPath)

  val epubReady = getClass.getResource("/epub_verified.json")
  val epubReadyBytes = Files.readAllBytes(new File(epubReady.toURI).toPath)

  test("Construct and destructure EpubVerified message") {
    val event = Event.apply(EventHeader("epub"), EventBody(epubVerifiedBytes, ContentType(Schema.jsonMediaType_, None)))
    val msg = Schema.convert[EpubVerified](event)
    assert( msg.isDefined )
  }

  test("Construct and destructure EpubReady message") {
    val event = Event.apply(EventHeader("epub"), EventBody(epubReadyBytes, ContentType(Schema.jsonMediaType_, None)))
    val msg = Schema.convert[EpubReady](event)
    assert( msg.isDefined )
  }
}
