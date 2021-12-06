package adventofcode

import scala.util.{Failure, Success, Try}
// import scala.compiletime.constValue
import sttp.client3._

object Support:

	var session=""

	type PartNumber = 1 | 2

	type Output = Any

	opaque type Input <: String = String

	trait Solution[N <: Int]:
		def apply(input: Input, part: Part[N]): Unit

	final class Part[N <: Int](day: N):
		import scala.collection.mutable
		private[Support] var last: Int = 0
		def update(part: PartNumber, v: => Output): Unit =
			require(part == last + 1)
			last = part
			Try(v).map(String.valueOf) match
				case Success(output) =>
					println(s"Day $day part $part: '$output'")
				case Failure(e: NotImplementedError) =>
					println(s"(ignored part $part)")
				case Failure(e) => throw new Exception(e)

	inline def Day[N <: Int](day: N)(implementation: Solution[N]): Unit = implementation(getInput(day), new Part(day))

	private def getInput(day: Int) = 
		val request = basicRequest.get(uri"https://adventofcode.com/2021/day/$day/input").header("cookie",s"session=${session}")
		val backend = HttpURLConnectionBackend()
		val response = request.send(backend)
		response.body match{
			case Right(body) => body
			case _ => throw new Exception(s"Error obtaining input for day $day")
		} 