package com.blinkbox.books.ingestion.book

import java.net.URI

import com.blinkbox.books.messaging.{Event, EventBody, JsonEventBody, MediaType}
import org.joda.time.DateTime

object Schema {

  case class EpubMetadata(wordCount: Long, size: Long)

  case class UriModel(`type`: String, uri: URI)

  case class EpubModel(classification: List[Classification], uris: List[UriModel], keyFile: Option[URI], wordcount: Long, size: Long)

  case class Classification(realm: String, id: String)

  case class Media(epubs: List[EpubModel])

  case class EpubSystem(name: String, version: String)

  case class Remaining(deliveredAt: DateTime, uri: String, role: String, username: String, system: EpubSystem)

  case class Source(`$remaining`: Remaining)

  case class EpubVerified(classification: List[Classification], media: Media, source: Source)

  case class EpubMessage(isbn: String, publisher: String, epubUrl: String, deliveredAt: DateTime)

  case class EpubReady(classification: List[Classification], media: Media, source: Source)

  val jsonMediaType_ = MediaType("application/vnd.blinkbox.books.ingestion.book.metadata.v2+json")

  implicit object EpubReady extends JsonEventBody[EpubReady] {
    val jsonMediaType = jsonMediaType_
  }

  implicit object EpubVerified extends JsonEventBody[EpubVerified] {
    val jsonMediaType = jsonMediaType_
  }

  def convert[T](event: Event)(implicit manifest: Manifest[T]): Option[T] = {
    implicit object JsonHelper extends JsonEventBody[T] {
      val jsonMediaType = jsonMediaType_
    }
    JsonEventBody.unapply[T](event.body)
  }

}
