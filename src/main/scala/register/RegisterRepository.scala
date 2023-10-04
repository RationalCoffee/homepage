package org.rational.coffee
package register

import zio.UIO

trait RegisterRepository:
  def create(preUser: PreUser): UIO[Unit]
