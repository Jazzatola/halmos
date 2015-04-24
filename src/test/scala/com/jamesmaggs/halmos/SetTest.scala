package com.jamesmaggs.halmos

import org.scalatest.FunSuite

class SetTest extends FunSuite {

  import Member._

  val A = Set[Int](_ % 2 == 0)

  test("membership") {
    assert(2 ∈ A)
    assert(3 ∉ A)
    assert(A ∋ 2)
  }

}
