package adventofcode.solutions

import adventofcode.Support.*

@main def Day19 = Day(19) { (input, part) =>

  val pattern = raw"--- scanner \d+ ---@(\s+)?(.+?)(@(\s+)?@|^)".r//\n.+?(\n\n|^)".r
  val i = pattern.findAllIn(input.replace("\n","@") + "@ @").map(_.trim.split("@").drop(1).filter(_.length > 5).map(_.trim.match{ case s"$a,$b,$c" => (a.toInt,b.toInt,c.toInt)})).toArray

  def getDiffs(i: Array[(Int,Int,Int)]) = {
    i.combinations(2).toList.map(arr => 
      (arr(0), (arr(0)._1 - arr(1)._1, arr(0)._2 - arr(1)._2, arr(0)._3 - arr(1)._3))
      ).toArray
  }

  def reducePoints(ref: Array[(Int,Int,Int)], ent: Array[(Int,Int,Int)]) : (Array[(Int,Int,Int)], (Int,Int,Int)) = {

    val deltas = ent.map((x,y,z) => ref.map((rx,ry,rz) => (rx-x, ry-y, rz-z))).flatten.groupBy(identity).toArray.map((a,b) => (a, b.length)).filter(_._2 >= 12)

    if (deltas.length > 0)
      val delta = deltas.head._1
      val newEnt = ent.map((x,y,z)=> (x + delta._1, y + delta._2, z + delta._3))
      ((newEnt ++ ref).distinct, delta)
    else
      (Array.fill(ref.length + ent.length)((0,0,0)), (0,0,0)) // discarded anyway
  }

  def keepLooking(init: Array[(Int,Int,Int)], deltas: Array[(Int,Int,Int)], rest: Array[Array[Array[(Int,Int,Int)]]]) : (Int, Int) = {
    if (rest.length == 0)
      (init.length, getDiffs(deltas).map((_,b) => (b._1 + b._2 + b._3).abs).max)
    else
      val update = rest.to(LazyList).zipWithIndex.map((scan, idx) => 
          scan.to(LazyList).map( ori => 
            reducePoints(
              init,
              ori
            )
          ).dropWhile(n => n._1.length > ((init.length + scan(0).length) - 12)).take(1).map((idx,_))
        ).flatten.take(1).head
      keepLooking(update._2._1, deltas :+ update._2._2, rest.take(update._1) ++ rest.drop(update._1 + 1))
  }

  val init = i.head
  val rest = i.tail

  val orientations = rest.map(x => List(0,1,2).permutations.toArray.map(p => 
        Array(
          Array(1,1,1),
          Array(-1,1,1),
          Array(-1,-1,1),
          Array(1,-1,1),
          Array(-1,-1,-1),
          Array(1,-1,-1),
          Array(1,1,-1),
          Array(-1,1,-1),
        ).map(c=> x.map(r => 
          val tmp = Array(r._1, r._2, r._3)
          (c(0) * tmp(p(0)), c(1) * tmp(p(1)), c(2) * tmp(p(2)))
          ) ) ).flatten )

  val res = keepLooking(init, Array((0,0,0)), orientations)

  part(1) = res._1
  part(2) = res._2

}