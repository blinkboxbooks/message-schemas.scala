package com.blinkbox.books.schemas.ingestion.book.v2

import java.io.File
import java.nio.file.Files

import com.blinkbox.books.messaging._
import com.blinkbox.books.schemas.ingestion.book.v2.Book.Metadata
import org.scalatest.FunSuite

class BookTest extends FunSuite {

  val epubIngestion = getClass.getResource("/epub_ingestion.json")
  val epubIngestionBytes = Files.readAllBytes(new File(epubIngestion.toURI).toPath)


  test("Construct and destructure Epub Ingested message") {
    val event = Event.apply(EventHeader("epub"), EventBody(epubIngestionBytes, ContentType(Book.jsonMediaType, None)))
    val metadata = JsonEventBody.unapply[Metadata](event.body)
    assert(metadata.isDefined)
  }
}
