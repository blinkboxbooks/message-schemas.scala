package com.blinkbox.books.messaging.events.user.v2

import com.blinkbox.books.messaging.events.client.v2.Schema.Client
import com.blinkbox.books.messaging.{EventBody, JsonEventBody, MediaType}

object Schema {
  case class User(id: Int, username: String, firstName: String, lastName: String, allowMarketing: Boolean)

  case class UserAuthenticated(user: User, client: Option[Client])
  case class UserRegistered(user: User)
  case class UserUpdated(user: User, previousDetails: User)

  implicit object UserAuthenticated extends JsonEventBody[UserAuthenticated] {
    val jsonMediaType = MediaType("application/vnd.blinkbox.books.events.user.authenticated.v2+json")
    def unapply(body: EventBody): Option[(User, Option[Client])] = JsonEventBody.unapply[UserAuthenticated](body).flatMap(UserAuthenticated.unapply)
  }

  implicit object UserRegistered extends JsonEventBody[UserRegistered] {
    val jsonMediaType = MediaType("application/vnd.blinkbox.books.events.user.registered.v2+json")
    def unapply(body: EventBody): Option[User] = JsonEventBody.unapply[UserRegistered](body).flatMap(UserRegistered.unapply)
  }

  implicit object UserUpdated extends JsonEventBody[UserUpdated] {
    val jsonMediaType = MediaType("application/vnd.blinkbox.books.events.user.updated.v2+json")
    def unapply(body: EventBody): Option[(User, User)] = JsonEventBody.unapply[UserUpdated](body).flatMap(UserUpdated.unapply)
  }
}
