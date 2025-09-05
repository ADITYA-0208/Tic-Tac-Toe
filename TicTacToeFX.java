// Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TicTacToeFX extends Application {
   private TicTacToe game = new TicTacToe();
   private Button[][] buttons = new Button[3][3];
   private boolean gameOver = false;
   private char currentPlayer = 'X';

   public TicTacToeFX() {
   }

   public void start(Stage var1) throws Exception {
      GridPane var2 = new GridPane();
      var2.setAlignment(Pos.CENTER);
      var2.setHgap(10.0);
      var2.setVgap(10.0);

      for(int var3 = 0; var3 < 3; ++var3) {
         for(int var4 = 0; var4 < 3; ++var4) {
            Button var5 = new Button();
            var5.setPrefSize(100.0, 100.0);
            var5.setFont(Font.font("Verdana", FontWeight.BOLD, 36.0));
            var2.add(var5, var4, var3);
            this.buttons[var3][var4] = var5;
            var5.setOnAction((var4x) -> {
               if (!this.gameOver) {
                  if (this.game.board[var3][var4] == ' ') {
                     this.game.makeMove(var3, var4, this.currentPlayer);
                     var5.setText(String.valueOf(this.currentPlayer));
                     this.checkGameState();
                     if (!this.gameOver) {
                        this.currentPlayer = (char)(this.currentPlayer == 'X' ? 79 : 88);
                     }
                  }

               }
            });
         }
      }

      StackPane var8 = new StackPane();
      var8.getChildren().add(var2);
      Scene var9 = new Scene(var8, 320.0, 320.0);
      var1.setTitle("Tic-Tac-Toe");
      var1.setScene(var9);
      var1.show();
   }

   private void checkGameState() {
      if (this.game.isGameOver()) {
         this.gameOver = true;
         if (this.game.hasWon('X')) {
            this.showWinningLine(this.getWinningLine());
            this.showMessage("Player X wins!");
         } else if (this.game.hasWon('O')) {
            this.showWinningLine(this.getWinningLine());
            this.showMessage("Player O wins!");
         } else {
            this.showMessage("It's a tie!");
         }
      }

   }

   private void showMessage(String var1) {
      Text var2 = new Text(var1);
      var2.setFont(Font.font("Verdana", FontWeight.BOLD, 24.0));
      var2.setFill(Color.RED);
      StackPane var3 = (StackPane)this.buttons[0][0].getParent().getParent();
      var3.getChildren().add(var2);
      StackPane.setAlignment(var2, Pos.BOTTOM_CENTER);
   }

   private void showWinningLine(Line var1) {
      if (var1 != null) {
         StackPane var2 = (StackPane)this.buttons[0][0].getParent().getParent();
         Line var3 = new Line(var1.getStartX(), var1.getStartY(), var1.getEndX(), var1.getEndY());
         var3.setStrokeWidth(5.0);
         var3.setStroke(Color.GREEN);
         var2.getChildren().add(var3);
      }
   }

   public Line getWinningLine() {
      int var1;
      for(var1 = 0; var1 < 3; ++var1) {
         if (this.game.board[var1][0] != ' ' && this.game.board[var1][0] == this.game.board[var1][1] && this.game.board[var1][1] == this.game.board[var1][2]) {
            return new Line(50.0, ((double)var1 + 0.5) * 100.0, 250.0, ((double)var1 + 0.5) * 100.0);
         }
      }

      for(var1 = 0; var1 < 3; ++var1) {
         if (this.game.board[0][var1] != ' ' && this.game.board[0][var1] == this.game.board[1][var1] && this.game.board[1][var1] == this.game.board[2][var1]) {
            return new Line(((double)var1 + 0.5) * 100.0, 50.0, ((double)var1 + 0.5) * 100.0, 250.0);
         }
      }

      if (this.game.board[1][1] != ' ' && this.game.board[0][0] == this.game.board[1][1] && this.game.board[1][1] == this.game.board[2][2]) {
         return new Line(50.0, 50.0, 250.0, 250.0);
      } else if (this.game.board[1][1] != ' ' && this.game.board[0][2] == this.game.board[1][1] && this.game.board[1][1] == this.game.board[2][0]) {
         return new Line(250.0, 50.0, 50.0, 250.0);
      } else {
         return null;
      }
   }
}
