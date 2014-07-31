package com.blinkbox.books.schemas.events.client.v2

import com.blinkbox.books.messaging.{EventBody, JsonEventBody, MediaType}

case class Client(id: Int, name: String, brand: String, model: String, os: String)

object Client {
  case class Deregistered(client: Client)
  case class Registered(client: Client)
  case class Updated(client: Client, previousDetails: Client)

  implicit object Deregistered extends JsonEventBody[Deregistered] {
    val jsonMediaType = MediaType("application/vnd.blinkbox.books.events.client.deregistered.v2+json")
    def unapply(body: EventBody): Option[Client] = JsonEventBody.unapply[Deregistered](body).flatMap(Deregistered.unapply)
  }

  implicit object Registered extends JsonEventBody[Registered] {
    val jsonMediaType = MediaType("application/vnd.blinkbox.books.events.client.registered.v2+json")
    def unapply(body: EventBody): Option[Client] = JsonEventBody.unapply[Registered](body).flatMap(Registered.unapply)
  }

  implicit object Updated extends JsonEventBody[Updated] {
    val jsonMediaType = MediaType("application/vnd.blinkbox.books.events.client.updated.v2+json")
    def unapply(body: EventBody): Option[(Client, Client)] = JsonEventBody.unapply[Updated](body).flatMap(Updated.unapply)
  }
}
