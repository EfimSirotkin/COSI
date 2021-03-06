package sample;

import java.awt.*;

public class HopfieldNetwork {
    public static int IMAGE_SIZE = 100;
    public static int DIMENSION = 10;
    public static int WEIGHT_SIZE = IMAGE_SIZE * IMAGE_SIZE;
    public static int NUMBER_OF_ITERATIONS = 10;
    private int[][] weightMatrix;

    public HopfieldNetwork(int[][] weightMatrix) {
        this.weightMatrix = weightMatrix;
    }

    public HopfieldNetwork()
    {
        this.weightMatrix = new int[IMAGE_SIZE][IMAGE_SIZE];
    }

    public void addImage(int[] sourceImage) {

        for (int i = 0; i < IMAGE_SIZE; ++i) {
            for (int j = 0; j < IMAGE_SIZE; ++j) {
                if(i == j) {
                    continue;
                }
                weightMatrix[i][j] += sourceImage[i] * sourceImage[j];
            }
        }
    }

    public int[] recognizeImage(int[] testImage)
    {
        int[] outputImage = new int[IMAGE_SIZE];
        for(int i = 0;i < IMAGE_SIZE; ++i) {
            for (int j = 0; j < IMAGE_SIZE; ++j) {
                outputImage[j] += testImage[i] * this.weightMatrix[i][j];
            }
        }
        for(int i = 0; i < IMAGE_SIZE; ++i)
            outputImage[i] = signum(outputImage[i]);

        return outputImage;
    }

    public int signum(int sourceValue)
    {
        if(sourceValue > 0)
            return 1;
        else
            return -1;
    }

    public int[][] getWeightMatrix() {
        return weightMatrix;
    }
}
