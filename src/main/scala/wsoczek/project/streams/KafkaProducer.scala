package wsoczek.project.streams

import cats.effect.IO
import fs2.Stream
import fs2.kafka.{KafkaProducer, ProducerRecord, ProducerRecords, ProducerResult, ProducerSettings}

class KafkaProducer(producerSettings: ProducerSettings[IO, String, String], topic: String) {
  def flow(stream: Stream[IO, (Int, Int)]): Stream[IO, ProducerResult[Unit, String, String]] =
    stream.map { case (divisor, sum) => ProducerRecords.one(ProducerRecord(topic, divisor.toString, sum.toString)) }
      .through(KafkaProducer.pipe(producerSettings))
}
