package com.jamesmaggs.halmos

import scala.language.implicitConversions

trait Equality[A] {
  def equal(a1: A, a2: A): Boolean
}

object Equality {
  def apply[A](implicit instance: Equality[A]): Equality[A] = instance
  
  def equality[A](f: (A, A) => Boolean): Equality[A] = new Equality[A] {
    override def equal(a1: A, a2: A): Boolean = f(a1, a2)
  }
}

trait EqualityOps[A] {
  def self: A
  implicit def equality: Equality[A]
  final def equalTo(a: A): Boolean = equality.equal(self, a)
  final def ===(a: A) = equalTo(a)
}

object EqualityOps {
  implicit def withEquality[A](a: A)(implicit instance: Equality[A]): EqualityOps[A] = new EqualityOps[A] {
    override def self = a
    override implicit def equality: Equality[A] = instance
  }
}