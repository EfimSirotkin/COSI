package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.ejml.simple.SimpleMatrix;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        //primaryStage.show();
        double[][] firstImage = {{-1.0, 1.0, -1.0, 1.0}};
        double[][] secondImage = {{ 1.0, -1.0, 1.0, 1.0}};
        double[][] thirdImage = {{-1.0, 1.0, -1.0, -1.0}};

        double[][] testImage = {{ 1.0, -1.0, 1.0, -1.0}};

        HopfieldNetwork hopfieldNetwork = new HopfieldNetwork();
        hopfieldNetwork.setFirstImage(firstImage);
        hopfieldNetwork.setSecondImage(secondImage);
        hopfieldNetwork.setThirdImage(thirdImage);

        hopfieldNetwork.calculateWeightsMatrix();

        System.out.println(hopfieldNetwork.getWeightMatrix());

        System.out.println(hopfieldNetwork.getResultImage(hopfieldNetwork.getMultipliedTestImageByWeights(new SimpleMatrix(testImage))));
    }


    public static void main(String[] args) {
        launch(args);
    }
}
