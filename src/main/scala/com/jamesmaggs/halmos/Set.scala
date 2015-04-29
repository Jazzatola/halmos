package com.jamesmaggs.halmos

sealed trait Set[+A] {

  def isEmpty: Boolean

  def ===[B >: A](set: Set[B]) = equals(set)
  def =/=[B >: A](set: Set[B]) = !equals(set)
  def ≠[B >: A](set: Set[B]) = !equals(set)

  def contains[B >: A](element: B): Boolean
  def ∋[B >: A](element: B) = contains(element)
  def ∌[B >: A](element: B) = !contains(element)

  def subsetOf[B >: A](set: Set[B]): Boolean
  def supersetOf[B >: A](set: Set[B]) = set.subsetOf(this)
  def ⊂[B >: A](set: Set[B]) = subsetOf(set)
  def ⊃[B >: A](set: Set[B]) = supersetOf(set)
  def ⊄[B >: A](set: Set[B]) = !subsetOf(set)
  def ⊅[B >: A](set: Set[B]) = !supersetOf(set)
  def ⊆[B >: A](set: Set[B]) = subsetOf(set) || equals(set)
  def ⊇[B >: A](set: Set[B]) = supersetOf(set) || equals(set)
}

case object EmptySet extends Set[Nothing] {
  override def isEmpty: Boolean = true
  override def contains[B >: Nothing](element: B) = false
  override def subsetOf[B >: Nothing](set: Set[B]) = true

  override def equals(obj: scala.Any) = obj match {
    case other: Set[_] => other.isEmpty
    case _ => false
  }

  override def toString: String = "{}"
}

case class FiniteSet[A](as: A*) extends Set[A] {

  lazy val elements: Seq[A] = as.distinct

  override def isEmpty = elements.isEmpty

  override def equals(obj: scala.Any) = obj match {
    case EmptySet => isEmpty
    case other: FiniteSet[_] => (this ⊂ other) && (other ⊂ this)
    case _ => false
  }

  override def contains[B >: A](element: B) =  elements.contains(element)

  override def subsetOf[B >: A](set: Set[B]) = set match {
    case EmptySet => isEmpty
    case other: FiniteSet[_] => elements.forall(other ∋ _)
  }

  override def toString: String = elements.mkString("{", ", ", "}")
}

object Set {
  val empty = EmptySet
  val ∅ = empty

  def apply[A](as: A*): Set[A] = if(as.isEmpty) empty else FiniteSet[A](as: _*)
}