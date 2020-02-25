package aiproj;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Controller implements Initializable {

    @FXML
    private Label label;

    @FXML
    private Button submitButton;
   
    @FXML
    private TextField gridsize;
   
    Stage primaryStage;
   
    public void start(Stage primaryStage) {
this.primaryStage = primaryStage;
}
   
@FXML
    void submit(ActionEvent event) throws ClassNotFoundException {
    System.out.println("Button Clicked");
    int size = Integer.parseInt(gridsize.getText());
   
    GridPane gridPane = new GridPane();    
         
         Text[][] cells = new Text[(size*2)+1][size];
         
         for (int i=0; i<size;i++) {
         for (int j=0;j<size;j++) {
         Random rand = new Random();
         int value = rand.nextInt(size-1)+1;
         cells[i][j] = new Text(value+"");
         gridPane.add(cells[i][j], i, j);
        // gridPane.setHgrow(cells[i][j], Priority.ALWAYS);
         //gridPane.setVgrow(cells[i][j], Priority.ALWAYS);
         gridPane.setHalignment(cells[i][j], HPos.CENTER);
         gridPane.setMargin(cells[i][j], new Insets(10,10,10,10));
         
         }
         }
         Button resetbtn = new Button("Reset");
         gridPane.add(resetbtn, 0, size+1);
         
         Button bfsbtn = new Button("BFS");
         gridPane.add(bfsbtn, 1, size+1);
         
         Button abtn = new Button("A*");
         gridPane.add(abtn, 2, size+1);
         
         Button climberbtn = new Button("Climber");
         gridPane.add(climberbtn, 3, size+1);
         
         resetbtn.setOnAction(new EventHandler<ActionEvent>() {

  @Override
  public void handle(ActionEvent arg0) {
  // TODO Auto-generated method stub
  for (int i = 0; i<size; i++) {
  for (int j=0; j<size; j++) {
  gridPane.getChildren().remove(cells[size+i+1][j]);
  cells[size+i+1][j] = new Text("NULL");
  gridPane.add(cells[size+i+1][j], i+size+1, j);
           gridPane.setHalignment(cells[i+size+1][j], HPos.CENTER);
           gridPane.setMargin(cells[i+size+1][j], new Insets(10,10,10,10));
  }
  }
 
  }
           
           });
         
         bfsbtn.setOnAction(new EventHandler<ActionEvent>() {

@Override
public void handle(ActionEvent arg0) {
// TODO Auto-generated method stub
for (int i = 0; i<size; i++) {
for (int j=0; j<size; j++) {
gridPane.getChildren().remove(cells[size+i+1][j]);
cells[size+i+1][j] = new Text("BFS");
gridPane.add(cells[size+i+1][j], i+size+1, j);
         gridPane.setHalignment(cells[i+size+1][j], HPos.CENTER);
         gridPane.setMargin(cells[i+size+1][j], new Insets(10,10,10,10));
}
}

}
         
         });
         abtn.setOnAction(new EventHandler<ActionEvent>() {

  @Override
  public void handle(ActionEvent arg0) {
  // TODO Auto-generated method stub
  for (int i = 0; i<size; i++) {
  for (int j=0; j<size; j++) {
  gridPane.getChildren().remove(cells[size+i+1][j]);
  cells[size+i+1][j] = new Text("A*");
  gridPane.add(cells[size+i+1][j], i+size+1, j);
           gridPane.setHalignment(cells[i+size+1][j], HPos.CENTER);
           gridPane.setMargin(cells[i+size+1][j], new Insets(10,10,10,10));
  }
  }
 
  }
           
           });
         
         climberbtn.setOnAction(new EventHandler<ActionEvent>() {

  @Override
  public void handle(ActionEvent arg0) {
  // TODO Auto-generated method stub
  for (int i = 0; i<size; i++) {
  for (int j=0; j<size; j++) {
  gridPane.getChildren().remove(cells[size+i+1][j]);
  cells[size+i+1][j] = new Text("C");
  gridPane.add(cells[size+i+1][j], i+size+1, j);
           gridPane.setHalignment(cells[i+size+1][j], HPos.CENTER);
           gridPane.setMargin(cells[i+size+1][j], new Insets(10,10,10,10));
  }
  }
 
  }
           
           });
       
         for (int i =0; i < size; i++) {
         Text space = new Text("--------------------->");
         gridPane.add(space, size, i);
         }
       
         for (int i = 0; i < size;i++) {
         for (int j =0; j< size; j++) {
         cells[size+i+1][j] = new Text("NULL");
         gridPane.add(cells[size+i+1][j], i+size+1, j);
         gridPane.setHalignment(cells[i+size+1][j], HPos.CENTER);
         gridPane.setMargin(cells[i+size+1][j], new Insets(10,10,10,10));
         }
         }
   
         gridPane.setMinSize(800, 400);
         gridPane.setMaxSize(1200, 600);
         gridPane.setAlignment(Pos.CENTER);
         gridPane.setGridLinesVisible(true);
       
         
         Scene scene = new Scene(gridPane);
         primaryStage.setScene(scene);
        primaryStage.setTitle("Create Grid");
        primaryStage.show();
    }

@Override
public void initialize(URL arg0, ResourceBundle arg1) {


}
}