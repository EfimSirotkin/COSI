package sample;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

import static sample.HopfieldNetwork.IMAGE_SIZE;
import static sample.HopfieldNetwork.NUMBER_OF_ITERATIONS;

class Main {


    public static int[] firstImage = {-1, -1, -1, -1, 1, 1, 1, 1, 1, 1};
    public static int[] secondImage = {-1, 1, 1, 1, 1, 1, 1, 1, 1, -1};
    public static int[] thirdImage = {1, 1, 1, 1, -1, -1, 1, 1, 1, 1};

    public static int[] testImage = {-1, 1, 1, 1, -1, 1, 1, -1, -1, -1};

    public static int MAX_NOISE = 90;

    public static boolean isEqual(int[] firstImage, int[] secondImage) {
        for (int i = 0; i < firstImage.length; ++i) {
            if (firstImage[i] != secondImage[i])
                return false;
        }
        return true;
    }

    public static void printImage(int[] sourceImage) {
        for (int i = 0; i < IMAGE_SIZE; ++i) {
            if (sourceImage[i] > 0)
                System.out.print("x ");
            else
                System.out.print("o ");
        }

        System.out.println();
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

        System.out.println("Исходные образы: ");
        printImage(firstImage);
        printImage(secondImage);
        printImage(thirdImage);
        System.out.println("----------------");

        System.out.println("Тестовый образ: ");
        printImage(firstImage);

        for (int i = 0; i < MAX_NOISE; i += 10) {
            int[] noisedImage = generateNoisedImage(firstImage, noise);
            System.out.println("Шум " + noise * 100 + "%");
            printImage(noisedImage);
            outputImage = noisedImage;
            for (int j = 0; j < NUMBER_OF_ITERATIONS; ++j) {
                outputImage = hopfieldNetwork.recognizeImage(outputImage);
                if (isEqual(outputImage, firstImage) || isEqual(outputImage, secondImage) || isEqual(outputImage, thirdImage))
                    break;
            }
            System.out.println("Распознанный образ: ");
            printImage(outputImage);
            noise += 0.1;
            noise = roundNumber(noise, 2);
            System.out.println("\n");
        }
    }
}

//    public static int[] firstImage = {
//            -1, -1, -1,-1, -1, -1, -1, -1, -1, -1,
//            1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
//            1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
//            1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
//            1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
//            1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
//            1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
//            1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
//            1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
//            1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
//    };
//    public static int[] secondImage = {
//            1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
//            1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
//            1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
//            1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
//            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
//            1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
//            1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
//            1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
//            1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
//            1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
//    };
//    public static int[] thirdImage = {
//            1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
//            1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
//            1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
//            1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
//            1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
//            1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
//            1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
//            1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
//            1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
//            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
//    };
//
