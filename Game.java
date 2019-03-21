
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

import global.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import structures.List;
import structures.ListFactory;

/**
 * Game
 */
public class Game extends Application {
	ArrayList<Level> lvls;
	VBox box;
	Configuration conf;
	private Stage stage;

	public static void main(String[] args) {
		Game.launch(args);
	}

	@Override
	public void init() throws Exception {
		conf = Configuration.getInstance();
		LevelReader lvReader = new LevelReader(conf.load("Original.txt"));
		lvls = new ArrayList<>();
		Level lvl;
		while ((lvl = lvReader.readNextLevel()) != null) {
			lvls.add(lvl);
		}
		conf.logger().info("nb levels read: " + lvls.size());
	}

	void drawLevel(int no) {
		Level l = lvls.get(no);
		double tileSz = Math.min(stage.getWidth() / l.getNbCols(), stage.getHeight() / l.getNbRows());
		System.out.println("Size: " + tileSz);

		l.forEach((Tile elem, int row, int col, int nbRow, int nbCol) -> {
			InputStream is = conf.load("Images/" + elem.getImg());
			if (col == 0) {
				box.getChildren().add(new HBox());
			}
			ImageView iv = new ImageView(new Image(is, tileSz, tileSz, true, true));
			((HBox) box.getChildren().get(box.getChildren().size() - 1)).getChildren().add(iv);
		});

		System.out.println(
				"size2: " + box.getChildren().size() + ", " + ((HBox) box.getChildren().get(0)).getChildren().size());

	}

	@Override
	public void start(Stage primaryStage) {
		this.stage = primaryStage;
		primaryStage.setTitle("Sokoban");
		primaryStage.setOnCloseRequest(we -> System.out.println("Fin du jeu"));

		box = new VBox();

		Scene s = new Scene(box);

		primaryStage.setScene(s);
		primaryStage.setWidth(800);
		primaryStage.setHeight(700);

		drawLevel(0);
		primaryStage.show();
	}

}