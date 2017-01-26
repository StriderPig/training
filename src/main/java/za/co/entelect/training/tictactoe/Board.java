package za.co.entelect.training.tictactoe;

public class Board {
    private int[][] board = new int[3][3];
    private Display display;

    public Board(){
        display = new Display();
    }

    public void showBoard(){
        display.showln("");
        for(int line=0 ; line<3 ; line++){

            for(int column=0 ; column<3 ; column++){

                if(board[line][column]==-1){
                    display.show(" X ");
                }
                if(board[line][column]==1){
                    display.show(" O ");
                }
                if(board[line][column]==0){
                    display.show("   ");
                }

                if(column==0 || column==1)
                    display.show("|");
            }
            display.showln("");
        }

    }

    public int getPosition(int[] attempt){
        return board[attempt[0]][attempt[1]];
    }

    public void setPosition(int[] attempt, int player){
        if(player == 1)
            board[attempt[0]][attempt[1]] = -1;
        else
            board[attempt[0]][attempt[1]] = 1;
    }

    public int checkLines(){
        return 0;
        // Implement this.
    }

    public int checkColumns(){
        return 0;
        // Code this goodness.

    }

    public int checkDiagonals(){
        return 0;
        // Bring it to liiiiiife!
    }

    public boolean fullBoard(){
        return false;
        // You know the drill.
    }
}