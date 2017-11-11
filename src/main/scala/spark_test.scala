import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.mllib.stat.Statistics
import org.apache.spark.sql.SparkSession
import sparkmodels.prototipe.SpNormalAD

object spark_test extends App{
  val logFile = "out20G" // Should be some file on your system
  val spark = SparkSession.builder.appName("Simple Application").getOrCreate()

//  val conf = new SparkConf()
//    .set("spark.default.parallelism", "4")
//    .setMaster("local[4]")
//    .set("executor-memory","1")
  val sc = spark.sparkContext
//  val sc = new SparkContext(conf)


  val logData = sc.textFile(logFile).flatMap(_.split(" ")).map(_.toDouble)
  //    val logData = sc.objectFile[Double]("serrialize").cache()
  //    logData.saveAsObjectFile("serrialize")
  val res = SpNormalAD.testOneSample(logData)

  println(res)
  sc.stop()
  spark.stop()
}
