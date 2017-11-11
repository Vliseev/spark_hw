
import scala.collection.mutable
import java.io._
import spire.random.Dist

object MyGenerate extends App {


  var size: BigInt = 80000000
  var out_f = "out"

  args.sliding(2, 2).toList.collect {
    case Array("--out", out: String) => out_f = out
    case Array("--size", size_x: String) => size = BigInt(size_x)
  }

  val file = new File(out_f)
  val bw = new BufferedWriter(new FileWriter(file, true))

  val z = Dist.uniform(0.0, 1.0).sample[mutable.Buffer](size.toInt).view
  z.foreach(x => bw.write(x.toString + "\n"))

  bw.close()
}
