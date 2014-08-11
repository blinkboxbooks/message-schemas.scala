package com.blinkbox.books.schemas.events.client.v2

import com.blinkbox.books.messaging.JsonEventBody
import com.blinkbox.books.schemas.events.user.v2.{User, UserId}
import org.joda.time.{DateTime, DateTimeZone}
import org.scalatest.FunSuite

class SchemaTests extends FunSuite {
  val now = DateTime.now(DateTimeZone.UTC)
  val testUser = User(UserId(456), "john.doe@example.org", "John", "Doe")
  val testClient = Client(ClientId(123), "Test Client", "Apple", "iPhone", "iOS")

  test("Construct and destructure Client.Deregistered message") {
    JsonEventBody(Client.Deregistered(now, testUser, testClient)) match {
      case Client.Deregistered(ts, user, client) => assert(ts == now && user == testUser && client == testClient)
    }
  }

  test("Construct and destructure Client.Registered message") {
    JsonEventBody(Client.Registered(now, testUser, testClient)) match {
      case Client.Registered(ts, user, client) => assert(ts == now && user == testUser && client == testClient)
    }
  }

  test("Construct and destructure Client.Updated message") {
    val previousDetails = Client(ClientId(123), "Old Client", "Samsung", "Galaxy", "Android")
    JsonEventBody(Client.Updated(now, testUser, testClient, previousDetails)) match {
      case Client.Updated(ts, user, client, previous) => assert(ts == now && user == testUser && client == testClient && previous == previousDetails)
    }
  }
}
