package adventofcode.solutions

import adventofcode.Support.*
import scala.collection.mutable.PriorityQueue
import scala.collection.mutable.Map

@main def Day15 = Day(15) { (input, part) =>

  val i = input.split("\\n").zipWithIndex.map((a,b) => a.trim.zipWithIndex.map((v,p) => ((p,b), Seq(v).mkString.toInt)).toArray).flatten.to(collection.mutable.Map)

  def pathDijkstra(i: Map[(Int,Int), Int], repTilesXY: Int) = {
    val cC = i.maxBy(_._1._1)._1._1
    val rC = i.maxBy(_._1._2)._1._2

    val size = repTilesXY - 1
    val newMap = i.toArray.map((a,b) => 
      (0 to size).map(x => (0 to size).map(y=> ((a._1 + (cC + 1) * x, a._2 + (rC + 1) * y), (((b + x + y) % 10) + ((b + x + y) / 10), 9999999, false))))
      ).toArray.flatten.flatten.to(collection.mutable.Map)

    val queue = new PriorityQueue[((Int,Int), Int)]()(Ordering.by((a,b)=> b * -1))
    queue.enqueue((0,0) -> 0)

    // Imperative implementation of Dijkstra algorithm
    LazyList.from(1).map(_ => {
      val (point, value) = queue.dequeue
      if (point._1 == ((cC + 1) * repTilesXY - 1) && point._2 == ((rC + 1) * repTilesXY - 1))
        value
      else
        newMap(point) = (0, value, true)
        val (a,b) = point
        val updates = Array((a + 1, b), (a, b + 1), (a - 1, b), (a, b - 1))
                .map(p => (p, (newMap.getOrElse(p, (-1,-1, true)))))
                .filter((_,b) => !b._3 && (b._2 > (value + b._1)))
                .map((a,b) => (a, (b._1, value + b._1 , b._3)))

        updates.foreach((p,v) => {
          newMap(p) = v
          queue.enqueue(p -> v._2)
        })
        -1
    }).dropWhile(_ < 0).take(1)
  }

  part(1) = pathDijkstra(i, 1).head
  part(2) = pathDijkstra(i, 5).head

}