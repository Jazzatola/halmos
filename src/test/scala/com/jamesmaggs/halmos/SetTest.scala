package com.jamesmaggs.halmos

import Set._
import Member._

import org.scalatest.FunSuite

class SetTest extends FunSuite {

  val A = Set(2, 4, 6, 8)

  test("membership") {
    assert(2 ∈ A)
    assert(3 ∉ A)
    assert(A ∋ 2)
    assert(A ∌ 3)
  }

  test("empty set") {
    assert(2 ∉ ∅)
    assert("3" ∉ ∅)
    assert(false ∉ ∅)
    assert(∅ ∌ 2)
  }
}
