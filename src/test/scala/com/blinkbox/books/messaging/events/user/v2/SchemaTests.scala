package com.blinkbox.books.messaging.events.user.v2

import com.blinkbox.books.messaging.JsonEventBody
import com.blinkbox.books.messaging.events.client.v2.Schema._
import com.blinkbox.books.messaging.events.user.v2.Schema._
import org.scalatest.FunSuite

class SchemaTests extends FunSuite {
  val testClient = Client(123, "Test Client", "Apple", "iPhone", "iOS")
  val testUser = User(456, "john.doe@example.org", "John", "Doe", allowMarketing = true)

  test("Construct and destructure UserAuthenticated message without a client") {
    JsonEventBody(UserAuthenticated(testUser, None)) match {
      case UserAuthenticated(user, client) => assert(user == testUser && client == None)
    }
  }

  test("Construct and destructure UserAuthenticated message with a client") {
    JsonEventBody(UserAuthenticated(testUser, Some(testClient))) match {
      case UserAuthenticated(user, client) => assert(user == testUser && client == Some(testClient))
    }
  }

  test("Construct and destructure UserRegistered message") {
    JsonEventBody(UserRegistered(testUser)) match {
      case UserRegistered(user) => assert(user == testUser)
    }
  }

  test("Construct and destructure UserUpdated message") {
    val previousDetails = User(456, "fred@example.org", "Fred", "Smith", allowMarketing = false)
    JsonEventBody(UserUpdated(testUser, previousDetails)) match {
      case UserUpdated(user, previous) => assert(user == testUser && previous == previousDetails)
    }
  }
}
