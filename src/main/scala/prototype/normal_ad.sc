//import org.apache.commons.math3.distribution.NormalDistribution
//
//import scala.collection.mutable.ArrayBuffer
//import org.apache.commons.math3.stat.descriptive.moment.Mean
//
//
//val a =  ArrayBuffer[Double](5,1,7,89,1,55)
//
//a.sortWith(_<_)
//
//val e = new Mean()
//
//
//val r=new NormalDistribution(0, 1)
//
//val w = Array[Double](-1.45988645, -0.94463241,  0.08587567,  0.60112971,  0.60112971,
//  1.11638375)
//w.map(x=>r.cumulativeProbability(x))
//List((1,2),(2,3),(3,4),(4,5)).foldLeft(""){
//  (v,e) => s"($v,$e)"
//}
//
//List((1,2),(2,3),(3,4),(4,5)).foldLeft(""){
//  (v,e) => {
//    (s"($v ${e._1}, ${e._2})")
//  }
//}
//
//List((1,2),(3,4),(5,6),(7,8)).foldLeft(("",0)){
//  (a: Tuple2[String,Int],b:Tuple2[Int,Int]) =>
//    (a._1+s"${b._1}+${b._2}+",b._1+b._2+a._2)
//}

import breeze.numerics.log
import spire.random.Dist
import org.apache.commons.math3.distribution.NormalDistribution
import scala.collection.mutable.Buffer


def foo1(zs: Buffer[Double])={
  val S = zs.zip(zs.reverse)
    .map { case (x, y) =>log(x) * log(1 - y) }.sum
  S
}

val x = Dist.uniform(0.0, 1.0).sample[Buffer](10)
val y = x.sortWith(_<_)
val cdf=new NormalDistribution(0, 1)
val z = y.map(x_ => cdf.cumulativeProbability(x_))

foo1(z)