import java.awt.event.ActionListener;
import java.lang.reflect.Member;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application implements EventHandler<ActionEvent>{
	
	// scene = container dari komponen
	Scene scene;
	
	Label addressLabel,
	usernameLabel, passwordLabel, genderLabel, ageLabel;
	
	TextField firstNameTF, lastNameTF, usernameTF;
	
	PasswordField passwordField;
	
	TextArea addressField;
	
	BorderPane borderPane;
	GridPane gridPane;
	FlowPane flowPane;
	ScrollPane scrollPane;
	
	TableView<User> tableUser;
	
	RadioButton maleRadio, femaleRadio;
	
	Button addButton, deleteButton;
	
	ToggleGroup genderGroup;
	
	Spinner<Integer> ageSpinner;
	

	public static void main(String[] args) {
		launch(args);

	}
	
	public void init() {
		borderPane = new BorderPane();
		gridPane = new GridPane();
		flowPane = new FlowPane();
		
		addressLabel = new Label("Address");
		usernameLabel = new Label("Username");
		passwordLabel = new Label("Password");
		genderLabel = new Label("Gender");
		ageLabel = new Label("Age");
		
		firstNameTF = new TextField("First name");
		lastNameTF = new TextField();
		usernameTF = new TextField();
		
		addressField = new TextArea();
		
		passwordField = new PasswordField();
		
		addButton = new Button("ADD");
		deleteButton = new Button("DELETE");
		
		maleRadio = new RadioButton("Male");
		femaleRadio = new RadioButton("Female");
		genderGroup = new ToggleGroup();
		maleRadio.setToggleGroup(genderGroup);
		femaleRadio.setToggleGroup(genderGroup);
		
		scrollPane = new ScrollPane();
		tableUser = new TableView<User>();
		
		ageSpinner = new Spinner<Integer>(1, 100, 1); // start, end, default
	}
	
	public void setLayout() {
		scrollPane.setContent(tableUser);
		scrollPane.setFitToWidth(true);
		
		flowPane.getChildren().add(maleRadio);
		flowPane.getChildren().add(femaleRadio);
		flowPane.setHgap(10);
		
		
		gridPane.add(usernameLabel, 0, 0);
		gridPane.add(usernameTF, 1, 0);
		usernameTF.setMaxWidth(200);
		
		gridPane.add(genderLabel, 0, 1);
		gridPane.add(flowPane, 1, 1);	
		flowPane.setMaxWidth(200);
		
		gridPane.add(passwordLabel, 0, 2);
		gridPane.add(passwordField, 1, 2);	
		passwordField.setMaxWidth(200);
		
		gridPane.add(addressLabel, 0, 3);
		gridPane.add(addressField, 1, 3);
		addressField.setMaxWidth(200);
		
		gridPane.add(ageLabel, 0, 4);
		gridPane.add(ageSpinner, 1, 4);
		ageSpinner.setMaxWidth(200);
		
		gridPane.add(addButton, 0, 5);
		gridPane.add(deleteButton, 1, 5);
		
		borderPane.setTop(gridPane);
		borderPane.setCenter(scrollPane);
	}
	
	public void setAlignment() {
		BorderPane.setMargin(gridPane, new Insets(10));
		gridPane.setHgap(10);
		gridPane.setVgap(10);
	}
	
	public void setTable() {
		// menentuka column table yang ingin di show
		TableColumn<User, String> usernameColumn = 
				new TableColumn<User, String>("User name");
		// menentukan asal content
		usernameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
		
		TableColumn<User, String> addressColumn = 
				new TableColumn<User, String>("Address");
		addressColumn.setCellValueFactory(new PropertyValueFactory<User, String>("address"));
		
		TableColumn<User, String> genderColumn = 
				new TableColumn<User, String>("Gender");
		genderColumn.setCellValueFactory(new PropertyValueFactory<User, String>("gender"));
		
		usernameColumn.setPrefWidth(100);
		genderColumn.setPrefWidth(100);
		addressColumn
		.setMinWidth(240);
		
		tableUser.getColumns().addAll(usernameColumn, addressColumn, genderColumn);
	}

	@Override
	// stage = top level container
	public void start(Stage stage) throws Exception {
		init();
		setLayout();
		setAlignment();
		setTable();
		addEventListener();
		scene = new Scene(borderPane, 500, 500);
		stage.setScene(scene);
		stage.setTitle("Java per 5");
		stage.show();
		
	}
	
	public void addEventListener() {
		addButton.setOnAction(this);
		deleteButton.setOnAction(this);
	}

	@Override
	public void handle(ActionEvent arg0) {
		if(arg0.getSource() == addButton) {
			User user = new User(usernameTF.getText(), passwordField.getText(), addressField.getText(),
					genderGroup.getSelectedToggle() == maleRadio ? "Male" : "Female");
			
			tableUser.getItems().add(user);
		}else if (arg0.getSource() ==  deleteButton) {
			tableUser.getItems().removeAll(tableUser.getSelectionModel().getSelectedItem());
		}
		
	}

}
