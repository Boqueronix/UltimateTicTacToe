import java.awt.*;

public class Tile {
    public int[] coords = new int[2];
    public Square parent;
    public State state;

    public Tile(int[] coo, Square par){
        coords = coo;
        parent = par;
        state = State.EMPTY;
    }
    public void draw(){
        StdDraw.setPenRadius(0.01);
        if (state == State.NOUGHT){
            StdDraw.setPenColor(Color.RED);
            StdDraw.circle((coords[0] + 0.5) / 9.0, (coords[1] + 0.5) / 9.0, 1 / 21.0);
        } else if (state == State.CROSS) {
            StdDraw.setPenColor(Color.BLUE);
            StdDraw.line((coords[0] + 0.1) / 9.0, (coords[1] + 0.1) / 9.0, (coords[0] + 0.9) / 9.0, (coords[1] + 0.9) / 9.0);
            StdDraw.line((coords[0] + 0.9) / 9.0, (coords[1] + 0.1) / 9.0, (coords[0] + 0.1) / 9.0, (coords[1] + 0.9) / 9.0);
        }

    }
}
