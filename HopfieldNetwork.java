package sample;

import org.ejml.simple.SimpleMatrix;
import sun.java2d.pipe.SpanShapeRenderer;

import java.util.ArrayList;

public class HopfieldNetwork {

    public static int NUMBER_OF_ROWS = 1;
    public static int NUMBER_OF_COLUMNS = 4;
    public static int NUMBER_OF_IMAGES = 3;

    private SimpleMatrix firstImage;
    private SimpleMatrix secondImage;
    private SimpleMatrix thirdImage;
    private SimpleMatrix weightMatrix;

    public SimpleMatrix getFirstImage() {
        return firstImage;
    }

    public SimpleMatrix getSecondImage() {
        return secondImage;
    }

    public SimpleMatrix getWeightMatrix() {
        return weightMatrix;
    }

    public SimpleMatrix getThirdImage() {
        return thirdImage;
    }

    public HopfieldNetwork() {
        firstImage = new SimpleMatrix(NUMBER_OF_ROWS, NUMBER_OF_COLUMNS);
        secondImage = new SimpleMatrix(NUMBER_OF_ROWS, NUMBER_OF_COLUMNS);
        thirdImage = new SimpleMatrix(NUMBER_OF_ROWS, NUMBER_OF_COLUMNS);
        weightMatrix = new SimpleMatrix(NUMBER_OF_COLUMNS, NUMBER_OF_COLUMNS);
    }

    public void setFirstImage(double[][] sourceArray) {
        this.firstImage = new SimpleMatrix(sourceArray);
    }

    public void setSecondImage(double[][] sourceArray) {
        this.secondImage = new SimpleMatrix(sourceArray);
    }

    public void setThirdImage(double[][] sourceArray) {
        this.thirdImage = new SimpleMatrix(sourceArray);
    }

    public SimpleMatrix[] allocateWeightMatrices(int numberOfMatrices, int numberOfRows, int numberOfColumns)
    {
        SimpleMatrix[] tempMatricesArray = new SimpleMatrix[numberOfMatrices];
        for(int i = 0; i < tempMatricesArray.length; ++i)
            tempMatricesArray[i] = new SimpleMatrix(numberOfRows, numberOfColumns);

        return tempMatricesArray;
    }

    public void calculateWeightsMatrix()
    {
        SimpleMatrix[] weightMatricesArray = allocateWeightMatrices(NUMBER_OF_IMAGES, NUMBER_OF_ROWS, NUMBER_OF_COLUMNS);
        weightMatricesArray[0] = getFirstImage().transpose().mult(getFirstImage());
        weightMatricesArray[1] = getSecondImage().transpose().mult(getSecondImage());
        weightMatricesArray[2] = getThirdImage().transpose().mult(getThirdImage());

        for(int i = 0; i < weightMatricesArray.length; ++i)
            weightMatrix = weightMatrix.plus(weightMatricesArray[i]);

        zeroWeightMatrixDiagonal();
    }

    public SimpleMatrix getMultipliedTestImageByWeights(SimpleMatrix testImage)
    {
        return weightMatrix.mult(testImage.transpose());
    }

    public SimpleMatrix getResultImage(SimpleMatrix multipliedImage)
    {
        SimpleMatrix testMatrix = new SimpleMatrix(multipliedImage);
        for(int i = 0; i < multipliedImage.numRows(); ++i)
            for(int j = 0; j < multipliedImage.numCols(); ++j)
            {
                if(testMatrix.get(i,j)>= 0)
                    testMatrix.set(i,j, 1);
                else if(testMatrix.get(i,j) < 0)
                    testMatrix.set(i,j,-1);
            }
         return testMatrix.transpose();
    }

    public void zeroWeightMatrixDiagonal()
    {
        for(int i = 0; i < NUMBER_OF_COLUMNS; ++i)
            weightMatrix.set(i,i,0.0);
    }

}
