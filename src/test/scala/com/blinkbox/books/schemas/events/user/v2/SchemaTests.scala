package com.blinkbox.books.schemas.events.user.v2

import java.net.URL

import com.blinkbox.books.messaging.JsonEventBody
import com.blinkbox.books.schemas.events.client.v2._
import org.joda.time.{DateTime, DateTimeZone}
import org.scalatest.FunSuite

class SchemaTests extends FunSuite {
  val now = DateTime.now(DateTimeZone.UTC)
  val testClient = Client(ClientId(123), "Test Client", "Apple", "iPhone", "iOS")
  val testUser = User(UserId(456), "john.doe@example.org", "John", "Doe")
  val testUserProfile = UserProfile(testUser, "v1", CommunicationPreferences(allowFromBooks = true))

  test("Construct and destructure User.Authenticated message without a client") {
    JsonEventBody(User.Authenticated(now, testUser, None)) match {
      case User.Authenticated(ts, user, client) => assert(ts == now && user == testUser && client == None)
    }
  }

  test("Construct and destructure User.Authenticated message with a client") {
    JsonEventBody(User.Authenticated(now, testUser, Some(testClient))) match {
      case User.Authenticated(ts, user, client) => assert(ts == now && user == testUser && client == Some(testClient))
    }
  }

  test("Construct and destructure User.Registered message") {
    JsonEventBody(User.Registered(now, testUserProfile)) match {
      case User.Registered(ts, user) => assert(ts == now && user == testUserProfile)
    }
  }

  test("Construct and destructure User.Updated message") {
    val previousDetails = UserProfile(User(UserId(456), "fred@example.org", "Fred", "Smith"), "v2", CommunicationPreferences(allowFromBooks = false))
    JsonEventBody(User.Updated(now, testUserProfile, previousDetails)) match {
      case User.Updated(ts, user, previous) => assert(ts == now && user == testUserProfile && previous == previousDetails)
    }
  }

  test("Construct and destructure User.Credited message") {
    val (originalAmount, originalCurrency, originalReason) = (BigDecimal("3.99"), "GBP", "hudl2")
    JsonEventBody(User.Credited(now, testUser, originalAmount, originalCurrency, originalReason)) match {
      case User.Credited(ts, user, amount, currency, reason) =>
        assert(ts == now && user == testUser && amount == originalAmount && currency == originalCurrency && reason == originalReason)
    }
  }

  test("Construct and destructure User.PasswordChanged message") {
    JsonEventBody(User.PasswordChanged(now, testUser)) match {
      case User.PasswordChanged(ts, user) => assert(ts == now && user == testUser)
    }
  }

  test("Construct and destructure User.PasswordResetRequested message") {
    val testToken = "2983nns39d"
    val testLink = new URL(s"https://www.blinkboxbooks.com/reset-password/$testToken")
    JsonEventBody(User.PasswordResetRequested(now, testUser, testToken, testLink)) match {
      case User.PasswordResetRequested(ts, user, token, link) =>
        assert(ts == now && user == testUser && token == testToken && link == testLink)
    }
  }
}
