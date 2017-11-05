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

import spire.random.Dist
import scala.math.log
import scala.collection.mutable.Buffer

val z = Dist.uniform(0.0, 1.0).sample[Buffer](5).view
val I = Stream.from(0,2)


val S: Double = (I,z,z.reverseIterator.toStream).zipped.foldLeft(0:Double){
  (sum, el:(Int,Double,Double)) => sum+(el._1.toDouble-1).*(log(el._2)+log(1-el._3))}


val S1: Double = z.zip(z.reverse).zipWithIndex
  .map{ case ((x, y), i) => (2 * i-1) * (log(x) + log(1 - y))}.sum

val S2: Double = z.zip(z.reverse).zipWithIndex.foldLeft(0:Double){
  case (sum, ((x,y),i)) => (2*i-1) *(log(x)+log(1-y))+sum
  }
