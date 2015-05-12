package com.jamesmaggs.halmos

import scala.language.implicitConversions

trait Membership[A] {
  def elementOf(set: Set[A]): Boolean
  def notElementOf(set: Set[A]) = !elementOf(set)
  def ∈(set: Set[A]) = elementOf(set)
  def ∉(set: Set[A]) = notElementOf(set)
}

object Membership {
  implicit def asMember[A](a: A): Membership[A] = new Membership[A] {
    override def elementOf(set: Set[A]): Boolean = Set.contains(set, a)
  }
}
