package mokuroku.menus;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.input.KeyCombination;

public class FileMenu extends Menu{

	public FileMenu() {
		setText("File");
		
		MenuItem open = new MenuItem("Open Existing Moku");
		open.setAccelerator(KeyCombination.keyCombination("Ctrl+O"));
			
		MenuItem save = new MenuItem("Save Current Moku");
		save.setAccelerator(KeyCombination.keyCombination("Ctrl+S"));
		
		MenuItem exit = new MenuItem("Exit");
		exit.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
               System.exit(0);
            }
        });        
		
		getItems().addAll(open, save, new SeparatorMenuItem(), exit);
	}
	
}
