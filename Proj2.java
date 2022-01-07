import java.util.Scanner;
import java.util.Random;

/**
 *  Purpose of program: Generate and output random sample data.
 *  Determine which sample is the closest to the user query
 *  and output sample and query in text and in a graph
 *  Author Name: Meghana Karthic 
 *  JHED ID: 785802
 *  Date: September 30, 2021
 */

public class Proj2 {

   static Random gen = new Random(10);
   
    /**
    * Generates a random year between 1972 and 2020, with all
    * years being equally likely.
    *
    * @return the generated number, which represents a year
    */
   public static int generateYear() {
      
      int numOfValues = 2020 - 1972 + 1;
      int year = gen.nextInt(numOfValues) + 1972;
      
      return year;
   
   }

   /**
    * Generate an integer in a specified range, with all
    * values being equally likely.
    *
    * @param low the low value of the range, inclusive
    * @param high the high value of the range, inclusive
    * @return the value generated
    */
   public static int generateInt(int low, int high) {
   
      int value = gen.nextInt(high - low + 1) + low;
      return value;
   
   }

   /**
    * Generate a random mileage: split the possibilities into 5 ranges:
    * [0, 17000), [17000, 30000), [30000, 40000), [40000, 70000),
    * [70000, 999999], with each range being equally likely. Each mileage
    * within a range is equally likely, but the ranges don't contain the same
    * number of miles in them, so be careful.
    *
    * @return the generated number
    */
   public static int generateMileage() {
   
      int value = gen.nextInt(100);
      int low;
      int high;
      
      if (value >= 80) {
         low = 0;
         high = 16_999;
      }
      
      else if (value >= 60) {
         low = 17_000;
         high = 29_999;
      }
         
      else if (value >= 40) { 
         low = 30_000;
         high = 39_999;
      }
         
      else if (value >= 20) {
         low = 40_000;
         high = 69_999;
      }
         
      else {
         low = 70_000;
         high = 999_999;
      }
            
      int mileage = generateInt(low, high);
      
      return mileage;
   
   }

   /**
    * Generate the brand of a car based on given brand probabilities.
    *
    * @return the generated brand as a string
    */
   public static String generateBrand() {
   
      String brand;
      double val = gen.nextDouble();
   
      if ((val >= 0.0) && (val <= 0.13)) {
         brand = "Chevrolet";
      }
      
      else if ((val > 0.13) && (val <= 0.32)) {
         brand = "Dodge";
      }
      
      else if ((val > 0.32) && (val <= 0.863)) {
         brand = "Ford";
      }
      
      else {
         brand = "Nissan";
      }
      
      return brand;
   
   }

   /**
    * Generate a random car price based on the mileage and project-provided
    * statistics.
    *
    * @param mileage car's mileage in the range [0, 999999]
    * @return a price value in the range [1, 74000]
    */
   public static int generatePrice(int mileage) {
   
      int price; 
      
      if ((mileage >= 0) && (mileage < 17_000)) {
         price = gen.nextInt(74_000) + 1;
      }
      
      else if ((mileage >= 17_000) && (mileage < 30_000)) {
         price = gen.nextInt(65_000) + 1;
      }
      
      else if ((mileage >= 30_000) && (mileage < 40_000)) {
         price = gen.nextInt(60_000) + 1;
      }
      
      else if ((mileage >= 40_000) && (mileage < 70_000)) {
         price = gen.nextInt(55_000) + 1;
      }
      
      else {
         price = gen.nextInt(50_000) + 1;
      }
       
      return price;  
   }

    /**
    * Computes the distance between the query.
    * and each random data sample 
    *
    * @param yearA the year given by the user
    * @param mileageA the mileage given by the user
    * @param brandA the brand given by the user
    * @param yearB the year of the random data sample
    * @param mileageB the mileage of the random data sample
    * @param brandB the brand of the random data sample
    * @return the distance between the query and given sample
    */
   public static double squaredDistance(int yearA, int mileageA, String brandA,
         int yearB, int mileageB, String brandB) {
   
      int brandDiff = 1;
      
      if (brandA == brandB) {
         brandDiff = 0;
      }
      
      double distanceSquared = Math.pow(((yearA - yearB) / 3.3), 2) 
         + Math.pow(((mileageA - mileageB) / 50561), 2) + brandDiff;
      
      return distanceSquared;
   
   }
   
   /**
    * Create a string with all the data for a subject using the
    * format: [xxxx] year, mileage, brand, price .
    * @param number the sample number
    * @param year the car's model year
    * @param mileage the car's mileage
    * @param brand the car's brand
    * @param price the car's selling price
    * @return the composed string
    */
    
   public static String sampleText(int number, int year, int mileage,
                                   String brand, int price) {
   
      String sampleNum;
      String dataOutput = "";
      
      if (number < 10) {
         sampleNum = "[000" + number + "]";
      }
        
      else if (number < 100) {
         sampleNum = "[00" + number + "]";
      }
      
      else if (number < 1000) {
         sampleNum = "[0" + number + "]";
      }
      
      else {
         sampleNum = "[" + number + "]";
      }
         
      dataOutput = dataOutput + sampleNum + " " + year + ", " 
         + mileage + ", " + brand + ", $" + price + "\n";
         
      return dataOutput;
   
   }

    /**
    * Plots random sample on graph as a dot with a certain color and size.
    * 
    * @param year the sample year
    * @param price the sample price
    * @param brand the sample brand
    * @param mileage the sample mileage
    */
   
   public static void plotSample(int year, int price, 
                                 String brand, int mileage) {
      
      // determines color of point
      if ("Chevrolet".equals(brand)) {
         StdDraw.setPenColor(StdDraw.RED);
      }
      
      else if ("Dodge".equals(brand)) {
         StdDraw.setPenColor(StdDraw.YELLOW);
      }
      
      else if ("Ford".equals(brand)) {
         StdDraw.setPenColor(StdDraw.DARK_GRAY);
      }
      
      else {
         StdDraw.setPenColor(StdDraw.BLUE);
      }
      
      // determines size of point
      if ((mileage >= 0) && (mileage < 17_000)) {
         StdDraw.setPenRadius(0.01); 
      }
      
      else if ((mileage >= 17_000) && (mileage < 30_000)) {
         StdDraw.setPenRadius(0.02);
      }
      
      else if ((mileage >= 30_000) && (mileage < 40_000)) {
         StdDraw.setPenRadius(0.03);
      }
      
      else if ((mileage >= 40_000) && (mileage < 70_000)) {
         StdDraw.setPenRadius(0.04); 
      }
      
      else { 
         StdDraw.setPenRadius(0.05);
      }
      
      // plots point on graph
      StdDraw.point(year, price);
   
   }

   /**
    * Main method where execution begins.
    * @param args not used here
    */
   public static void main(String[] args) {
   
      // create new scanner for console input
      Scanner scnr = new Scanner(System.in);
   
      // input car year
      System.out.print("Enter query car's model year: ");
      int inputYear = scnr.nextInt();
   
      // input mileage
      System.out.print("Enter query car's mileage [1, 999999]: ");
      int inputMileage = scnr.nextInt();
   
      // input brand
      System.out.print("Enter query car's brand [Chevrolet, Dodge, Ford, " +
         "Nissan]: ");
      String inputBrand = scnr.next();
   
      // input's associated price
      int inputPrice = 0;
      
      // gets input for the number of samples to generate
      System.out.print("Enter number of samples to generate: ");
      int howMany = scnr.nextInt();
      
      // generates first random sample and prints result
      int currSample = 1;
      int yearOne = generateYear();
      int mileageOne = generateMileage();
      String brandOne = generateBrand();
      int priceOne = generatePrice(mileageOne);
      String outputOne = sampleText(currSample, yearOne, mileageOne,
                                    brandOne, priceOne);
      System.out.print(outputOne);
      
      /* finds difference between first random sample and query
      and assigns it as the minimum difference */
      double minDifference = squaredDistance(inputYear, inputMileage, 
                             inputBrand, yearOne, mileageOne, brandOne);
      
      /* sets background color, x scale, and y scale of graph
      and plots first random sample */
      StdDraw.clear(StdDraw.CYAN);
      StdDraw.setXscale(1960, 2030);
      StdDraw.setYscale(-5000, 80_000); 
      plotSample(yearOne, priceOne, brandOne, mileageOne);
      
      /* generates and plots rest of the random samples
      and finds the sample with minimum distance to the query */
      currSample = 2;
      String closestMatch = "";
      
      while (currSample <= howMany) {
      
         // prints random sample data
         int year = generateYear();
         int mileage = generateMileage();
         String brand = generateBrand();
         int price = generatePrice(mileage);
         String output = sampleText(currSample, year, mileage, brand, price);
         System.out.print(output);
         
         /* finds which random sample has the lowest distance to the query
         and assigns price of closest match to the query's predicted price */
         double sampleDifference = squaredDistance(inputYear, inputMileage, 
                                   inputBrand, year, mileage, brand); 
         
         if (sampleDifference < minDifference) {
            minDifference = sampleDifference;
            closestMatch = "Closest match: " 
               + sampleText(currSample, year, mileage, brand, price);
            inputPrice = price;
         }
            
         // plots the random samples on the graph
         plotSample(year, price, brand, mileage); 
           
         currSample = currSample + 1;    
      }
      
      // outputs the query and its closest match
      System.out.println("");
      System.out.println("Input car: [0000] " + inputYear + ", " 
         + inputMileage + ", " + inputBrand + ", " + "$" + inputPrice);
      System.out.println(closestMatch);
      
      // plots the query and places a red square around it
      plotSample(inputYear, inputPrice, inputBrand, inputMileage);
      StdDraw.setPenRadius(0.003);
      StdDraw.setPenColor(StdDraw.RED);
      StdDraw.rectangle(inputYear, inputPrice, 3, 3642);  
   
   }

}

/*

Reflection: 

The difference is each of the three characteristics (year, mileage, and brand)
are scaled differently based on their standard deviation. Mileage has the
greatest standard deviation, and because standard deviation is in 
the denominator, the difference between the sample mileage and query mileage 
will have less impact on the result for distance squared.

The matches my solution found seem like the “closest” sample to the query. 
It seems like when the sample size increases, a random sample is closer 
to the query than with a lower sample size.

It will not be effective to use year, mileage, and price to predict brand. 
The price of the car is based on the mileage and can be predicted from mileage. 
The brand of the car is not based on any characteristic. There could be multiple
cars in the random sample data with a similar year, mileage, and price 
but represent different brands.

*/