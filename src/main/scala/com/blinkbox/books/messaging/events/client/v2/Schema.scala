package com.blinkbox.books.messaging.events.client.v2

import com.blinkbox.books.messaging.{EventBody, JsonEventBody, MediaType}

object Schema {
  case class Client(id: Int, name: String, brand: String, model: String, os: String)

  case class ClientDeregistered(client: Client)
  case class ClientRegistered(client: Client)
  case class ClientUpdated(client: Client, previousDetails: Client)

  implicit object ClientDeregistered extends JsonEventBody[ClientDeregistered] {
    val jsonMediaType = MediaType("application/vnd.blinkbox.books.events.client.deregistered.v2+json")
    def unapply(body: EventBody): Option[Client] = JsonEventBody.unapply[ClientDeregistered](body).flatMap(ClientDeregistered.unapply)
  }

  implicit object ClientRegistered extends JsonEventBody[ClientRegistered] {
    val jsonMediaType = MediaType("application/vnd.blinkbox.books.events.client.registered.v2+json")
    def unapply(body: EventBody): Option[Client] = JsonEventBody.unapply[ClientRegistered](body).flatMap(ClientRegistered.unapply)
  }

  implicit object ClientUpdated extends JsonEventBody[ClientUpdated] {
    val jsonMediaType = MediaType("application/vnd.blinkbox.books.events.client.updated.v2+json")
    def unapply(body: EventBody): Option[(Client, Client)] = JsonEventBody.unapply[ClientUpdated](body).flatMap(ClientUpdated.unapply)
  }
}
