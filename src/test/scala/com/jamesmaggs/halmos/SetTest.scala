package com.jamesmaggs.halmos

import com.jamesmaggs.halmos.Membership._
import com.jamesmaggs.halmos.Set._
import com.jamesmaggs.halmos.SetOps._
import com.jamesmaggs.halmos.Generators._

import org.scalacheck.Prop._
import org.scalacheck.Properties

import scala.util.Random._

class SetTest extends Properties("set") {

  property("empty set is a subset of all") = forAll { set: Set[Int] =>
    ∅ ⊂ set
  }

  property("element of") = forAll { elements: List[Int] =>
    val set = Set(elements: _*)
    elements.nonEmpty ==> (shuffle(elements).head ∈ set)
  }

  property("only subset of the empty set is the empty set itself") = forAll { set: Set[Int] =>
    (set ⊂ ∅) ==> (set === ∅)
  }

  property("set equality is transitive") = forAll { (a: Set[Int], b: Set[Int], c: Set[Int]) =>
    ((a === b) && (b === c)) ==> (a === c)
  }
}