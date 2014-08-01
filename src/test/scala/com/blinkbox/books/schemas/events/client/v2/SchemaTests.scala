package com.blinkbox.books.schemas.events.client.v2

import com.blinkbox.books.messaging.JsonEventBody
import com.blinkbox.books.schemas.events.user.v2.UserId
import org.joda.time.{DateTime, DateTimeZone}
import org.scalatest.FunSuite

class SchemaTests extends FunSuite {
  val now = DateTime.now(DateTimeZone.UTC)
  val testUserId = UserId(789)
  val testClient = Client(ClientId(123), "Test Client", "Apple", "iPhone", "iOS")

  test("Construct and destructure Client.Deregistered message") {
    JsonEventBody(Client.Deregistered(now, testUserId, testClient)) match {
      case Client.Deregistered(ts, userId, client) => assert(ts == now && userId == testUserId && client == testClient)
    }
  }

  test("Construct and destructure Client.Registered message") {
    JsonEventBody(Client.Registered(now, testUserId, testClient)) match {
      case Client.Registered(ts, userId, client) => assert(ts == now && userId == testUserId && client == testClient)
    }
  }

  test("Construct and destructure Client.Updated message") {
    val previousDetails = Client(ClientId(123), "Old Client", "Samsung", "Galaxy", "Android")
    JsonEventBody(Client.Updated(now, testUserId, testClient, previousDetails)) match {
      case Client.Updated(ts, userId, client, previous) => assert(ts == now && userId == testUserId && client == testClient && previous == previousDetails)
    }
  }
}
