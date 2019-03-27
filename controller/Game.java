package controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import global.Configuration;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Coord;
import model.InvalidMoveException;
import model.Level;
import model.LevelReader;
import model.MoveEvent;
import model.Tile;

/**
 * Game
 */
public class Game extends Application implements Observer {
	ArrayList<Level> lvls;
	int curLvl;
	VBox vB;
	VBox vue;
	HBox buttons;
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

	double getTerrainWidth() {
		return stage.getWidth();
	}

	double getTerrainHeight() {
		return stage.getHeight() - buttons.getHeight();
	}

	Pane getPane(Coord c) {
		HBox ln = (HBox) vB.getChildren().get(c.getRow());
		return (Pane) ln.getChildren().get(c.getCol());
	}

	void move(Coord from, Coord to) {
		Pane pn = getPane(from);
		ImageView iv = (ImageView) pn.getChildren().get(pn.getChildren().size() - 1);
		pn.getChildren().remove(iv);
		pn = getPane(to);
		pn.getChildren().add(iv);
	}

	Pane createPaneForTile(Tile t) {
		InputStream is = conf.loadImage(Tile.FLOOR);
		Pane pn = new Pane(new ImageView(new Image(is)));
		if (!t.equals(Tile.FLOOR)) {
			switch (t) {
			case PLAYER_GOAL:
				is = conf.loadImage(Tile.GOAL);
				pn.getChildren().add(new ImageView(new Image(is)));
				is = conf.loadImage(Tile.PLAYER);
				pn.getChildren().add(new ImageView(new Image(is)));
				break;
			default:
				is = conf.loadImage(t);
				pn.getChildren().add(new ImageView(new Image(is)));
				break;
			}
		}

		return pn;
	}

	void GG() {
		vB.getChildren().clear();
		Label label = new Label("Bravo !");
		label.setTextFill(Color.web("#144d0b"));
		label.setFont(Font.font(20));
		HBox labelBox = new HBox(label);
		labelBox.setAlignment(Pos.CENTER);
		vB.getChildren().add(labelBox);
	}

	void drawLevel() {
		Level l = lvls.get(curLvl);
		l.addObserver(this);
		conf.logger().info("Drawing level " + (curLvl + 1) + "/" + lvls.size());
		vB.getChildren().clear();

		// double tileSz = Math.min((getTerrainWidth()) / l.getNbCols(),
		// (getTerrainHeight()) / l.getNbRows());
		// System.out.println("Size: " + tileSz);

		l.forEach((Tile elem, int row, int col, int nbRow, int nbCol) -> {
			if (col == 0) {
				vB.getChildren().add(new HBox());
			}

			Pane pn = createPaneForTile(elem);

			((HBox) vB.getChildren().get(vB.getChildren().size() - 1)).getChildren().add(pn);
			pn.setOnMouseClicked(event -> {
				System.out.println("Click on position : " + row + "," + col);
				try {
					l.movePusher(new Coord(row, col));
					if (l.isCompleted()) {
						GG();
					}
				} catch (InvalidMoveException e) {
					System.out.println("MAAAAAAAIS: " + e.getMessage());
				}
			});
		});

		fixSize();
	}

	void fixSize() {
		Level l = lvls.get(curLvl);
		double tileSz = Math.min((getTerrainWidth()) / l.getNbCols(), (getTerrainHeight()) / l.getNbRows());

		vB.getChildren().forEach((n) -> {
			((HBox) n).getChildren().forEach((n2) -> {
				((Pane) n2).getChildren().forEach((n3) -> {
					((ImageView) n3).setFitWidth(tileSz);
					((ImageView) n3).setFitHeight(tileSz);
				});
			});
		});
	}

	boolean hasNextLvl() {
		return (curLvl + 1 < lvls.size());
	}

	@Override
	public void start(Stage primaryStage) {
		this.stage = primaryStage;
		primaryStage.setTitle("Sokoban");
		primaryStage.setOnCloseRequest(we -> System.out.println("Fin du jeu"));

		vB = new VBox();
		vue = new VBox(vB);
		vue.setAlignment(Pos.CENTER);
		Button but = new Button("Next Level >");
		buttons = new HBox(but);
		but.setOnMouseClicked(event -> {
			curLvl++;
			drawLevel();
			but.setDisable(!hasNextLvl());
		});

		but.setDisable(!hasNextLvl());

		buttons.setMinHeight(45);
		buttons.setAlignment(Pos.CENTER);
		vue.getChildren().add(buttons);

		Scene s = new Scene(vue);
		primaryStage.setScene(s);
		primaryStage.setWidth(800);
		primaryStage.setHeight(700);

		ChangeListener<Number> resizeListener = (observable, oldValue, newValue) -> {
			fixSize();
		};

		primaryStage.widthProperty().addListener(resizeListener);
		primaryStage.heightProperty().addListener(resizeListener);

		drawLevel();
		primaryStage.show();
	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg instanceof MoveEvent) {
			conf.logger().info("MoveEvent received");
			MoveEvent me = (MoveEvent) arg;
			move(me.from, me.to);
		}
	}

}