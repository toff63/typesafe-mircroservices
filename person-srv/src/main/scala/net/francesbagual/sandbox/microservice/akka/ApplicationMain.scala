package net.francesbagual.sandbox.microservice.akka

import akka.actor.ActorSystem
import akka.contrib.pattern.DistributedPubSubExtension
import akka.contrib.pattern.DistributedPubSubMediator
import com.typesafe.config.ConfigFactory
import scala.collection.JavaConversions._
import akka.actor.actorRef2Scala
import scala.concurrent.Future
import scala.concurrent.Await

object ApplicationMain extends App {

  // Simple cli parsing
  val port = args match {
    case Array()     => "2551"
    case Array(port) => port
    case args        => throw new IllegalArgumentException(s"only ports. Args [ $args ] are invalid")
  }

  // System initialization
  val properties = Map("akka.remote.netty.tcp.port" -> "2551")

  implicit val system = ActorSystem("cluster-example", ConfigFactory.load())
  val personAggregate = system.actorOf(HelloActor.props, "hello")
}