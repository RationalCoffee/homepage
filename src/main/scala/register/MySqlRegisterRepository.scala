package org.rational.coffee
package register
import io.getquill.*
import zio.*

class MySqlRegisterRepository(config: AppConfig) extends RegisterRepository:
  val ctx = new MysqlJdbcContext(SnakeCase, config.db.createDataSource)
  import ctx.*

  override def create(preUser: PreUser): UIO[Unit] =
    ZIO.succeed {
      run(query[PreUser].insertValue(lift(preUser)))
    }.unit

object MySqlRegisterRepository:
  val layer: ZLayer[AppConfig, Nothing, MySqlRegisterRepository] =
    ZLayer.fromFunction(MySqlRegisterRepository(_))
