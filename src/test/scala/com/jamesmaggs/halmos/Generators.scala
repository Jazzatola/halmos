package com.jamesmaggs.halmos

import org.scalacheck.{Arbitrary, Gen}

object Generators {

  def genSet[T](genElem: Gen[T]) = Gen.listOf[T](genElem).map(Set[T])

  implicit def arbitrarySet[T](implicit a: Arbitrary[T]): Arbitrary[Set[T]] = Arbitrary(genSet[T](a.arbitrary))
}