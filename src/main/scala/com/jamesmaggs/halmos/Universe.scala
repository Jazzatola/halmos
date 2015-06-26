package com.jamesmaggs.halmos

case class Universe[+A](elements: Seq[A])

object Universe {
  val ℕ = Universe(  0 to 100)
  val ℤ = Universe(-50 to 50)
}
