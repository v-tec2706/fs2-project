package wsoczek.project.streams

import cats.effect.{IO, IOApp}
import fs2.Stream
import fs2.io.file.{Files, Path}
import fs2.kafka.ProducerResult

object Main extends IOApp.Simple {

  override def run: IO[Unit] = for {
    config <- Config.read
    _ <- IO.println(s"Start processing...")
    _ <- numbersProcessingStream(config).compile.drain
    _ <- IO.println(s"Processing completed.")
  } yield ()

  def numbersProcessingStream(config: Config): Stream[IO, ProducerResult[Unit, String, String]] =
    readIntStreamFromFile(config.sourceFilePath)
      .broadcastThrough(SubStreamProcessor(config.subStreamsNumber).flow:_*)
      .through(new KafkaProducer(config.producerConfig, config.kafkaTopic).flow)

  private def readIntStreamFromFile(path: String): Stream[IO, Int] = Files[IO].readUtf8Lines(Path(path)).map(_.toInt)
}
