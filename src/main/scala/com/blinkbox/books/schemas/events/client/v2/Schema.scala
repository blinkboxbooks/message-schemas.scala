package com.blinkbox.books.schemas.events.client.v2

import com.blinkbox.books.messaging.{EventBody, JsonEventBody, MediaType}
import com.blinkbox.books.schemas.events.user.v2.User
import org.joda.time.DateTime

case class ClientId(value: Int) extends AnyVal
case class Client(id: ClientId, name: String, brand: String, model: String, os: String)

object Client {
  case class Deregistered(timestamp: DateTime, user: User, client: Client)
  case class Registered(timestamp: DateTime, user: User, client: Client)
  case class Updated(timestamp: DateTime, user: User, client: Client, previousDetails: Client)

  implicit object Deregistered extends JsonEventBody[Deregistered] {
    val jsonMediaType = MediaType("application/vnd.blinkbox.books.events.client.deregistered.v2+json")
    def unapply(body: EventBody): Option[(DateTime, User, Client)] = JsonEventBody.unapply[Deregistered](body).flatMap(Deregistered.unapply)
  }

  implicit object Registered extends JsonEventBody[Registered] {
    val jsonMediaType = MediaType("application/vnd.blinkbox.books.events.client.registered.v2+json")
    def unapply(body: EventBody): Option[(DateTime, User, Client)] = JsonEventBody.unapply[Registered](body).flatMap(Registered.unapply)
  }

  implicit object Updated extends JsonEventBody[Updated] {
    val jsonMediaType = MediaType("application/vnd.blinkbox.books.events.client.updated.v2+json")
    def unapply(body: EventBody): Option[(DateTime, User, Client, Client)] = JsonEventBody.unapply[Updated](body).flatMap(Updated.unapply)
  }
}
