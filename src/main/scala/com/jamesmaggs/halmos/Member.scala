package com.jamesmaggs.halmos

import scala.language.implicitConversions

trait Member[T] {
  def elementOf(set: Set[T]): Boolean
  def notElementOf(set: Set[T]) = !elementOf(set)
  def ∈(set: Set[T]) = elementOf(set)
  def ∉(set: Set[T]) = notElementOf(set)
}

object Member {
  implicit def asMember[T](element: T): Member[T] = new Member[T] {
    override def elementOf(set: Set[T]): Boolean = set.contains(element)
  }
}
