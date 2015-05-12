package com.jamesmaggs.halmos

sealed trait Set[+A]
case object EmptySet extends Set[Nothing]
case class FiniteSet[+A](head: A, tail: Set[A]) extends Set[A]

object Set {

  import EqualityOps._

  val empty = EmptySet
  val ∅ = empty

  def apply[A](as: A*): Set[A] =
    if(as.isEmpty) empty
    else FiniteSet[A](as.head, apply(as.tail: _*))

  def isEqual[A](set: Set[A], other: Set[A]) = equality.equal(set, other)

  def isEmpty[A](set: Set[A]): Boolean = set match {
    case EmptySet => true
    case _ => false
  }

  def isSubset[A](set: Set[A], subset: Set[A]): Boolean =
    forAll(subset, (a: A) => contains(set, a))

  def contains[A](set: Set[A], a: A): Boolean =
    exists(set, (aa: A) => aa === a)

  private def exists[A](set: Set[A], f: A => Boolean): Boolean =
    fold(set, false)(_ || f(_))

  private def forAll[A](set: Set[A], f: A => Boolean): Boolean =
    fold(set, true)(_ && f(_))

  private def fold[A,B](set: Set[A], z: B)(f: (B, A) => B): B = set match {
    case EmptySet => z
    case FiniteSet(a, as) => fold(as, f(z, a))(f)
  }

  implicit def defaultEquality[A]: Equality[A] = Equality.equality(_ == _)

  implicit def equality[A]: Equality[Set[A]] = Equality.equality {
    (a, b) => isSubset(a, b) && isSubset(b, a)
  }
}