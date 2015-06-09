package net.francesbagual.sandbox.microservice.akka

@SerialVersionUID(1L)
sealed trait Message


object PersonService {
    case class SayHello(name: String) extends Message
}