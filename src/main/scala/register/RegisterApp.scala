package org.rational.coffee
package register

import zio.*
import zio.http.*

case class RegisterApp(registerRepository: RegisterRepository):
  val http: Http[Any, Nothing, Request, Response] =
    Http.collectZIO[Request] {
      case req @ Method.POST -> Root / "api" / "v1" / "pre-register" =>
        for
          body <- req.body.asString.orDie
          preUser = PreUser.fromJson(body)
          _ <- registerRepository.create(preUser)
        yield Response.ok
    }

object RegisterApp:
  val layer: ZLayer[RegisterRepository, Nothing, RegisterApp] =
    ZLayer.fromFunction(RegisterApp(_))
