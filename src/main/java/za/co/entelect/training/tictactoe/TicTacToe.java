package za.co.entelect.training.tictactoe;

public class TicTacToe {
    private Board board;
    private Display display;
    private int turn=1, who=1;
    private Player player1;
    private Player player2;


    public TicTacToe(){
        board = new Board();
        display = new Display();
        startPlayers();

        while( play() );
    }

    public void startPlayers(){
        this.player1 = new Player(1);
        this.player2 = new Player(2);
    }

    public boolean play(){
        board.showBoard();
        if(won() == 0 ){
            display.showln("----------------------");
            display.showln("\nTurn "+turn);
            display.showln("It's turn of Player " + who() );

            if(who()==1)
                player1.play(board);
            else
                player2.play(board);


            if(board.fullBoard()){
                display.showln("Full Board. Draw!");
                return false;
            }
            who++;
            turn++;

            return true;
        } else{
            if(won() == -1 )
                display.showln("Player 1 won!");
            else
                display.showln("Player 2 won!");

            return false;
        }

    }

    public int who(){
        if(who%2 == 1)
            return 1;
        else
            return 2;
    }

    /**
     * Return 0 if game not over.
     * Return -1 if player 1 wins.
     * Return 1 if player 2 wins.
     */
    
    public int won(){
        if(board.checkLines() == 1)
            return 1;
        if(board.checkColumns() == 1)
            return 1;
        if(board.checkDiagonals() == 1)
            return 1;

        if(board.checkLines() == -1)
            return -1;
        if(board.checkColumns() == -1)
            return -1;
        if(board.checkDiagonals() == -1)
            return -1;

        return 0;
    }


}