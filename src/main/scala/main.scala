//import org.apache.spark.sql.SparkSession
// define main method (scala entry point)

import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
//import org.apache.spark.mllib.stat.Statistics
import test.smirnov.KolmogorovSmirnovTest

object HelloWorld {
  def main(args: Array[String]) {

    // initialise spark context
    val conf = new SparkConf().setAppName("HelloWorld").setMaster("local")
    val sc = new SparkContext(conf)

    val data: RDD[Double] = sc.parallelize(Seq(0.1, 0.15, 0.2, 0.3, 0.25))  // an RDD of sample data

    // run a KS test for the sample versus a standard normal distribution
    val testResult = KolmogorovSmirnovTest.testOneSample(data, "norm", 0, 1)

    // summary of the test including the p-value, test statistic, and null hypothesis if our p-value
    // indicates significance, we can reject the null hypothesis.

    println(testResult)
    println()
    // terminate spark context
    sc.stop()


  }
}