package com.blinkbox.books.messaging.events.client.v2

import com.blinkbox.books.messaging.JsonEventBody
import com.blinkbox.books.messaging.events.client.v2.Schema._
import org.scalatest.FunSuite

class SchemaTests extends FunSuite {
  val testClient = Client(123, "Test Client", "Apple", "iPhone", "iOS")

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
}
