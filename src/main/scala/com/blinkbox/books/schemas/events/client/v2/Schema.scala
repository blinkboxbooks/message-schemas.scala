package com.blinkbox.books.schemas.events.client.v2

import com.blinkbox.books.messaging.{EventBody, JsonEventBody, MediaType}
import com.blinkbox.books.schemas.events.user.v2.UserId
import org.joda.time.DateTime

case class ClientId(value: Int) extends AnyVal
case class Client(id: ClientId, name: String, brand: String, model: String, os: String)

object Client {
  sealed trait Event
  case class Deregistered(timestamp: DateTime, userId: UserId, client: Client) extends Event
  case class Registered(timestamp: DateTime, userId: UserId, client: Client) extends Event
  case class Updated(timestamp: DateTime, userId: UserId, client: Client, previousDetails: Client) extends Event

  implicit object Deregistered extends JsonEventBody[Deregistered] {
    val jsonMediaType = MediaType("application/vnd.blinkbox.books.events.client.deregistered.v2+json")
    def unapply(body: EventBody): Option[(DateTime, UserId, Client)] = JsonEventBody.unapply[Deregistered](body).flatMap(Deregistered.unapply)
  }

  implicit object Registered extends JsonEventBody[Registered] {
    val jsonMediaType = MediaType("application/vnd.blinkbox.books.events.client.registered.v2+json")
    def unapply(body: EventBody): Option[(DateTime, UserId, Client)] = JsonEventBody.unapply[Registered](body).flatMap(Registered.unapply)
  }

  implicit object Updated extends JsonEventBody[Updated] {
    val jsonMediaType = MediaType("application/vnd.blinkbox.books.events.client.updated.v2+json")
    def unapply(body: EventBody): Option[(DateTime, UserId, Client, Client)] = JsonEventBody.unapply[Updated](body).flatMap(Updated.unapply)
  }
}
