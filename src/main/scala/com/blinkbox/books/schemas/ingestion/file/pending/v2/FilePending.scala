package com.blinkbox.books.schemas.ingestion.file.pending.v2

import java.net.URI
import com.blinkbox.books.messaging.{EventBody, MediaType, JsonEventBody}
import org.joda.time.DateTime

object FilePending {
  case class Details(source: FileSource)
  case class FileSource(
    deliveredAt: DateTime,
    uri: URI,
    username: String,
    fileName: String,
    contentType: String,
    system: SystemDetails,
    role: String = "publisher_ftp"
  )
  case class SystemDetails(
    name: String = "Marvin/watcher",
    version: String
  )

  implicit object Details extends JsonEventBody[Details] {
    val jsonMediaType = MediaType("application/vnd.blinkbox.books.ingestion.file.pending.v2+json")
    def unapply(body: EventBody): Option[(FileSource)] = JsonEventBody.unapply[Details](body).flatMap(Details.unapply)
  }
}