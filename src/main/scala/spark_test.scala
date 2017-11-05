import org.apache.spark.mllib.stat.Statistics
import org.apache.spark.sql.SparkSession

class spark_test {
  val logFile = "out20G" // Should be some file on your system
  val spark = SparkSession.builder.appName("Simple Application").getOrCreate()
  val sc = spark.sparkContext

  val logData = sc.textFile(logFile).flatMap(_.split(" ")).map(_.toDouble)
  //    val logData = sc.objectFile[Double]("serrialize").cache()
  //    logData.saveAsObjectFile("serrialize")
  val res = Statistics.kolmogorovSmirnovTest(logData, "norm", 0, 1)

  println(res)
  sc.stop()
  spark.stop()
}
