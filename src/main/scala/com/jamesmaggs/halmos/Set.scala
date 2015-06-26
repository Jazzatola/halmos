package com.jamesmaggs.halmos

sealed trait Set[+A] {
  override def toString: String = Set.mkString(this)
}

case object EmptySet extends Set[Nothing]
case class FiniteSet[+A](head: A, tail: Set[A]) extends Set[A]

object Set {

  import EqualityOps._

  val empty = EmptySet
  val ∅ = empty

  def apply[A](as: A*): Set[A] = reverse(construct(as.distinct, empty))

  private def construct[A](as: Seq[A], set: Set[A]): Set[A] =
    if(as.isEmpty) set
    else construct(as.tail, FiniteSet(as.head, set))

  def isEqual[A](set: Set[A], other: Set[A]) = equality.equal(set, other)

  def isEmpty[A](set: Set[A]): Boolean = set match {
    case EmptySet => true
    case _ => false
  }

  def isSubset[A](set: Set[A], subset: Set[A]): Boolean =
    forAll(subset, (a: A) => contains(set, a))

  def contains[A](set: Set[A], a: A): Boolean =
    exists(set, (aa: A) => aa === a)

  def add[A](set: Set[A], a: A): Set[A] =
    if(contains(set, a)) set
    else FiniteSet(a, set)

  def size[A](set: Set[A]): Int =
    fold(set, 0)((acc, _) => acc + 1)

  def mkString[A](set: Set[A]): String = set match {
    case EmptySet => "{}"
    case FiniteSet(a, as) => fold(as, "{" + a)(_ + ", " + _) + "}"
  }

  private def reverse[A](set: Set[A]): Set[A] = {
    val z: Set[A] = empty
    fold(set, z)((acc, a) => FiniteSet(a, acc))
  }

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