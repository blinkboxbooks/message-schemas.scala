package com.blinkbox.books.schemas.events.gifting.v2

import com.blinkbox.books.messaging.JsonEventBody
import com.blinkbox.books.schemas.events.user.v2.UserId
import org.joda.time.DateTime
import org.scalatest.FunSuite
import Gift._

class SchemaTests extends FunSuite {

  val expectedCampaign = Campaign(1, "book_stickers_campaign")
  val expectedUser = 3246
  val expectedDetails = RedemptionDetails(Map("amount" -> "5.0"))
  val expectedDate = DateTime.parse("2014-10-07T12:20:08Z")

  test("Construct and destructure Gift.Redeemed message"){
    JsonEventBody(Redeemed(expectedCampaign, expectedDetails, expectedUser, expectedDate)) match {
      case Redeemed(actualCamp, actualDetails, actualUser, actualDate) =>
        assert(
          actualCamp == expectedCampaign &&
          actualDetails == expectedDetails &&
          actualUser == expectedUser &&
          actualDate == expectedDate
        )
    }
  }
}
