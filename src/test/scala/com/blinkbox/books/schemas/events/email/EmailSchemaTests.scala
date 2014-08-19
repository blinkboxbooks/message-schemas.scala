package com.blinkbox.books.schemas.events.email

import org.joda.time.DateTime
import org.scalatest.FunSuite
import org.joda.time.DateTimeZone
import com.blinkbox.books.messaging.JsonEventBody

package v2 {

  class EmailSchemaTests extends FunSuite {

    val now = DateTime.now(DateTimeZone.UTC)
    val testUser = v2.User("john.doe@example.org", "456")
    val templateName = "test template"
    val exampleAttributes = Map("foo" -> "42", "bar" -> "3.14")

    test("Construct and destructure Email.Send message") {
      JsonEventBody(Email.Send(now, testUser, templateName, exampleAttributes)) match {
        case Email.Send(timestamp, to, template, attributes) =>
          assert(timestamp == now && to == testUser && template == templateName && attributes == exampleAttributes)
      }
    }
  }

}
