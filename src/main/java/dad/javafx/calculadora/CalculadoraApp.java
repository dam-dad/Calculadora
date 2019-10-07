package dad.javafx.calculadora;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class CalculadoraApp extends Application {
	
	// modelo
	
	private DoubleProperty operando1 = new SimpleDoubleProperty();
	private DoubleProperty operando2 = new SimpleDoubleProperty();
	private DoubleProperty resultado = new SimpleDoubleProperty();
	private StringProperty operador = new SimpleStringProperty();
	
	// vista
	
	private TextField operando1Text;
	private TextField operando2Text;
	private TextField resultadoText;
	private ComboBox<String> operadorCombo;

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		operando1Text = new TextField();
		operando1Text.setPrefColumnCount(4);

		operando2Text = new TextField();
		operando2Text.setPrefColumnCount(4);

		resultadoText = new TextField();
		resultadoText.setPrefColumnCount(4);
		resultadoText.setDisable(false);
		
		operadorCombo = new ComboBox<String>();
		operadorCombo.getItems().addAll("+", "-", "*", "/");
		
		HBox root = new HBox(5, operando1Text, operadorCombo, operando2Text, new Label("="), resultadoText);
		root.setAlignment(Pos.BASELINE_CENTER);
		
		Scene scene = new Scene(root, 320, 200);
		
		primaryStage.setTitle("Calculadora Compleja");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		// bindeos
		
		Bindings.bindBidirectional(operando1Text.textProperty(), operando1, new NumberStringConverter());
		Bindings.bindBidirectional(operando2Text.textProperty(), operando2, new NumberStringConverter());
		Bindings.bindBidirectional(resultadoText.textProperty(), resultado, new NumberStringConverter());
		operador.bind(operadorCombo.getSelectionModel().selectedItemProperty());
		
		// listeners
		
		operador.addListener((o, ov, nv) -> onOperadorChanged(nv));

		operadorCombo.getSelectionModel().selectFirst();
		
	}

	private void onOperadorChanged(String nv) {

		switch(nv) {
		case "+":
			resultado.bind(operando1.add(operando2));
			break;
		case "-":
			resultado.bind(operando1.subtract(operando2));			
			break;
		case "*": 
			resultado.bind(operando1.multiply(operando2));
			break;
		case "/":
			resultado.bind(operando1.divide(operando2));
			break;
		}
		
	}

	public static void main(String[] args) {
		launch(args);
	}

}
