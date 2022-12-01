package wsoczek.project.streams

import cats.effect.IO
import fs2.Pipe

class SubStreamProcessor private (pipesNumber: Int) {
  def flow: List[Pipe[IO, Int, (Int, Int)]] =
    List.tabulate(pipesNumber)(n => _.filter(_ % pipesNumber == n)
    .reduce(_ + _)
    .map(sum => (n, sum))
    )
}

object SubStreamProcessor {
  def apply(pipeNumber: Int): SubStreamProcessor = {
    require(pipeNumber > 0, "`pipeNumber` should be greater than zero")
    new SubStreamProcessor(pipeNumber)
  }
}
