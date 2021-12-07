package adventofcode.solutions

import adventofcode.Support.*

@main def Day07 = Day(7) { (input, part) =>

  val i = input.split(",").map(_.trim.toInt).toList 

  part(1) = (for d <- i.min to i.max yield i.map(x=>(x - d).abs).sum).min
    
  part(2) = (for d <- i.min to i.max yield i.map(x=>(0 to (x - d).abs).sum).sum).min
}