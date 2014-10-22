package com.blinkbox.books.schemas.events.gifting.v2

import com.blinkbox.books.messaging.{EventBody, MediaType, JsonEventBody}
import org.joda.time.DateTime

case class Campaign(id: Int, name: String)

object Gift {
  case class Redeemed(campaign: Campaign, giftDetails: Map[String, String], userId: Int, redeemedAt: DateTime)

  implicit object Redeemed extends JsonEventBody[Redeemed] {
    override val jsonMediaType = MediaType("application/vnd.blinkbox.books.events.gifting.redeemed.v2+json")
    def unapply(body: EventBody): Option[(Campaign, Map[String, String], Int, DateTime)] =
      JsonEventBody.unapply[Redeemed](body).flatMap(Redeemed.unapply)
  }
}
