public class Solver {

    static int GRID_ROWS = 9;
    static int GRID_COLUMNS = 9;
    static int MININUM_NUMBER = 1;
    static int MAXIMUM_NUMBER = 9;

    static int[] grid;

    public Solver(int[] input_grid){
        grid = input_grid;
    }

    public static boolean solveSudoku(){
        for(int row = 0; row < GRID_ROWS;row++){
            for(int column = 0; column < GRID_COLUMNS; column++){
                int cellIndex = row*9+column;
                //if cell value not set check if GRID is valid for value i
                if(grid[cellIndex] < MININUM_NUMBER){
                    for(int i = MININUM_NUMBER;i <= MAXIMUM_NUMBER;i++){
                        grid[cellIndex]=i;
                        if(checkMoveValid(cellIndex)&&solveSudoku()){
                            return true;
                        }
                        grid[cellIndex]=0;
                    }
                    return false;
                }
            }
        }
        printBoard();
        return true;
    }

    static private boolean checkMoveValid(int cellIndex){
        if(checkRowValid(cellIndex)&&checkColumnValid(cellIndex)&&checkBoxValid(cellIndex)){
            return true;
        }
        return false;
    }

    static private boolean checkRowValid(int cellIndex){
        int[] rowArray = new int[GRID_COLUMNS];
        int columnIndex = cellIndex - (cellIndex%9);
        for(int index = 0;index<GRID_COLUMNS;index++){
            rowArray[index]=grid[columnIndex+index];
        }
        if(checkIfArrayContainsDuplicates(rowArray)){
            return false;
            }
        return true;
    }

    static private boolean checkColumnValid(int cellIndex){
        int[] columnArray = new int[GRID_ROWS];
        int rowIndex = cellIndex%9;
        for(int index = 0;index<GRID_ROWS;index++){
            columnArray[index]=grid[rowIndex+GRID_ROWS*index];
        }
        if(checkIfArrayContainsDuplicates(columnArray)){
            return false;
            }
        return true;
    }

    static private boolean checkBoxValid(int cellIndex){
        int[] cellArray = new int[(GRID_ROWS+GRID_COLUMNS)/2];
        int startColumnIndex = (cellIndex%9)-(cellIndex%9)%3;
        int startRowIndex = (cellIndex/9)-(cellIndex/9)%3;
        int startIndex = startRowIndex + startColumnIndex * 9;
        for(int index = 0;index<3;index++){
            for(int i = 0;i<3;i++){
                cellArray[i+3*index]=grid[startIndex+i+index*9];
            }
        }
        if(checkIfArrayContainsDuplicates(cellArray)){
            return false;
            }
        return true;
    }


    static private boolean checkIfArrayContainsDuplicates(int[] arr){
        for(int i = 0;i<arr.length-1;i++){
            for(int j = i;j<arr.length-1;j++){
                if(arr[i]==arr[j+1]&&arr[i]!=0){
                    return true;
                }
            }
        }
        return false;
    }

    static private void printBoard() {
    for (int row = 0; row < 9; row++) {
        for (int column = 0; column < 9; column++) {
            System.out.print(grid[row*9+column] + " ");
        }
        System.out.println();
    }
}
    // public static void main(String [] args){
    //     int[] GRID = {
    //      0, 3, 2, 4, 8, 0, 5, 0, 6,
    //      0, 0, 0, 0, 6, 0, 0, 4, 1,
    //      0, 0, 6, 5, 0, 9, 0, 3, 0,
    //      0, 0, 0, 0, 0, 6, 3, 2, 0,
    //      0, 0, 8, 2, 0, 3, 1, 0, 0,
    //      0, 2, 9, 1, 0, 0, 0, 0, 0,
    //      0, 5, 0, 7, 0, 8, 9, 0, 0,
    //      9, 8, 0, 0, 2, 0, 0, 0, 0,
    //      2, 0, 7, 0, 3, 4, 8, 1, 0   
    //                };
    //     Solver solv = new Solver(GRID);
        
    //     solveSudoku();

    // }
}