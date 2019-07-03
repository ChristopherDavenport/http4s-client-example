package io.chrisdavenport.clientexample

import cats.implicits._
import org.http4s.client.blaze.BlazeClientBuilder
import org.http4s.dsl._
import org.http4s._
import org.http4s.client._
import cats.effect._
import fs2._
import scala.concurrent.ExecutionContext
import org.http4s.server.blaze.BlazeServerBuilder

object ClientExampleServer {
    def serve[F[_]](implicit Effect: Effect[F], EC: ExecutionContext) : Stream[F, StreamApp.ExitCode] = 
        for {
            _ <- Stream.eval(Sync[F].delay(println("Starting Client")))
            client <- Http1Client.stream[F]()

            service = getGoogleService[F](client)
            exitCode <- BlazeServerBuilder[F].bindHttp(8080, "0.0.0.0").mountService(service).serve
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