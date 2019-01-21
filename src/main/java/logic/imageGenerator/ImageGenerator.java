package logic.imageGenerator;

import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import logic.sudoku.GameField;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageGenerator extends Canvas {
    private GameField gameField;
    private static int cellSize = 50;
    private static int canvasSize = cellSize * 9;
    private static int fontSize = 30;
    private static int verticalTextOffset = 37;
    private static int horizontalTextOffset = 15;

    private ImageGenerator(GameField gameField) {
        this.gameField = gameField;
    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setBackground(Color.WHITE);
        g2.clearRect(0, 0, this.getWidth(), this.getHeight());
        g2.setColor(Color.BLACK);
        g2.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));

        int fieldSize = gameField.getSize();

        for (int row = 0; row < fieldSize; row++) {
            int rowOffset = row == 0 ? cellSize : cellSize * (row + 1);
            if (row == 2 || row == 5) {
                g2.setStroke(new BasicStroke(3));
            }

            if (row != 8) {
                g2.drawLine(0, rowOffset, canvasSize, rowOffset);
            }

            g2.setStroke(new BasicStroke(1));

            for (int column = 0; column < fieldSize; column++) {
                int columnOffset = column == 0 ? cellSize : cellSize * (column + 1);

                if (column == 2 || column == 5) {
                    g2.setStroke(new BasicStroke(3));
                }

                if (column != 8) {
                    g2.drawLine(columnOffset, 0, columnOffset, canvasSize);
                }

                g2.setStroke(new BasicStroke(1));
                int cellValue = gameField.get(row, column);

                if (cellValue != 0) {
                    String cellText = String.valueOf(cellValue);
                    g2.drawString(
                        cellText,
                        (cellSize * column) + horizontalTextOffset,
                        (cellSize * row) + verticalTextOffset
                    );
                }
            }
        }
    }

    public static void generateAndSave(GameField gameField, Stage mainStage) {
        Canvas canvas = new ImageGenerator(gameField);
        canvas.setSize(canvasSize, canvasSize);
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save");

        BufferedImage image = new BufferedImage(canvasSize, canvasSize, BufferedImage.TYPE_INT_RGB);

        Graphics2D g2 = (Graphics2D)image.getGraphics();

        canvas.paint(g2);

        File file = fileChooser.showSaveDialog(mainStage);
        if (file != null) {
            if (!file.getName().contains(".png")) {
                file = new File(file.getAbsolutePath() + ".png");
            }

            try {
                ImageIO.write(image, "png", file);
            } catch (Exception error) {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Error");
                errorAlert.setHeaderText(error.getMessage());
            }
        }
    }
}
