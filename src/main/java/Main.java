
import org.apache.commons.math3.distribution.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {

    static Scanner scan = new Scanner(System.in);
    static String flag="0";
    static int sampleSize;
    static List<? extends Number> sample;

    public static void main(String[]args)
    {
        while (flag.equals("0")) {
            System.out.println("Enter sample size\nn=");
            try {
                sampleSize = scan.nextInt();
                if (sampleSize <= 0) {
                    throw new IllegalArgumentException();
                } else {
                    flag="01";
                }
            } catch (Exception e) {
                System.out.println("! Incorrect Data !\n");
                flag = "0";
            }
        }

        while (flag.equals("01")) {
            System.out.println("Enter distribution number: \n1.Normal\n2.Exponential\n3.Poisson");
            flag = scan.next();
            switch (flag) {
                case "1":
                    NormalDistribution normalDistribution = new NormalDistribution(1, 1);
                    sample=getSample(normalDistribution,sampleSize);
                    System.out.println(sample);
                    System.out.println("Sample mean: "+getSampleMean(sample));
                    break;
                case "2":
                    ExponentialDistribution exponentialDistribution = new ExponentialDistribution(1);
                    sample=getSample(exponentialDistribution,sampleSize);
                    System.out.println(sample);
                    System.out.println("Sample mean: "+getSampleMean(sample));
                    break;
                case "3":
                    PoissonDistribution poissonDistribution = new PoissonDistribution(1);
                    sample=getSample(poissonDistribution,sampleSize);
                    System.out.println(sample);
                    System.out.println("Sample mean: "+getSampleMean(sample));
                    break;
                default:
                    System.out.println("! Incorrect Data. !\n");
                    flag = "01";
                    break;
            }
        }
    }

    static List<Double> getSample(RealDistribution realDistribution , int sampleSize){
     List<Double> sample = new ArrayList<>();
        for (int i=0; i<sampleSize; i++) {
            sample.add(round(realDistribution.sample(sampleSize)[i],4));
        }
     return sample;
 }

    static List<Integer> getSample(IntegerDistribution integerDistribution , int sampleSize){
        List<Integer> sample = new ArrayList<>();
        for (int i=0; i<sampleSize; i++) {
            sample.add(integerDistribution.sample(sampleSize)[i]);
        }
        return sample;
    }

    static double getSampleMean(List<? extends Number> sample){
        Class clazz=sample.get(0).getClass();
        double sampleMean=0;
        for (int i=0; i<sampleSize; i++){
            if(clazz==Double.class)
                sampleMean = sampleMean + (Double) Main.sample.get(i);
            else if (clazz==Integer.class) {
                sampleMean = sampleMean + (Integer) Main.sample.get(i);
            }
        }
        return round(sampleMean/sampleSize,4);
    }

    static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

}
