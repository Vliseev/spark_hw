import sparkmodels.prototipe.NormalAD
import spire.random.Dist

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

object Main {
  def main(args: Array[String]) {

    val zs = ArrayBuffer[Double](-0.64080389472312782, -0.1988724552328629, -2.372718397485543, 0.1392694315293459, -1.0231443304975303, -0.23130222162476419, 0.61636365850622732, -1.6034658944393076, -0.31467817543208543, 0.41106247961469572)
    val s = NormalAD.testOneSample(zs)

    s
  }
}