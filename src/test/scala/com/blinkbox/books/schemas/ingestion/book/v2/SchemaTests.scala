package com.blinkbox.books.schemas.ingestion.book.v2

import java.net.URI

import com.blinkbox.books.messaging._
import com.blinkbox.books.schemas.ingestion.book.v2.Book._
import org.joda.time.{DateTime, DateTimeZone}
import org.scalatest.FunSuite

class SchemaTests extends FunSuite {
  val now = DateTime.now(DateTimeZone.UTC)
  val testClassification = List(
    Classification("isbn", "9780111222333"),
    Classification("source_username", "randomhouse_uk")
  )
  val testIsbn = "9780111222333"
  val testMedia = Media(Some(List(Image(
    List(Classification("type", "front_cover")),
    new URI("bbbmap:covers:abcd1234"),
    1200,
    2500,
    25485))))
  val testSource = Source(
    now,
    new URI("azure://path.to/a/folder/and/a/book-cover.jpg"),
    None,
    "publisher_ftp",
    "randomhouse_uk",
    None,
    System("marvin/cover_processor", "1.1.2"))

  test("Construct and destructure Book.Cover message") {
    JsonEventBody(Book.Cover(testClassification, testIsbn, testMedia, testSource)) match {
      case Book.Cover(classification, isbn, media, source) =>
        assert(classification == testClassification && isbn == testIsbn && media == testMedia && source == testSource)
    }
  }
}
