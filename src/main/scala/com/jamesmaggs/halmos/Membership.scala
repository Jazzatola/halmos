package com.jamesmaggs.halmos

import scala.language.implicitConversions

trait Membership[A] {
  def self: A
  def elementOf(set: Set[A]) = Set.contains(set, self)
  def notElementOf(set: Set[A]) = !elementOf(set)
  def ∈(set: Set[A]) = elementOf(set)
  def ∉(set: Set[A]) = notElementOf(set)
}

object Membership {
  implicit def asMember[A](a: A): Membership[A] = new Membership[A] {
    override def self = a
  }
}
