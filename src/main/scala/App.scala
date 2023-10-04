package org.rational.coffee

import register.{MySqlRegisterRepository, RegisterApp}

import zio.http.Server
import zio.{Scope, ZIO, ZIOAppArgs, ZIOAppDefault}

object App extends ZIOAppDefault:
  override def run: ZIO[Any with ZIOAppArgs with Scope, Any, Any] =
    (for
      registerApp <- ZIO.service[RegisterApp]
      _ <- Server.serve(
        registerApp.http.withDefaultErrorResponse
      )
    yield ())
      .provide(
        Server.defaultWithPort(8080),
        DBConfig.layer,
        AppConfig.layer,
        MySqlRegisterRepository.layer,
        RegisterApp.layer
      )
