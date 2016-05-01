package mokuroku.tabs.interfaceParts;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import mokuroku.tabs.InventoryTab;

public class NewItemDialog extends Dialog<MokuStockItem> {
	
	private ButtonType createButtonType = new ButtonType("Create", ButtonData.OK_DONE);
	private Node btnCreate;
	private TextField itemName = new TextField();
	private TextField description = new TextField();
	
	public NewItemDialog(InventoryTab parent) {
		setTitle("Create New Item");
		
		getDialogPane().getButtonTypes().addAll(createButtonType, ButtonType.CANCEL);
		
		GridPane dialogContent = new GridPane();
		dialogContent.setHgap(10);
		dialogContent.setVgap(10);
		dialogContent.setPadding(new Insets(20, 100, 10, 10));
		
		itemName.setPromptText("Item Name");
		description.setPromptText("Description");
		// set spinner for the price
		SpinnerValueFactory<Double> spinnerFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0.00, 100.00, 0.00, 0.01);
		Spinner<Double> priceSpinner = new Spinner<>();
		priceSpinner.setValueFactory(spinnerFactory);
		priceSpinner.setEditable(true);
		// spinner for stock
		Spinner<Integer> stockSpinner = new Spinner<>();
		stockSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0, 1));
		stockSpinner.setEditable(true);

		// add elements into grid
		dialogContent.add(new Label("Item Name:"), 0, 0);
		dialogContent.add(itemName, 2, 0);
		dialogContent.add(new Label("Description:"), 0, 1);
		dialogContent.add(description, 2, 1);
		dialogContent.add(new Label("Price:"), 0, 2);
		dialogContent.add(new Label("$"), 1, 2);
		dialogContent.add(priceSpinner, 2, 2);
		dialogContent.add(new Label("No. of Stock:"), 0, 3);
		dialogContent.add(stockSpinner, 2, 3);
		
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
		
		// Convert the result to a MokuStockItem when the create button is clicked.
		setResultConverter(dialogButton -> {
		    if (dialogButton == createButtonType) {
		        return new MokuStockItem(parent, 0, itemName.getText(), description.getText(), Double.parseDouble(priceSpinner.getEditor().getText()), Integer.parseInt(stockSpinner.getEditor().getText()));
		    }
		    return null;
		});
	}

	private void validateCreateButton() {
		if (itemName.getText().isEmpty() || description.getText().isEmpty()) {
			btnCreate.setDisable(true);
		} else {
			btnCreate.setDisable(false);
		}
	}
}
