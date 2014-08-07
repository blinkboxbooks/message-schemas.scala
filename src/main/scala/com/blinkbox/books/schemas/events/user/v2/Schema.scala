package com.blinkbox.books.schemas.events.user.v2

import com.blinkbox.books.messaging.{EventBody, JsonEventBody, MediaType}
import com.blinkbox.books.schemas.events.client.v2.Client
import org.joda.time.DateTime

case class UserId(value: Int) extends AnyVal
case class User(id: UserId, username: String, firstName: String, lastName: String, allowMarketingCommunications: Boolean)

object User {
  sealed trait Event
  case class Authenticated(timestamp: DateTime, user: User, client: Option[Client]) extends Event
  case class Registered(timestamp: DateTime, user: User) extends Event
  case class Updated(timestamp: DateTime, user: User, previousDetails: User) extends Event
  case class Credited(timestamp: DateTime, user: User, amount: BigDecimal, currency: String, reason: String)

  implicit object Authenticated extends JsonEventBody[Authenticated] {
    val jsonMediaType = MediaType("application/vnd.blinkbox.books.events.user.authenticated.v2+json")
    def unapply(body: EventBody): Option[(DateTime, User, Option[Client])] = JsonEventBody.unapply[Authenticated](body).flatMap(Authenticated.unapply)
  }

  implicit object Registered extends JsonEventBody[Registered] {
    val jsonMediaType = MediaType("application/vnd.blinkbox.books.events.user.registered.v2+json")
    def unapply(body: EventBody): Option[(DateTime, User)] = JsonEventBody.unapply[Registered](body).flatMap(Registered.unapply)
  }

  implicit object Updated extends JsonEventBody[Updated] {
    val jsonMediaType = MediaType("application/vnd.blinkbox.books.events.user.updated.v2+json")
    def unapply(body: EventBody): Option[(DateTime, User, User)] = JsonEventBody.unapply[Updated](body).flatMap(Updated.unapply)
  }

  implicit object Credited extends JsonEventBody[Credited] {
    val jsonMediaType = MediaType("application/vnd.blinkbox.books.events.user.credited.v2+json")
    def unapply(body: EventBody): Option[(DateTime, User, BigDecimal, String, String)] =
      JsonEventBody.unapply[Credited](body).flatMap(Credited.unapply)
  }
}
