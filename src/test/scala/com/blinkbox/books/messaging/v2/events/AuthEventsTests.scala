package com.blinkbox.books.messaging.v2.events

import com.blinkbox.books.messaging.JsonEventBody
import com.blinkbox.books.messaging.v2.events.AuthEvents._
import org.scalatest.FunSuite

class AuthEventsTests extends FunSuite {
  val testClient = Client(123, "Test Client", "Apple", "iPhone", "iOS")
  val testUser = User(456, "john.doe@example.org", "John", "Doe", allowMarketing = true)

  test("Construct and destructure ClientDeregistered message") {
    JsonEventBody(ClientDeregistered(testClient)) match {
      case ClientDeregistered(client) => assert(client == testClient)
    }
  }

  test("Construct and destructure ClientRegistered message") {
    JsonEventBody(ClientRegistered(testClient)) match {
      case ClientRegistered(client) => assert(client == testClient)
    }
  }

  test("Construct and destructure ClientUpdated message") {
    val previousDetails = Client(123, "Old Client", "Samsung", "Galaxy", "Android")
    JsonEventBody(ClientUpdated(testClient, previousDetails)) match {
      case ClientUpdated(client, previous) => assert(client == testClient && previous == previousDetails)
    }
  }

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
