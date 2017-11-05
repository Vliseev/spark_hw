
import scala.collection.mutable
import java.io._
import spire.random.Dist

object MyGenerate extends App {

  val MAX_SIZE = 0x7ffffff

  var size: BigInt = 0x7ffffff
  var out_f = "out"

  args.sliding(2, 2).toList.collect {
    case Array("--out", out: String) => out_f = out
    case Array("--size", size_x: String) => size = BigInt(size_x)
  }

  val file = new File(out_f)
  val bw = new BufferedWriter(new FileWriter(file, true))


  val portion = {
    if (size > MAX_SIZE)
      (MAX_SIZE).toInt
    else
      size
  }
  val num_iter: Int = {
    if (size > MAX_SIZE)
      (size / MAX_SIZE).toInt
    else
      1
  }

  for (i <- 1 to num_iter) {
    val z = Dist.uniform(0.0, 1.0).sample[mutable.Buffer](200000).view
    z.foreach(x => bw.write(x.toString + " "))
    bw.write("\n")
  }

  bw.close()
}
