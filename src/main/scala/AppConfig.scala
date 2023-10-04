package org.rational.coffee

import com.google.gson.Gson
import org.yaml.snakeyaml.Yaml
import zio.ZLayer

import java.io.{File, FileReader}
import java.util
import javax.sql.DataSource
import com.mysql.cj.jdbc.MysqlDataSource

import scala.collection.convert.ImplicitConversions.`map AsScala`

private val gson = new Gson()
private val env: String =
  if System.getenv("environ") == null then "alpha" else System.getenv("environ")

private val configReader = new FileReader(
  new File(s"src/main/resources/configs/$env.yml")
)
private val secretReader = new FileReader(
  new File(s"src/main/resources/configs/secrets.json")
)

private val yamlData: util.LinkedHashMap[String, Any] = new Yaml().load(
  configReader
)
private val dbData: util.LinkedHashMap[String, Any] =
  yamlData.get("db").asInstanceOf[util.LinkedHashMap[String, Any]]

private val secretsData: util.LinkedHashMap[String, Any] = gson.fromJson(
  secretReader,
  util.LinkedHashMap[String, Any]().getClass
)

private val data = dbData ++ secretsData
private val dataString: String = gson.toJson(data)

case class DBConfig(
    host: String,
    database: String,
    port: Int,
    user: String,
    password: String
):
  def url = s"jdbc:mysql://${host}:${port}/${database}?useSSl=false"
  def createDataSource: MysqlDataSource =
    val ds = new MysqlDataSource()
    ds.setURL(url)
    ds.setDatabaseName(database)
    ds.setUser(user)
    ds.setPassword(password)
    ds
object DBConfig:
  def layer: ZLayer[Any, Throwable, DBConfig] =
    ZLayer.succeed(
      new Gson().fromJson(
        dataString,
        classOf[DBConfig]
      )
    )

case class AppConfig(db: DBConfig)
object AppConfig:
  def layer: ZLayer[DBConfig, Throwable, AppConfig] =
    ZLayer.fromFunction(AppConfig(_))
