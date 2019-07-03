package io.chrisdavenport.clientexample

import org.http4s.server.blaze.BlazeServerBuilder
import org.http4s.client.blaze.BlazeClientBuilder
import org.http4s.dsl._
import org.http4s._
import org.http4s.implicits._
import org.http4s.client._
import cats.effect._
import fs2._
import scala.concurrent.ExecutionContext.global

object ClientExampleServer {
    def serve[F[_]](implicit Effect: ConcurrentEffect[F], T: Timer[F]) : Stream[F, ExitCode] = 
        for {
            _ <- Stream.eval(Sync[F].delay(println("Starting Client")))
            client <- Stream.resource(BlazeClientBuilder[F](global).resource)

            service = getGoogleService[F](client)
            exitCode <- BlazeServerBuilder[F].bindHttp(8080, "0.0.0.0")
                .withHttpApp(service.orNotFound)
                .serve
        } yield exitCode


    def getGoogleService[F[_]: Effect](c: Client[F]): HttpRoutes[F] = {
        val _ = c
        val dsl = new Http4sDsl[F]{}
        import dsl._

        HttpRoutes.of[F]{
            case GET -> Root / "google" => 
                Ok()
        }

    }

}