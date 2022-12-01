import cats.effect.testing.scalatest.AsyncIOSpec
import fs2.Stream
import org.scalatest.freespec.AsyncFreeSpec
import org.scalatest.matchers.should.Matchers
import wsoczek.project.streams.SubStreamProcessor

class SubStreamProcessorSpec extends AsyncFreeSpec with AsyncIOSpec with Matchers {

  "Substream processor " - {
    "valid flow" in {
      Stream(1,2,3,4)
        .broadcastThrough(SubStreamProcessor(2).flow:_*)
        .compile
        .toList
        .asserting(_ should contain theSameElementsAs List((0, 6), (1,4)))
    }
    "empty stream" in {
      Stream()
        .broadcastThrough(SubStreamProcessor(2).flow:_*)
        .compile
        .toList
        .asserting(_ should contain theSameElementsAs List())
    }
    "should fail when processor with non positive substreams number is created" in {
      assertThrows[IllegalArgumentException](SubStreamProcessor(0))
    }
  }
}