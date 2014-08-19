package com.blinkbox.books.schemas.actions.email

import com.blinkbox.books.messaging._
import org.joda.time.DateTime

package v2 {

  case class User(emailAddress: String, id: String)

  object Email {

    case class Send(timestamp: DateTime, to: User, templateName: String, attributes: Map[String, String])

    implicit object Send extends JsonEventBody[Send] {
      val jsonMediaType = MediaType("application/vnd.blinkbox.books.actions.email.send.v2+json")
      def unapply(body: EventBody): Option[(DateTime, User, String, Map[String, String])] =
        JsonEventBody.unapply[Send](body).flatMap(Send.unapply)
    }

  }

}
