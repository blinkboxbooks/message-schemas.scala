package com.blinkbox.books.messaging.v2.events

import com.blinkbox.books.messaging._

object AuthEvents {
  case class Client(id: Int, name: String, brand: String, model: String, os: String)
  case class User(id: Int, username: String, firstName: String, lastName: String, allowMarketing: Boolean)

  case class ClientDeregistered(client: Client)
  case class ClientRegistered(client: Client)
  case class ClientUpdated(client: Client, previousDetails: Client)
  case class UserAuthenticated(user: User, client: Option[Client])
  case class UserRegistered(user: User)
  case class UserUpdated(user: User, previousDetails: User)

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

