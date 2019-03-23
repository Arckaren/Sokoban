
import java.beans.EventHandler;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

import global.*;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
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
	int curLvl;
	VBox box;
	Configuration conf;
	private Stage stage;

	public static void main(String[] args) {
		Game.launch(args);
	}

	@Override
	public void init() throws Exception {
		conf = Configuration.getInstance();
		curLvl = 0;
		LevelReader lvReader = new LevelReader(conf.load("Original.txt"));
		lvls = new ArrayList<>();
		Level lvl;
		while ((lvl = lvReader.readNextLevel()) != null) {
			lvls.add(lvl);
		}
		conf.logger().info("nb levels read: " + lvls.size());
	}

	void drawLevel() {
		Level l = lvls.get(curLvl);
		box.getChildren().clear();
		double tileSz = Math.min((stage.getWidth()) / l.getNbCols(), (stage.getHeight()) / l.getNbRows());
		System.out.println("Size: " + tileSz);

		l.forEach((Tile elem, int row, int col, int nbRow, int nbCol) -> {
			if (col == 0) {
				box.getChildren().add(new HBox());
			}
			InputStream isFloor = conf.load("Images/" + Tile.FLOOR.getImg());
			Pane pn = new Pane(new ImageView(new Image(isFloor)));
			if (!elem.equals(Tile.FLOOR)) {
				InputStream is = conf.load("Images/" + elem.getImg());
				pn.getChildren().add(new ImageView(new Image(is)));
			}

			((HBox) box.getChildren().get(box.getChildren().size() - 1)).getChildren().add(pn);
			pn.setOnMouseClicked(event -> {
				System.out.println("Click on position : " + col + "," + row);
			});
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

		ChangeListener<Number> resizeListener = (observable, oldValue, newValue) -> {
			Level l = lvls.get(curLvl);
			double tileSz = Math.min((stage.getWidth()) / l.getNbCols(), (stage.getHeight()) / l.getNbRows());
			System.out.println("Size: " + tileSz);
			box.getChildren().forEach((n) -> {
				((HBox) n).getChildren().forEach((n2) -> {
					((Pane) n2).getChildren().forEach((n3) -> {
						((ImageView) n3).setFitWidth(tileSz);
						((ImageView) n3).setFitHeight(tileSz);
					});
				});
			});
		};

		primaryStage.widthProperty().addListener(resizeListener);
		primaryStage.heightProperty().addListener(resizeListener);

		drawLevel();
		primaryStage.show();
	}

}