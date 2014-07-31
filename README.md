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

## Modifying message classes

Once a message has been published by a service (in production) then you **must not** make incompatible changes to the schema, otherwise receiving applications may break. That means you can add new optional fields, but cannot remove or rename fields, change the type of fields, or add new mandatory fields. If you need to do any of those things then copy the schema and increment the version.

For example if you have a message class declared like this:

~~~scala
case class SomeEvent(foo: String, bar: Int)
~~~

The making this kind of change is OK:

~~~scala
case class SomeEvent(foo: String, bar: Int, newField: Option[Double] = None)
~~~

But making any of these kinds of changes is not OK:

~~~scala
case class SomeEvent(foo: String) // don't remove fields!
case class SomeEvent(foo: String, stuff: Int) // don't rename fields!
case class SomeEvent(foo: String, bar: Int, newField: Double) // don't add mandatory fields!
~~~

I realise that this section is labouring the point somewhat, but it's really important that you don't break already-deployed clients, so a bit of labouring seems fine.
