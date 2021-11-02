import java.util.Scanner;
public class sudoku
{
    //to check for whether it is already solved
    public static boolean solved=true;
    //Printing the Board
    public static void printBoard(int[][] board)
    {
        //check for out of bounds
        try{
            for(int i=0;i<board.length;i++){
                for(int j=0;j<board.length;j++){
                    if(board[i][j]==0&&sudoku.solved==true)
                        sudoku.solved=false;
                    System.out.print(board[i][j]+" ");
                }
                System.out.println();
            }
        }
        catch(Exception e){
            System.out.println("\nGrid should consist same number of rows and columns (9*9 elements)\nProgram terminated");
        }
    }

    //Main logic to solve Sudoku
    public static boolean solveSudoku(int[][] board)
    {
        int row=0,col=0;
        boolean ifBlank=false;
        /* verify if sudoku is already solved, if not solved,get next "blank" space position */ 
        for(row=0;row<board.length;row++){
            for(col=0;col<board[row].length;col++){
                if(board[row][col]==0){
                    ifBlank=true;
                    break;
                }
            }
            if(ifBlank==true){
                break;
            }
        }
        //if no blank tiles, puzzle solved return truw
        if(ifBlank==false){
            return true;
        }

        //filling blank spaces with numbers
        for(int i=1;i<=9;i++){
            //check that number isn't already there in row and col
            if(isSafe(board,row,col,i)){
                board[row][col]=i;
                if(solveSudoku(board)){
                    return true;
                }
                //backtrack with other number, if number placed in incorrect position by placing 0
                board[row][col]=0;
            }
        }
        return false;
    }
    public static boolean isSafe(int[][] board,int row,int col,int number)
    {
        return (!isInRow(board,row,number)&&!isInCol(board,col,number)&&
                !isInBox(board,row-(row%3),col-(col%3),number));
    }
    public static boolean isInRow(int[][] board,int row,int number)
    {
        for(int i=0;i<board.length;i++){
            if(board[row][i]==number){
                return true;
            }
        }
        return false;
    }
    public static boolean isInCol(int[][] board,int col,int number)
    {
        for(int i=0;i<board.length;i++){
            if(board[i][col]==number){
                return true;
            }
        }
        return false;
    }
    public static boolean isInBox(int[][] board,int row,int col,int number)
    {
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(board[row+i][j+col]==number){
                    return true;
                }
            }
        }
        return false;
    }
    public static void main(String ar[])
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Select your choice:\n1.Use defaults system generated initial grid\n2.Enter dynamic initial grid");
        try{
        int choice=sc.nextInt();sc.nextLine();
        
        if(choice==1)
        {
            //Here "0" represents a empty tile/block
            int[][] board={ { 3, 0, 6, 5, 0, 8, 4, 0, 0 },
                            { 5, 2, 0, 0, 0, 0, 0, 0, 0 },
                            { 0, 8, 7, 0, 0, 0, 0, 3, 1 },
                            { 0, 0, 3, 0, 1, 0, 0, 8, 0 },
                            { 9, 0, 0, 8, 6, 3, 0, 0, 5 },
                            { 0, 5, 0, 0, 9, 0, 6, 0, 0 },
                            { 1, 3, 0, 0, 0, 0, 2, 5, 0 },
                            { 0, 0, 0, 0, 0, 0, 0, 7, 4 },
                            { 0, 0, 5, 2, 0, 6, 3, 0, 0 } };
            System.out.println("Initial Grid");
            printBoard(board);
            //System.out.println("Solution-----------------");
            if(solveSudoku(board)&&sudoku.solved==false){
                System.out.println("----------------Solution-----------------");
                printBoard(board);
            }else{
                System.out.println("\nNo solution found");
            }
        }
        else if(choice==2){
            System.out.println("Enter initial sudoku value. enter '0' to represent blank");
            int[][] board=new int[9][9];
            //while(board[i][j]<=9 && board[i][j]>=1)
            try{
                for(int i=0;i<board.length;i++){
                    for(int j=0;j<board.length;j++){
                        board[i][j]=sc.nextInt();
                        if(board[i][j]==0){
                            continue;
                        }else{
                            if(board[i][j]<=0 || board[i][j]>=10)
                            {
                                System.out.println("Error: Input should be any number between 1 to 9 or a 0\nProgram Terminated"+board[i][j]);
                                return;
                            }
                        }
                    }
                }
                System.out.println("Initial Grid");
                printBoard(board);
                //System.out.println("Solution-----------------");
                if(solveSudoku(board)&&sudoku.solved==false){
                    System.out.println("----------------Solution-----------------");
                    printBoard(board);
                }else{
                    System.out.println("\nNo solution found");
                }
            }
            catch(Exception e)
            {
                System.out.println("Accepts only Numbers.Enter number between 1 to 9");
                // e.printStackTrace();
            }
        }
        }
        catch(Exception e){
            System.out.println("Accepts only Numbers.Enter number 1 or 2 in selection of initial grid");
        }
    }
}