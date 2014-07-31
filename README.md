# message-schemas

Contains strongly typed versions of the message schemas.

## Creating and consuming JSON messages

To create a JSON event body use the `JsonEventBody` helper with your message type, for example:

~~~scala
val body: EventBody = JsonEventBody(MyEvent("some info"))
~~~

To destructure an `EventBody` you can use pattern matching, which will select the appropriate message
class based on the media type, for example:

~~~scala
body match {
  case MyEvent(info) => println(info)
  case _ => println("unexpected message")
}
~~~
