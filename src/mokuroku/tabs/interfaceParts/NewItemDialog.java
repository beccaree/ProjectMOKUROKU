package mokuroku.tabs.interfaceParts;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import mokuroku.tabs.InventoryTab;

public class NewItemDialog extends Dialog<MokuStockItem> {
	
	protected ButtonType createButtonType = new ButtonType("Save", ButtonData.OK_DONE);
	protected Node btnCreate;
	protected TextField itemName = new TextField();
	protected TextField description = new TextField();
	protected Spinner<Double> priceSpinner = new Spinner<>();
	protected Spinner<Integer> stockSpinner = new Spinner<>();
	protected GridPane dialogContent = new GridPane();
	
	protected TextField imgUrl = new TextField();
	protected Button browse = new Button("Browse");
	protected Rectangle img = new Rectangle(135, 135, Color.LIGHTBLUE);
	
	public NewItemDialog(InventoryTab parent) {
		setTitle("Create New Item");
		
		getDialogPane().getButtonTypes().addAll(createButtonType, ButtonType.CANCEL);
		
		dialogContent.setHgap(10);
		dialogContent.setVgap(10);
		dialogContent.setPadding(new Insets(20, 100, 10, 10));
		
		itemName.setPromptText("Item Name");
		description.setPromptText("Description");
		imgUrl.setPromptText("Add a picture");
		// set spinner for the price
		SpinnerValueFactory<Double> spinnerFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0.00, 100.00, 0.00, 0.01);
		priceSpinner.setValueFactory(spinnerFactory);
		priceSpinner.setEditable(true);
		// spinner for stock
		stockSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0, 1));
		stockSpinner.setEditable(true);

		// add elements into grid
		dialogContent.add(img, 1, 0, 1, 4);
		dialogContent.add(imgUrl, 1, 4);
		dialogContent.add(browse, 2, 4);
		dialogContent.add(new Label("Item Name:"), 2, 0);
		dialogContent.add(itemName, 4, 0);
		dialogContent.add(new Label("Description:"), 2, 1);
		dialogContent.add(description, 4, 1);
		dialogContent.add(new Label("Price:"), 2, 2);
		dialogContent.add(new Label("$"), 3, 2);
		dialogContent.add(priceSpinner, 4, 2);
		dialogContent.add(new Label("No. of Stock:"), 2, 3);
		dialogContent.add(stockSpinner, 4, 3);
		
		// Enable/Disable Create button depending on whether info was entered.
		btnCreate = this.getDialogPane().lookupButton(createButtonType);
		btnCreate.setDisable(true);
		itemName.textProperty().addListener((observable, oldValue, newValue) -> {
			validateCreateButton();
		});
		description.textProperty().addListener((observable, oldValue, newValue) -> {
			validateCreateButton();
		});

		getDialogPane().setContent(dialogContent);
		
		// event handler for browse button
		browse.setOnAction(new EventHandler<ActionEvent>() {
	                @Override
	                public void handle(final ActionEvent e) {
	                	FileChooser fileChooser = new FileChooser();
	                	fileChooser.setTitle("Choose an Image");
	                	fileChooser.setInitialDirectory(new File(System.getProperty("user.dir") + "\\images"));
	                	fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));

	                    File file = fileChooser.showOpenDialog(((InventoryTab) parent).getWindow());
	                    if (file != null) {
	                        imgUrl.setText(file.toString());
	                        // http://gamedev.stackexchange.com/questions/72924/how-do-i-add-an-image-inside-a-rectangle-or-a-circle-in-javafx
	                        Image image = new Image(file.toURI().toString());
	                        ImagePattern imagePattern = new ImagePattern(image);
	                        img.setFill(imagePattern);
	                        validateCreateButton();
	                    }
	                }
	            });
		
		// Convert the result to a MokuStockItem when the create button is clicked.
		setResultConverter(dialogButton -> {
		    if (dialogButton == createButtonType) {
		    	// error handling
		    	String desc, url;
		    	if(description.getText().isEmpty()) {
		    		desc = "";
		    	} else {
		    		desc = description.getText();
		    	}
		    	if(imgUrl.getText().isEmpty()) {
		    		url = "";
		    	} else {
		    		url = imgUrl.getText();
		    	}
		        return new MokuStockItem(parent, 0, itemName.getText(), desc, Double.parseDouble(priceSpinner.getEditor().getText()), Integer.parseInt(stockSpinner.getEditor().getText()), url);
		    }
		    return null;
		});
	}

	protected void validateCreateButton() {
		if (itemName.getText().isEmpty()) {
			btnCreate.setDisable(true);
		} else {
			btnCreate.setDisable(false);
		}
	}
}
