package models

import collection.immutable.ListMap

case class Gist(id: Int, title: String, makeDate: String, files: File*)

object Gist {

  def findAll: Iterable[Gist] = Db.values

  def findById(id: Int): Option[Gist] = Db.get(id)

  private lazy val Db: Map[Int, Gist] = ListMap(
    1924011 -> Gist(1924011, "mapBetween関数 trait版", "2012/02/28",
      File("ExSeq.scala",
        """|trait ExSeq[+A] {
          |  self: Seq[A] =>
          |
          |  def mapBetween[B](f:(A,A)=>B): Iterator[B] = {
          |    sliding(2).map(s=>f(s(0),s(1)))
          |  }
          |
          |}
          |object ExSeq {
          |
          |  import scala.collection.immutable.Range
          |
          |  def apply(r: Range): ExSeq[Int] = new Range(r.start, r.end, r.step) with ExSeq[Int]
          |
          |}
          |""".stripMargin),
      File("mapBetween.scala",
        """|object mapBetween {
          |
          |  def main(args: Array[String]) {
          |    val range2 = 1 to 10
          |    println(range2.mapBetween(_ + _) mkString ",")
          |    println(range2.mapBetween(_ * _) mkString ",")
          |    println(range2.mapBetween(_ - _) mkString ",")
          |  }
          |
          |  implicit def seqToExtSeq[A](seq: Seq[A]) = new {
          |    def mapBetween[B](f: (A, A) => B): Iterator[B] = {
          |      seq.sliding(2).map(s => f(s(0), s(1)))
          |    }
          |  }
          |
          |}
          |""".stripMargin)
    ),
    1865400 -> Gist(1865400, "Scala環境構築", "2012/02/20",
      File("reStructuredText",
        """|**ScalaJPのWikiに置いて貰いました。** `Scala開発環境構築手順`_
          |
          |.. _Scala開発環境構築手順: https://github.com/scalajp/scalajp.github.com/wiki/scala-develop-environment
          |""".stripMargin)
    ),
    1372563 -> Gist(1372563, "無限Streamのgrouped", "2011/11/16",
      File("Scala",
        """|scala> val s: Stream[Int] = Stream.continually {1}
          |s: Stream[Int] = Stream(1, ?)
          |
          |scala> val i = s.grouped(3)
          |i: Iterator[scala.collection.immutable.Stream[Int]] = non-empty iterator
          |
          |scala> i take 5 foreach println
          |Stream(1, ?)
          |Stream(1, ?)
          |Stream(1, ?)
          |Stream(1, ?)
          |Stream(1, ?)
          |
          |scala> i take 5 foreach {x => x foreach println}
          |1
          |1
          |1
          |1
          |1
          |1
          |1
          |1
          |1
          |1
          |1
          |1
          |1
          |1
          |1
          |
          |scala>
          |""".stripMargin)
    )
  )
  
}