package com.jamesmaggs.halmos

sealed trait Set[+A] {
  def contains[B >: A](element: B): Boolean
  def ∋[B >: A](element: B) = contains(element)
  def ∌[B >: A](element: B) = !contains(element)
}

case object EmptySet extends Set[Nothing] {
  override def contains[B >: Nothing](element: B) = false
}

case class FiniteSet[A](as: A*) extends Set[A] {
  override def contains[B >: A](element: B) = as.contains(element)
}

object Set {
  val empty = EmptySet
  val ∅ = empty

  def apply[A](as: A*) = FiniteSet[A](as: _*)
}