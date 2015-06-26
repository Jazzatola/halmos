package com.jamesmaggs.halmos

import scala.language.implicitConversions

trait SetOps[A] {
  def self: Set[A]
  def isEmpty = Set.isEmpty(self)
  
  def isEqual(set: Set[A]) = Set.isEqual(self, set)
  def ===(set: Set[A]) = isEqual(set)
  def =/=(set: Set[A]) = !isEqual(set)
  def ≠(set: Set[A]) = !isEqual(set)

  def contains(a: A) = Set.contains(self, a)
  def ∋(element: A) = contains(element)
  def ∌(element: A) = !contains(element)

  def subsetOf(set: Set[A]) = Set.isSubset(set, self)
  def supersetOf(set: Set[A]) = Set.isSubset(self, set)
  def ⊂(set: Set[A]) = subsetOf(set)
  def ⊃(set: Set[A]) = supersetOf(set)
  def ⊄(set: Set[A]) = !subsetOf(set)
  def ⊅(set: Set[A]) = !supersetOf(set)
  def ⊆(set: Set[A]) = subsetOf(set) || isEqual(set)
  def ⊇(set: Set[A]) = supersetOf(set) || isEqual(set)

  def mkString = Set.mkString(self)
}

object SetOps {
  implicit def withSetOps[A](set: Set[A]): SetOps[A] = new SetOps[A] {
    override def self: Set[A] = set
  }
}

