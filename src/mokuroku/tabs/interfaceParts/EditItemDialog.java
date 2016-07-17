package mokuroku.tabs.interfaceParts;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.GridPane;
import mokuroku.tabs.InventoryTab;

public class EditItemDialog extends Dialog<MokuStockItem> {
	
	protected ButtonType createButtonType = new ButtonType("Save", ButtonData.OK_DONE);
	protected Node btnSave;
	protected TextField itemName = new TextField();
	protected TextField description = new TextField();
	protected Spinner<Double> priceSpinner = new Spinner<>();
	protected Spinner<Integer> stockSpinner = new Spinner<>();

	public EditItemDialog(InventoryTab parent, MokuStockItem item) {
		setTitle("Edit Item");
		
		getDialogPane().getButtonTypes().addAll(createButtonType, ButtonType.CANCEL);
		
		GridPane dialogContent = new GridPane();
		dialogContent.setHgap(10);
		dialogContent.setVgap(10);
		dialogContent.setPadding(new Insets(20, 100, 10, 10));
		
		itemName.setText(item.getName());
		description.setText(item.getDescription());
		// set spinner for the price
		priceSpinner.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0.00, 100.00, item.getPrice(), 0.01));
		priceSpinner.setEditable(true);
		// spinner for stock
		stockSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, item.getStock(), 1));
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
		btnSave = this.getDialogPane().lookupButton(createButtonType);
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
			btnSave.setDisable(true);
		} else {
			btnSave.setDisable(false);
		}
	}


}
