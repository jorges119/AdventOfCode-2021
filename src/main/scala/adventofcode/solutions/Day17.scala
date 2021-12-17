package adventofcode.solutions

import adventofcode.Support.*

@main def Day17 = Day(17) { (input, part) =>

  val target = input.trim.match{ case s"$n=$a..$b, y=$c..$d" => (Array(a.toInt,b.toInt), Array(c.toInt,d.toInt))}

  def calcVector(i: (Int, Int)) : Array[(Int,Int)] = {
    val (x,y) = i
    
    LazyList.from(1).map(c => ((0 until c).map(v => Seq(x.abs - v, 0).max * (x/x.abs)).sum, (0 until c).map(v => y - v).sum))
    .takeWhile((nx,ny) => nx <= target._1.max  && ny >= target._2.min).toArray
  }

  // Not optimized ~40sec
  val res = (for x <- (1 to target._1.max).reverse.takeWhile(a => (0 to a).map(a - _).sum >= target._1.min) yield {
    for y <- target._2.min to (5*((target._2(0) - target._2(1)).abs + 1)) yield {
      calcVector((x,y))
    }
  }).flatten.toArray.filter(a=>  target._1.min <= a.last._1 && a.last._1 <= target._1.max && target._2.min <= a.last._2 && a.last._2 <= target._2.max)
  
  part(1) = res.map(_.maxBy(_._2)._2).max
  part(2) = res.length

}