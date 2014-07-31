package com.blinkbox.books.schemas.events.user.v2

import com.blinkbox.books.messaging.{EventBody, JsonEventBody, MediaType}
import com.blinkbox.books.schemas.events.client.v2.Client

case class User(id: Int, username: String, firstName: String, lastName: String, allowMarketing: Boolean)

object User {
  case class Authenticated(user: User, client: Option[Client])
  case class Registered(user: User)
  case class Updated(user: User, previousDetails: User)

  implicit object Authenticated extends JsonEventBody[Authenticated] {
    val jsonMediaType = MediaType("application/vnd.blinkbox.books.events.user.authenticated.v2+json")
    def unapply(body: EventBody): Option[(User, Option[Client])] = JsonEventBody.unapply[Authenticated](body).flatMap(Authenticated.unapply)
  }

  implicit object Registered extends JsonEventBody[Registered] {
    val jsonMediaType = MediaType("application/vnd.blinkbox.books.events.user.registered.v2+json")
    def unapply(body: EventBody): Option[User] = JsonEventBody.unapply[Registered](body).flatMap(Registered.unapply)
  }

  implicit object Updated extends JsonEventBody[Updated] {
    val jsonMediaType = MediaType("application/vnd.blinkbox.books.events.user.updated.v2+json")
    def unapply(body: EventBody): Option[(User, User)] = JsonEventBody.unapply[Updated](body).flatMap(Updated.unapply)
  }
}
