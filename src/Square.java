import java.awt.*;
public class Square {
    public Tile[] contents = new Tile[0];
    public int[] coords;
    public boolean selected;
    public State won;
    public Square(int[] coo){
        selected = false;
        won = State.EMPTY;
        coords = coo;
    }
    public void addTo(Tile t){
        Tile[] temp = new Tile[contents.length + 1];
        for (int i = 0; i < contents.length; i++) {
            temp[i] = contents[i];
        }
        temp[contents.length] = t;
        contents = temp;
    }
    public void blink (){
        StdDraw.setPenRadius(0.005);
        for (int i = 0; i < 10; i++) {
            Color color = new Color((int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256));
            StdDraw.setPenColor(color);
            StdDraw.square((coords[0] + 0.5) / 3, (coords[1] + 0.5) / 3, 1 / 6.0);
            StdDraw.pause(5);
        }
    }
    public void winCheck(){
        //horizontal
        for (int i = 0; i < 3; i++) {
            if (contents[i * 3].state == contents[i * 3 + 1].state && contents[i * 3 + 1].state == contents[i * 3 + 2].state && contents[i * 3].state != State.EMPTY){
                won = contents[i * 3].state;
                System.out.println("Horizontal Win " + i);
                break;
            }
        }
        //vertical
        for (int i = 0; i < 3; i++) {
            if (contents[i].state == contents[i + 3].state && contents[i + 3].state == contents[i + 6].state && contents[i].state != State.EMPTY){
                won = contents[i].state;
                System.out.println("vertical Win " + i);
                break;
            }
        }
        //diagonal
        if (contents[4].state != State.EMPTY && (contents[0].state == contents[4].state && contents[4].state == contents[8].state) || (contents[6].state == contents[4].state && contents[4].state == contents[2].state)){
            won = contents[4].state;
            System.out.println("Diagonal Win");
        }
    }
}
