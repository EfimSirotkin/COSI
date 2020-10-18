package sample;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

import static sample.HopfieldNetwork.*;

class Main {

    public static int[] firstImage = {
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
    };
    public static int[] secondImage = {
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, 1, 1, 1, 1, 1, 1,-1, -1,
            -1, -1, 1, -1, -1, -1, -1, 1, -1, -1,
            -1, -1, 1, -1, -1, -1, -1, 1, -1, -1,
            -1, -1, 1, -1, -1, -1, -1, 1, -1, -1,
            -1, -1, 1, -1, -1, -1, -1, 1, -1, -1,
            -1, -1, 1, 1, 1, 1, 1, 1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
    };

    public static int[] thirdImage = {
            -1, 1, -1, -1, 1, 1, -1, -1, 1, -1,
            -1, 1, -1, -1, 1, 1, -1, -1, 1, -1,
            -1, 1, -1, -1, 1, 1, -1, -1, 1, -1,
            -1, 1, -1, -1, 1, 1, -1, -1, 1, -1,
            -1, 1, -1, -1, 1, 1, -1, -1, 1, -1,
            -1, 1, -1, -1, 1, 1, -1, -1, 1, -1,
            -1, 1, -1, -1, 1, 1, -1, -1, 1, -1,
            -1, 1, -1, -1, 1, 1, -1, -1, 1, -1,
            -1, 1, -1, -1, 1, 1, -1, -1, 1, -1,
            -1, 1, 1, 1, 1, 1, 1, 1, 1, -1,
    };

//    public static int[] firstImage = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
//    public static int[] secondImage = {1, 1, 1, 1,1, 1, 1, 1,1, 1, 1, 1,1, 1, 1, 1,1, 1, 1, 1};
//    public static int[] thirdImage = {1, 1, -1, -1, -1, -1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };

    public static int[] testImage = {-1, -1, -1, -1, -1, -1, -1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};

    public static int MAX_NOISE = 90;
    public static int IMAGES_COUNT = 3;

    public static boolean isEqual(int[] firstImage, int[] secondImage) {
        for (int i = 0; i < firstImage.length; ++i) {
            if (firstImage[i] != secondImage[i])
                return false;
        }
        return true;
    }

    public static void printImage(int[] sourceImage) {
        for (int i = 0; i < IMAGE_SIZE; ++i) {
            System.out.print(sourceImage[i]);
        }

        System.out.println();
    }

    public static void printMatrix(int[][] sourceMatrix)
    {
        for(int i = 0; i < IMAGE_SIZE; ++i)
        {
            for(int j = 0; j < IMAGE_SIZE; ++j)
                System.out.print("  " + sourceMatrix[i][j] + "  ");
            System.out.println();
        }
        System.out.println("----------------------\n");
    }

    public static void printImageMatrix(int[] sourceImage)
    {
        for(int i = 0; i < DIMENSION; ++i)
        {
            for(int j = 0; j < DIMENSION; ++j)
                if(sourceImage[i * DIMENSION + j] > 0)
                    System.out.print(" " + "x");
                else
                    System.out.print(" " + "0");
            System.out.println();
        }
        System.out.println("------------------");
    }

    public static int[] generateNoisedImage(int[] primaryImage, double noiseDegree) {
        int[] tempImage = primaryImage;
        for (int i = 0; i < IMAGE_SIZE; ++i) {
            if (Math.random() < noiseDegree)
                tempImage[i] *= -1;
        }
        return tempImage;

    }

    public static double roundNumber(double primaryValue, int scale) {
        BigDecimal bd = new BigDecimal(primaryValue);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static void main(String[] args) {
        HopfieldNetwork hopfieldNetwork = new HopfieldNetwork();
        int[] outputImage = new int[IMAGE_SIZE];
        double noise = 0.0;


        hopfieldNetwork.addImage(firstImage);
        hopfieldNetwork.addImage(secondImage);
        hopfieldNetwork.addImage(thirdImage);

        //printMatrix(hopfieldNetwork.getWeightMatrix());


        System.out.println("Исходные образы: ");
        printImageMatrix(firstImage);
        printImageMatrix(secondImage);
        printImageMatrix(thirdImage);
        //printImage(thirdImage);
        System.out.println("----------------");


        int[] testImage = new int[IMAGE_SIZE];

        for (int k = 0; k < IMAGES_COUNT; ++k) {

            switch (k)
            {
                case 0:
                    testImage = firstImage;
                    break;
                case 1:
                    testImage = secondImage;
                    break;
                case 2:
                    testImage = thirdImage;
                    break;
            }

            System.out.println("Тестовый образ: ");
            printImageMatrix(testImage);
            noise = 0;
            for (int i = 0; i <= MAX_NOISE; i += 10) {
                int[] noisedImage = generateNoisedImage(testImage, noise);
                System.out.println("Шум " + noise * 100 + "%");
                printImageMatrix(noisedImage);
                outputImage = noisedImage;
                for (int j = 0; j < NUMBER_OF_ITERATIONS; ++j) {
                    int[] tempImage = outputImage;
                    outputImage = hopfieldNetwork.recognizeImage(tempImage);
                    if (isEqual(outputImage, firstImage) || isEqual(outputImage, secondImage) || isEqual(outputImage, thirdImage))
                        break;
                    if (j == NUMBER_OF_ITERATIONS - 1)
                        System.out.println("Образ не распознан");
                }
                System.out.println("Распознанный образ: ");
                printImageMatrix(outputImage);
                noise += 0.1;
                noise = roundNumber(noise, 2);
                System.out.println("\n");
            }
        }
    }
}



