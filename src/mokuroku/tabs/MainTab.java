package mokuroku.tabs;

import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import mokuroku.DBConnection;

/**
 * @author Rebecca Lee
 * 
 */
public class MainTab extends Tab {
	
	MainTab self = this;
	TilePane left = new TilePane();
	
	int iid;
	DBConnection c;
	
	public MainTab(DBConnection c, int iid) {
		setText("Main");		
		this.iid = iid;
		this.c = c;
		
		ScrollPane scrollBar = new ScrollPane();
		left.setPadding(new Insets(30, 30, 30, 30));
		left.setVgap(30);
		left.setHgap(50);
		left.setPrefColumns(3);
		
		left.getChildren().addAll(c.getTileItems(iid));
		scrollBar.setContent(left);
		
		BorderPane right = new BorderPane();
		
		SplitPane splitPane = new SplitPane(scrollBar, right);
		splitPane.setDividerPosition(0, 0.8);
		
		setContent(splitPane);
	}

	public void update() {
		left.getChildren().clear();
		left.getChildren().addAll(c.getTileItems(iid));	
	}

}
