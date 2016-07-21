package mokuroku.tabs.interfaceParts;

import java.io.File;

import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import mokuroku.tabs.InventoryTab;

/**
 * @author Rebecca Lee
 *
 */
public class EditItemDialog extends NewItemDialog {

	public EditItemDialog(InventoryTab parent, MokuStockItem item) {
		super(parent);
		setTitle("Edit Item");
		
		itemName.setText(item.getName());
		description.setText(item.getDescription());
		imgUrl.setText(item.getImage());
		// set spinner for the price
		priceSpinner.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0.00, 100.00, item.getPrice(), 0.01));
		priceSpinner.setEditable(true);
		// spinner for stock
		stockSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, item.getStock(), 1));
		stockSpinner.setEditable(true);
		// item image
		if (item.getImage() != "") {
			File file = new File(item.getImage());
			Image image = new Image(file.toURI().toString());
	        ImagePattern imagePattern = new ImagePattern(image);
	        img.setFill(imagePattern);
		}

		getDialogPane().setContent(dialogContent);
	}

}
