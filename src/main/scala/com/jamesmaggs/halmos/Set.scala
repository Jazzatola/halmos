package com.jamesmaggs.halmos

case class Set[T](predicate: T => Boolean) {
  def contains(element: T) = predicate(element)
  def ∋(element: T) = contains(element)
}