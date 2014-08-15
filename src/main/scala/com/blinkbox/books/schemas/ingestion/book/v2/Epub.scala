package com.blinkbox.books.schemas.ingestion.book.v2

import java.net.URI

import com.blinkbox.books.messaging.{EventHeader, Event, JsonEventBody, MediaType}
import org.joda.time.DateTime

object Epub {

  case class EpubMetadata(wordCount: Long, size: Long)

  case class UriModel(`type`: String, uri: URI)

  case class EpubModel(classification: List[Classification], uris: List[UriModel], keyFile: Option[URI], wordcount: Long, size: Long)

  case class Classification(realm: String, id: String)

  case class Media(epubs: List[EpubModel])

  case class EpubSystem(name: String, version: String)

  case class Remaining(deliveredAt: DateTime, uri: String, role: String, username: String, system: EpubSystem)

  case class Source(`$remaining`: Remaining)

  case class EpubMessage(isbn: String, publisher: String, epubUrl: String, deliveredAt: DateTime)

  case class Ingestion(classification: List[Classification], media: Media, source: Source)

  val jsonMediaType = MediaType("application/vnd.blinkbox.books.ingestion.book.metadata.v2+json")

  object Ingestion extends JsonEventBody[Ingestion] {
    val jsonMediaType = Epub.jsonMediaType
  }

  def convert(event: Event)(implicit manifest: Manifest[Ingestion]): Option[Ingestion] = {
    JsonEventBody.unapply[Ingestion](event.body)(manifest, Ingestion)
  }
  def convert(ingestion: Ingestion) = {
    Event.json(EventHeader("epub-ingestion"), ingestion)(Ingestion)
  }
}
