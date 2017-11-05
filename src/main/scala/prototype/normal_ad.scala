package sparkmodels.prototipe

import breeze.numerics.exp
import org.apache.commons.math3.distribution.NormalDistribution
import scala.collection.mutable.ArrayBuffer
import org.apache.commons.math3.stat.descriptive.moment
import scala.math.log

object NormalAD{
  def testOneSample(data: ArrayBuffer[Double]): (Double,Double) = {

    val ad = anderson_statistic(data)
    val n = data.length
    val ad2a = ad * (1 + 0.75/n + 2.25/math.pow(n,2))
    val pval = {
      if ((ad2a >= 0.00) && (ad2a < 0.200))
      1 - exp(-13.436 + 101.14 * ad2a - 223.73 * ad2a*ad2a)
      else if(ad2a < 0.340)
        exp(-8.318 + 42.796 * ad2a - 59.938 * ad2a*ad2a)
      else if(ad2a <0.600)
        1-exp(-8.318 + 42.796 * ad2a - 59.938 * ad2a*ad2a)
      else if(ad2a <= 13)
        exp(1.2937 - 5.709 * ad2a + 0.0186 * ad2a*ad2a)
      else
        0
    }
    (ad,pval)
  }

  def anderson_statistic(x: ArrayBuffer[Double]): Double ={
    val y = x.sortWith(_<_)
    val N = y.length

    //use  mapPartition
    //import org.apache.spark.rdd.DoubleRDDFunctions.sampleVariance() //    data.sampleVariance()
    //    moment.StandardDeviation()
    val xbar = new moment.Mean().evaluate(x.toArray)
    val s = new moment.StandardDeviation().evaluate(x.toArray)
    val w = y.map(x_ => (x_ - xbar)/s)
    val cdf=new NormalDistribution(0, 1)
    val z = w.map(x_ => cdf.cumulativeProbability(x_))

//    val S2: Double = z.zip(z.reverse).zipWithIndex.foldLeft(0:Double){
//      case (sum, ((x,y),i)) => (2*i-1) *(log(x)+log(1-y))+sum
//    }

    val S = z.zip(z.reverse).zipWithIndex
          .map{ case ((x1, y1), i) => (2 * (i+1)-1) * (log(x1) + log(1 - y1))}.sum/N
    -N-S
  }
}