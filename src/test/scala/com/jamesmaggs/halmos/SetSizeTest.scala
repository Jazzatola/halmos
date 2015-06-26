package com.jamesmaggs.halmos

import com.jamesmaggs.halmos.Generators._
import com.jamesmaggs.halmos.Set._
import com.jamesmaggs.halmos.SetOps._
import org.scalacheck.Gen._
import org.scalacheck.Prop._
import org.scalacheck.Properties

class SetSizeTest extends Properties("Set") {

  property("empty set has size zero") = forAll(const(empty)) { set: Set[Int] =>
    set.size == 0
  }

  property("we can induce the size of any other set") = forAll { (element: Int, set: Set[Int]) =>
    (set ∌ element) ==> ((set + element).size == (set.size + 1))
  }

}
