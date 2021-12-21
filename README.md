_My solutions to the 2021 edition of [Advent of Code](https://adventofcode.com/2021)._

## Problem statements & solutions

<div align="center">

  | Day | Code | Part 1 | Part 2 |
  |:---:|:---:|:---|:---|
  | **[01](https://adventofcode.com/2021/day/1)** | [solution](src/main/scala/adventofcode/solutions/Day01.scala) | `30305` | `25837` |
  | **[02](https://adventofcode.com/2021/day/2)** | [solution](src/main/scala/adventofcode/solutions/Day02.scala) | `28901` | `27385` |
  | **[03](https://adventofcode.com/2021/day/3)** | [solution](src/main/scala/adventofcode/solutions/Day03.scala) | `35484` | `38224` |
  | **[04](https://adventofcode.com/2021/day/4)** | [solution](src/main/scala/adventofcode/solutions/Day04.scala) | `16816` | `15220` |
  | **[05](https://adventofcode.com/2021/day/5)** | [solution](src/main/scala/adventofcode/solutions/Day05.scala) | `34102` | `34491` |
  | **[06](https://adventofcode.com/2021/day/6)** | [solution](src/main/scala/adventofcode/solutions/Day06.scala) | `24388` | `35596` |
  | **[07](https://adventofcode.com/2021/day/7)** | [solution](src/main/scala/adventofcode/solutions/Day07.scala) | `24460` | `22776` |
  | **[08](https://adventofcode.com/2021/day/8)** | [solution](src/main/scala/adventofcode/solutions/Day08.scala) | `28477` | `19462` |
  | **[09](https://adventofcode.com/2021/day/9)** | [solution](src/main/scala/adventofcode/solutions/Day09.scala) | `44102` | `41753` |
  | **[10](https://adventofcode.com/2021/day/10)** | [solution](src/main/scala/adventofcode/solutions/Day10.scala) | `22459` | `21448` |
  | **[11](https://adventofcode.com/2021/day/11)** | [solution](src/main/scala/adventofcode/solutions/Day11.scala) | `31033` | `30752` |
  | **[12](https://adventofcode.com/2021/day/12)** | [solution](src/main/scala/adventofcode/solutions/Day12.scala) | `21689` | `19404` |
  | **[13](https://adventofcode.com/2021/day/13)** | [solution](src/main/scala/adventofcode/solutions/Day13.scala) | `23492` | `23603` |
  | **[14](https://adventofcode.com/2021/day/14)** | [solution](src/main/scala/adventofcode/solutions/Day14.scala) | `8802` | `25076` |
  | **[15](https://adventofcode.com/2021/day/15)** | [solution](src/main/scala/adventofcode/solutions/Day15.scala) | `12420` | `27712` |
  | **[16](https://adventofcode.com/2021/day/16)** | [solution](src/main/scala/adventofcode/solutions/Day16.scala) | `18284` | `19803` |
  | **[17](https://adventofcode.com/2021/day/17)** | [solution](src/main/scala/adventofcode/solutions/Day17.scala) | `11271` | `9781` |
  | **[18](https://adventofcode.com/2021/day/18)** | [solution](src/main/scala/adventofcode/solutions/Day18.scala) | `14751` | `14543` |
  | **[19](https://adventofcode.com/2021/day/19)** | [solution](src/main/scala/adventofcode/solutions/Day19.scala) | `10450` | `10847` |
  | **[20](https://adventofcode.com/2021/day/20)** | [solution](src/main/scala/adventofcode/solutions/Day20.scala) | `15818` | `15485` |
  | **[21](https://adventofcode.com/2021/day/21)** | [solution](src/main/scala/adventofcode/solutions/Day21.scala) | `13484` | `13484` |
  | **[22](https://adventofcode.com/2021/day/22)** | [](src/main/scala/adventofcode/solutions/Day22.scala) |  |  |
  | **[23](https://adventofcode.com/2021/day/23)** | [](src/main/scala/adventofcode/solutions/Day23.scala) |  |  |
  | **[24](https://adventofcode.com/2021/day/24)** | [](src/main/scala/adventofcode/solutions/Day24.scala) |  |  |
  | **[25](https://adventofcode.com/2021/day/25)** | [](src/main/scala/adventofcode/solutions/Day25.scala) |  |  |

</div>

The third and last row indicate the ranking for each part. Empty cells mean no participation.

I am using the AdventOfCode challenge as a means to familiarize myself with Scala so the code here is by no means the best solution (hence the low rankings XD, and also **WHO WAKES UP AT 6AM FOR THIS!?**).
If you want quality solutions please [visit this repo](https://github.com/FlorianCassayre/AdventOfCode-2021) which I like to check after I find a solution for ideas on how to improve my usage of Scala (altought I leave my original answers in this repo for the judgement of everyone else).

## Usage

This project runs on [Scala](https://scala-lang.org) `3.1.0` and sbt `1.5.5`.

STTP client is used to obtain the challenge inputs directly from the web. For it to work you need to provide your session id (once logged in the website check your cookies), copy it in the Support object definition file.

I am using Metals extension on VSCode to run the code directly from the editor. For development purposes I am using the worksheet feature that provides easier interaction, but I don't commit that file because is more of a draft.

## License

MIT