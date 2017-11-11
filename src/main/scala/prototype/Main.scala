import sparkmodels.prototipe.NormalAD
import spire.random.Dist

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

object Main {
  def main(args: Array[String]) {

    val zs = ArrayBuffer(0.1, 0.15, 0.2, 0.3, 0.25)
    val s = NormalAD.testOneSample(zs)

    s
  }
}