package com.jamesmaggs.halmos

import org.scalacheck.{Arbitrary, Gen}

object Generators {

  implicit def arbitrarySet[A](implicit a: Arbitrary[A]): Arbitrary[Set[A]] = Arbitrary(genSet[A](a.arbitrary))

  def genSet[A](genElem: Gen[A]) = Gen.listOf[A](genElem).map(Set[A])
}