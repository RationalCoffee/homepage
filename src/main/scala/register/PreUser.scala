package org.rational.coffee
package register

import com.google.gson.Gson

private val gson = Gson()

case class PreUser(email: String)

object PreUser:
  def fromJson(jsonString: String): PreUser =
    gson.fromJson(jsonString, classOf[PreUser])
