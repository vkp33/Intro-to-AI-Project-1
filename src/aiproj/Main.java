package aiproj;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Main extends Application {
	   @Override
	   public void start(Stage primaryStage) throws Exception {  
	 
	  FXMLLoader loader = new FXMLLoader();
	    loader.setLocation(getClass().getResource("hellofx.fxml"));
	   
	    AnchorPane root = (AnchorPane)loader.load();
	   
	    Controller loginview = loader.getController();
	       loginview.start(primaryStage);

	    Scene scene = new Scene(root);
	    primaryStage.setScene(scene);
	    primaryStage.setTitle("Create Grid");
	    primaryStage.show();
	       

	   
	   }


    public static void main(String[] args) {
    	Node current = null;
    	PathingPuzzle puzzle = new PathingPuzzle(5);
    	current = puzzle.tree.root;
    	puzzle.decisionTree(puzzle.tree.root);
    	System.out.println();
    	puzzle.BFSall();
    	puzzle.HillClimbing(10);
    	launch(args);
    }
}