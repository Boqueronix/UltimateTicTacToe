public class Main {
    public static Square[] squares = new Square[9];
    public static Square selected = null;
    public static Tile[] all = new Tile[81];
    public static State turn = State.CROSS;
    public static boolean gameEnd = false;
    public static boolean mousePressed = false;
    public static State win = State.EMPTY;
    public static void main(String[] args) {
        for (int i = 0; i < 9; i++) {
            squares[i] = new Square(new int[] {i % 3, (int) (i / 3.0)});
        }
        for (int j = 0; j < 9; j++) {
            for (int i = 0; i < 9; i++) {
                Tile temp = new Tile(new int[]{i, j}, squares[((j / 3) * 3) + (i / 3)]);
                squares[((j / 3) * 3) + (i / 3)].addTo(temp);
                all[i * 9 + j] = temp;
            }
        }
        Board.init();
        while (win == State.EMPTY) {
            if (selected != null){
                selected.blink();
            }
            if (StdDraw.isMousePressed() && !mousePressed) {
                mousePressed = true;
                Tile t = findTile(StdDraw.mouseX, StdDraw.mouseY);
                if (t.state == State.EMPTY){
                    click(t);
                }
                winCheck();
                Board.init();
            } else if (!StdDraw.isMousePressed() && mousePressed) {
                mousePressed = false;
            }
        }
        System.out.println("WIN");
    }
    public static void click(Tile t){
        if (t != null && (selected == null || t.parent == selected)){
            t.state = (turn == State.CROSS)? State.CROSS: State.NOUGHT;
            t.draw();
            turn = (turn != State.CROSS)? State.CROSS: State.NOUGHT;
            t.parent.winCheck();
            Square s = squares[findIndex(t, t.parent)];
            if (s.won == State.EMPTY) {
                selected = s;
            } else {
                selected = null;
            }
        }
    }
    public static Tile findTile(double x, double y){
        for(Tile t: all){
            if (t.coords[0] == (int) (x * 9) && t.coords[1] == (int) (y * 9)){
                return t;
            }
        }
        return null;
    }
    public static int findIndex(Tile t, Square s){
        for (int i = 0; i < 9; i++){
            if (s.contents[i] == t){
                return i;
            }
        }
        return -1;
    }
    public static void winCheck(){
        //horizontal
        for (int i = 0; i < 3; i++) {
            if (squares[i * 3].won == squares[i * 3 + 1].won && squares[i * 3 + 1].won == squares[i * 3 + 2].won && squares[i * 3].won != State.EMPTY){
                win = squares[i * 3].won;
                System.out.println("Horizontal Win " + i);
                break;
            }
        }
        //vertical
        for (int i = 0; i < 3; i++) {
            if (squares[i].won == squares[i + 3].won && squares[i + 3].won == squares[i + 6].won && squares[i].won != State.EMPTY){
                win = squares[i].won;
                System.out.println("vertical Win " + i);
                break;
            }
        }
        //diagonal
        if (squares[4].won != State.EMPTY && (squares[0].won == squares[4].won && squares[4].won == squares[8].won) || (squares[6].won == squares[4].won && squares[4].won == squares[2].won)){
            win = squares[4].won;
            System.out.println("Diagonal Win");
        }
    }
}