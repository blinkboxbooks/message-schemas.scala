package com.blinkbox.books.schemas.events.user.v2

import com.blinkbox.books.messaging.JsonEventBody
import com.blinkbox.books.schemas.events.client.v2._
import org.scalatest.FunSuite

class SchemaTests extends FunSuite {
  val testClient = Client(123, "Test Client", "Apple", "iPhone", "iOS")
  val testUser = User(456, "john.doe@example.org", "John", "Doe", allowMarketing = true)

  test("Construct and destructure User.Authenticated message without a client") {
    JsonEventBody(User.Authenticated(testUser, None)) match {
      case User.Authenticated(user, client) => assert(user == testUser && client == None)
    }
  }

  test("Construct and destructure User.Authenticated message with a client") {
    JsonEventBody(User.Authenticated(testUser, Some(testClient))) match {
      case User.Authenticated(user, client) => assert(user == testUser && client == Some(testClient))
    }
  }

  test("Construct and destructure User.Registered message") {
    JsonEventBody(User.Registered(testUser)) match {
      case User.Registered(user) => assert(user == testUser)
    }
  }

  test("Construct and destructure User.Updated message") {
    val previousDetails = User(456, "fred@example.org", "Fred", "Smith", allowMarketing = false)
    JsonEventBody(User.Updated(testUser, previousDetails)) match {
      case User.Updated(user, previous) => assert(user == testUser && previous == previousDetails)
    }
  }
}
