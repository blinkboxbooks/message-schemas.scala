package com.blinkbox.books.schemas.events.user.v2

import com.blinkbox.books.messaging.JsonEventBody
import com.blinkbox.books.schemas.events.client.v2._
import org.joda.time.{DateTime, DateTimeZone}
import org.scalatest.FunSuite

class SchemaTests extends FunSuite {
  val now = DateTime.now(DateTimeZone.UTC)
  val testClient = Client(ClientId(123), "Test Client", "Apple", "iPhone", "iOS")
  val testUser = User(UserId(456), "john.doe@example.org", "John", "Doe", allowMarketingCommunications = true)

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
    JsonEventBody(User.Registered(now, testUser)) match {
      case User.Registered(ts, user) => assert(ts == now && user == testUser)
    }
  }

  test("Construct and destructure User.Updated message") {
    val previousDetails = User(UserId(456), "fred@example.org", "Fred", "Smith", allowMarketingCommunications = false)
    JsonEventBody(User.Updated(now, testUser, previousDetails)) match {
      case User.Updated(ts, user, previous) => assert(ts == now && user == testUser && previous == previousDetails)
    }
  }
}
