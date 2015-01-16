package com.blinkbox.books.schemas.ingestion.book.v2

import java.net.URI

import com.blinkbox.books.messaging.{EventBody, JsonEventBody, MediaType}
import org.joda.time.DateTime

object Book {
  case class Classification(realm: String, id: String)
  case class Image(classification: List[Classification], uri: URI, width: Int, height: Int, size: Long)
  case class Media(images: Option[List[Image]] = None)
  case class System(name: String, version: String)
  case class Source(deliveredAt: DateTime, uri: URI, fileName: Option[URI], role: String,
    username: String, contentType: Option[String], system: System)
  case class Cover(classification: List[Classification], isbn: String, media: Media, source: Source)

  implicit object Cover extends JsonEventBody[Cover] {
    val jsonMediaType = MediaType("application/vnd.blinkbox.books.ingestion.book.metadata.v2+json")
    def unapply(body: EventBody): Option[(List[Classification], String, Media, Source)] =
      JsonEventBody.unapply[Cover](body).flatMap(Cover.unapply)
  }
}
