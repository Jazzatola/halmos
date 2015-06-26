package com.jamesmaggs.halmos

import com.jamesmaggs.halmos.Generators._
import com.jamesmaggs.halmos.Membership._
import com.jamesmaggs.halmos.Set._
import com.jamesmaggs.halmos.SetOps._
import org.scalacheck.Prop._
import org.scalacheck.Properties

class SetTest extends Properties("Set") {

  property("empty set is a subset of all") = forAll { set: Set[Int] =>
    ∅ ⊂ set
  }

  property("element of") = forAll { (element: Int, set: Set[Int]) =>
    element ∈ (set + element)
  }

  property("empty set has no elements") = forAll { element: Int =>
    element ∉ ∅
  }

  property("only subset of the empty set is the empty set itself") = forAll { set: Set[Int] =>
    (set ⊂ ∅) == (set === ∅)
  }

//  property("set equality is transitive") = forAll { (a: Set[Int], b: Set[Int], c: Set[Int]) =>
//    ((a === b) && (b === c)) ==> (a === c)
//  }
}