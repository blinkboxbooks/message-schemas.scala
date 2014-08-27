package com.blinkbox.books.schemas.events.user.v2

import java.net.URL

import com.blinkbox.books.messaging.{EventBody, JsonEventBody, MediaType}
import com.blinkbox.books.schemas.events.client.v2.Client
import org.joda.time.DateTime

case class UserId(value: Int) extends AnyVal
case class User(id: UserId, username: String, firstName: String, lastName: String)
case class UserProfile(user: User, account: AccountInfo, marketing: MarketingPreferences)
case class AccountInfo(acceptedTermsVersion: String, suspended: Boolean = false, closed: Boolean = false, ssoUserId: Option[String] = None)
case class MarketingPreferences(allowFromBooks: Boolean)

object User {
  case class Authenticated(timestamp: DateTime, user: User, client: Option[Client])
  case class Registered(timestamp: DateTime, user: UserProfile)
  case class Updated(timestamp: DateTime, user: UserProfile, previousDetails: UserProfile)
  case class Credited(timestamp: DateTime, user: User, amount: BigDecimal, currency: String, reason: String)
  case class PasswordChanged(timestamp: DateTime, user: User)
  case class PasswordResetRequested(timestamp: DateTime, username: String, resetToken: String, resetLink: URL)

  implicit object Authenticated extends JsonEventBody[Authenticated] {
    val jsonMediaType = MediaType("application/vnd.blinkbox.books.events.user.authenticated.v2+json")
    def unapply(body: EventBody): Option[(DateTime, User, Option[Client])] = JsonEventBody.unapply[Authenticated](body).flatMap(Authenticated.unapply)
  }

  implicit object Registered extends JsonEventBody[Registered] {
    val jsonMediaType = MediaType("application/vnd.blinkbox.books.events.user.registered.v2+json")
    def unapply(body: EventBody): Option[(DateTime, UserProfile)] = JsonEventBody.unapply[Registered](body).flatMap(Registered.unapply)
  }

  implicit object Updated extends JsonEventBody[Updated] {
    val jsonMediaType = MediaType("application/vnd.blinkbox.books.events.user.updated.v2+json")
    def unapply(body: EventBody): Option[(DateTime, UserProfile, UserProfile)] = JsonEventBody.unapply[Updated](body).flatMap(Updated.unapply)
  }

  implicit object Credited extends JsonEventBody[Credited] {
    val jsonMediaType = MediaType("application/vnd.blinkbox.books.events.user.credited.v2+json")
    def unapply(body: EventBody): Option[(DateTime, User, BigDecimal, String, String)] =
      JsonEventBody.unapply[Credited](body).flatMap(Credited.unapply)
  }

  implicit object PasswordChanged extends JsonEventBody[PasswordChanged] {
    val jsonMediaType = MediaType("application/vnd.blinkbox.books.events.user.password-changed.v2+json")
    def unapply(body: EventBody): Option[(DateTime, User)] = JsonEventBody.unapply[PasswordChanged](body).flatMap(PasswordChanged.unapply)
  }

  implicit object PasswordResetRequested extends JsonEventBody[PasswordResetRequested] {
    val jsonMediaType = MediaType("application/vnd.blinkbox.books.events.user.password-reset-requested.v2+json")
    def unapply(body: EventBody): Option[(DateTime, String, String, URL)] = JsonEventBody.unapply[PasswordResetRequested](body).flatMap(PasswordResetRequested.unapply)
  }
}
