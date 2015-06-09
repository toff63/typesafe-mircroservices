# typesafe-mircroservices

Sandbox to play with a frontend that is using Play Framework and a backend that uses Akka

In a terminal run 

	$ cd web && sbt run

In another terminal run

	$ cd person-srv && sbt run 4444

Then, you can go first to [http://localhost:9000](http://localhost:9000) so Play application compiles and start and then [http://localhost:9000/hello](http://localhost:9000/hello). The second request will go from Play to Person-SRV. It is using the DistributedPubSubExtension akka extension to discover where the hello actor is running.
