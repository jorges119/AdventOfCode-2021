package adventofcode.solutions

import adventofcode.Support.*

@main def Day01 = Day(1) { (input, part) =>

  val i = input.split("\\n").map(_.trim.toInt).toList 
  part(1) =  i.sliding(2).map{case Seq(x,y) => if(y>x) 1 else 0 case _ => 0}.sum 
  part(2) =  i.sliding(3).map{case Seq(x,y,z) => y+x+z case _ => 0}.sliding(2).map{case Seq(x,y)=> if(y > x) 1 else 0 case _ => 0}.sum

}