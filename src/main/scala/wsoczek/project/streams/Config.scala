package wsoczek.project.streams

import cats.effect.IO
import fs2.kafka.ProducerSettings
import pureconfig.ConfigSource
import pureconfig.error.ConfigReaderFailures
import pureconfig.generic.auto._

case class Config(subStreamsNumber: Int,
                  kafkaTopic: String,
                  kafkaBootstrapServer: String,
                  sourceFilePath: String) {
  val producerConfig: ProducerSettings[IO, String, String] =
    ProducerSettings[IO, String, String].withBootstrapServers(kafkaBootstrapServer)
}

object Config {
  def read: IO[Config] = {
    val config: Either[ConfigReaderFailures, Config] = ConfigSource.default.load[Config]
    IO.fromEither(
      config
        .left
        .map(
          failures => new RuntimeException(s"Configuration parsing failed with errors: ${failures.toList.map(_.description)}")
        )
    )
  }

}

