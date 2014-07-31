package com.blinkbox.books.schemas.events.client.v2

import com.blinkbox.books.messaging.JsonEventBody
import org.scalatest.FunSuite

class SchemaTests extends FunSuite {
  val testClient = Client(123, "Test Client", "Apple", "iPhone", "iOS")

  test("Construct and destructure Client.Deregistered message") {
    JsonEventBody(Client.Deregistered(testClient)) match {
      case Client.Deregistered(client) => assert(client == testClient)
    }
  }

  test("Construct and destructure Client.Registered message") {
    JsonEventBody(Client.Registered(testClient)) match {
      case Client.Registered(client) => assert(client == testClient)
    }
  }

  test("Construct and destructure Client.Updated message") {
    val previousDetails = Client(123, "Old Client", "Samsung", "Galaxy", "Android")
    JsonEventBody(Client.Updated(testClient, previousDetails)) match {
      case Client.Updated(client, previous) => assert(client == testClient && previous == previousDetails)
    }
  }
}
