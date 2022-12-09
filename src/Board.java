import java.awt.*;
public class Board {
    public static void init(){
        if (Main.turn == State.CROSS){
            StdDraw.setTitle("Turn: X");
        } else {
            StdDraw.setTitle("Turn: O");
        }
        StdDraw.clear();
        StdDraw.setPenColor(Color.BLACK);
        for (int i = 0; i < 10; i++){
            StdDraw.setPenRadius(i % 3 == 0? 0.005 : 0.002);
            StdDraw.line(i/9.0,0,i/9.0,1);
            StdDraw.line(0,i/9.0,1,i/9.0);
        }
        for (Square s: Main.squares) {
            for (Tile t : s.contents) {
                if (t != null) {
                    t.draw();
                }
            }
            if (s.won == State.CROSS) {
                StdDraw.setPenColor(Color.BLUE);
                StdDraw.line((s.coords[0] + 0.1) / 3.0, (s.coords[1] + 0.1) / 3.0, (s.coords[0] + 0.9) / 3.0, (s.coords[1] + 0.9) / 3.0);
                StdDraw.line((s.coords[0] + 0.9) / 3.0, (s.coords[1] + 0.1) / 3.0, (s.coords[0] + 0.1) / 3.0, (s.coords[1] + 0.9) / 3.0);
            } else if (s.won == State.NOUGHT){
                StdDraw.setPenColor(Color.RED);
                StdDraw.circle((s.coords[0] + 0.5) / 3.0, (s.coords[1] + 0.5) / 3.0, 1 / 7.0);
            }
        }
        if (Main.win == State.CROSS) {
            StdDraw.setPenColor(Color.BLUE);
            StdDraw.line(0.1, 0.1, 0.9, 0.9);
            StdDraw.line(0.9, 0.1, 0.1, 0.9);
        } else if (Main.win == State.NOUGHT){
            StdDraw.setPenColor(Color.RED);
            StdDraw.circle(0.5, 0.5, 0.4);
        }
    }
}
