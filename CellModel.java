/**
 * Conway's Game of Life!
 * @EoinCorless
 * @23rd/March/2025
 */
//IMPORTS//
import java.awt.*;
import javax.swing.*;
import java.awt.Graphics;
import java.util.Arrays;
import java.awt.event.*;
import java.awt.image.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.IOException;
public class CellModel extends JFrame implements Runnable, MouseListener, MouseMotionListener
{
    private static final Dimension WindowSize = new Dimension(800,800);//Window Dimensions
    private BufferStrategy strategy;//Used in Double Buffering
    public boolean[][] cells;//2D Array of cell game states
    public boolean[][] cellsPrevious;//2D Array of previous cell game states
    public boolean gameInProgress = false;//True when game is running, false when it isn't
    public boolean frameAdvanceOn = false;//When true, game advances only one frame, when false it animates at full speed
    public int cellXLength = 40;//How many cells long the game is in the X direction
    public int cellYLength = 40;//How many cells long the game is in the Y direction
    public int[] oldCellCords = {-1,-1};//Keeps a record of the last cell the mouse drag was in
    //Constructor
    public CellModel(){
        cells = new boolean[cellXLength][cellYLength];//2D array of cells, each being 20px by 20px (800x800 total)
        randomize();//Randomizes the layout
        addMouseListener(this);//Allows use of the mouse
        addMouseMotionListener(this);//Allows use of mouse motion
        
        //Sets up the JFrame//
        Dimension screensize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();//Sets up JFrame
        int x = screensize.width/2 - WindowSize.width/2;//X bound
        int y = screensize.height/2 - WindowSize.height/2;//Y bound
        setBounds(x,y,WindowSize.width,WindowSize.height);//Sets bounds
        setTitle("Conway's Game of Life");//Sets JFrame title
        setVisible(true);//Shows screen
                
        //Sets up the Option menu//
        JFrame menu = new JFrame("Options");//Creates and sets title of frame
        menu.setLayout(new FlowLayout());//Button Menu Layout
        JButton startButton = new JButton("Start");//Creates Start Button
        JButton stopButton = new JButton("Stop");//Creates Stop Button
        JButton randomButton = new JButton("Random");//Creates Random Button
        JButton wipeButton = new JButton("Wipe");//Creates Wipe Button
        JButton frameButton = new JButton("Frame Advance");//Creates Frame Advance Button
        JButton saveButton = new JButton("Save");//Creates Save Button
        JButton loadButton = new JButton("Load");//Creates Load Button
        menu.add(startButton);//Adds Start Button
        menu.add(stopButton);//Adds Stop Button
        menu.add(randomButton);//Adds Random Button
        menu.add(wipeButton);//Adds Wipe Button
        menu.add(frameButton);//Adds Frame Advance Button
        menu.add(saveButton);//Adds Save Button
        menu.add(loadButton);//Adds Load Button
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Makes menu exit on close
        menu.setSize(163, 200);//Menu Dimensions
        menu.setVisible(true);//Makes menu visible
        
        //Start Button, begins the game in full animation speed
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameInProgress = true;
                frameAdvanceOn = false;
            }
        });
        
        //Stop Button, stops the game animation
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameInProgress = false;
                frameAdvanceOn = false;
            }
        });
        
        //Random Button, randomizes a new game layout
        randomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameInProgress = false;
                frameAdvanceOn = false;
                randomize();
            }
        });
        
        //Wipe Button, wipes game by setting all cells to False
        wipeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameInProgress = false;
                frameAdvanceOn = false;
                cells = new boolean[cellXLength][cellYLength];
                cellsPrevious = new boolean[cellXLength][cellYLength];
            }
        });
        
        //Frame Advance Button, animates the game by a single frame
        frameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameInProgress = true;
                frameAdvanceOn = true;
            }
        });
        
        //Save Button, saves current game layout to game.save
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveFile();
            }
        });
        
        //Load Button, loads game from game.save
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                load();
            }
        });
        
        //Double buffer set up//
        createBufferStrategy(2);
        strategy = getBufferStrategy();
        
        //Thread set up//
        Thread animation = new Thread(this); //Creates thread
        animation.start(); //Runs thread
    }

    //Method for randomizing game layout
    public void randomize(){
        cells = new boolean[cellXLength][cellYLength];
        int i = 0;//Horizontal counter
        int j = 0;//Vertical counter
        int randomState;
        while(true){
            double randomDouble = Math.random();//Generates random double
            randomState = (int)(randomDouble * 10);//Generates ints 0 to 9
            if(randomState ==  0){//Gives 10% chance of cell being active
                cells[i][j] = true;//Makes cell alive
            }
            else{
                cells[i][j] = false;//Keeps cell dead
            }
            i = i+1;
            if(i == cellXLength && j == cellYLength-1){//When i==cellXLength and j==cellYLength-1, all cells have been handled, so break the loop
                break;
            }
            else if(i == cellXLength){//When i=cellXLength, you've hit the end of the row, so moves onto the next one
                i = 0;
                j = j + 1;
            }      
        }
    }
    
    //Main function
    public static void main(String[] args) {
        CellModel model = new CellModel();//Calls constructor
    }
    
    //Ran by the thread
    public void run (){
        while (true){//Infinite Loop while running
            if(gameInProgress == true){//Applies the game rules when the game is running
                conwayRules();//Applies the game rules
                if(frameAdvanceOn == true){//Prevents game animating at full speed when Frame Advance is used
                    gameInProgress = false;
                    frameAdvanceOn = false;
                }
            }
            repaint();//Paints
            try{
                Thread.sleep(50);
            }
            catch(InterruptedException e){ //Handles IE errors
                e.printStackTrace();
            }
        }
    }
    
    //Paint Function
    public void paint(Graphics g) {
        g = strategy.getDrawGraphics();//Part of the Double buffer system
        strategy.show();//Part of the Double buffer system
        int cCounter = 0; //ColumnCounter
        int rCounter = 0; //RowCounter
        g.setColor(Color.BLACK);//Sets colour of background
        g.fillRect(0, 0, 800, 800);//Draws Black background
        g.setColor(Color.WHITE);//Sets colour of "true"/activated cells
        while(rCounter <= cellYLength-1){
            while(cCounter <= cellXLength-1){
                boolean result = cells[cCounter][rCounter];//Checks cell in matrix
                if(result == true){
                    g.fillRect(cCounter*20, rCounter*20, 20, 20);//Paints cell when they are true (activated)
                }
                cCounter = cCounter+1;//Checks next cell on current row
            }
            cCounter = 0;//Resets column search position
            rCounter = rCounter+1;//Moves down to next row
        }
    }
    
    //Translates pixel cords into grid cords
    public int[] getGridCords(int xCord, int yCord){
        //Counters for cells on the screen
        int colSearch = 0;
        int rowSearch = 0;
        
        //Bools for if the correct row/column has been found, doing this prevents constantly rechecking when answer is already found
        boolean rowFound = false;
        boolean colFound = false;
        
        //Stores x and y position of cells (POSITION, not cords, so its 0-cellLength-1 cells, not 0-800 pixels)
        int cellX = 0;
        int cellY = 0;
        while(rowSearch <= cellYLength-1){
            //Cord >= (20*Search)+1 finds the minimum value for certain cell, either on x or y
            //Cord <= (20*(Search+1)) finds the maximum value for certain cell, either on x or y
            //We check if the cord is exactly 0, manually because current min/max equation leaves out 0 on both x and y
            if(xCord == 0 && colFound == false){
                cellX = 0;
                colFound = true;
            }
            else if(xCord >= (20*colSearch)+1 && xCord <= (20*(colSearch+1)) && colFound == false){
                cellX = colSearch;
                colFound = true;
            }
            
            if(yCord == 0 && rowFound == false){
                cellY = 0;
                rowFound = true;
            }
            else if(yCord >= (20*rowSearch)+1 && yCord <= (20*(rowSearch+1)) && rowFound == false){
                cellY = rowSearch;
                rowFound = true;
            }
            
            colSearch = colSearch+1;//Check next column on the current row
            if(colFound == true && rowFound == true){//When cell that was clicked is found, break from the loop
                break;
            }
            if(colSearch == cellXLength){//When we hit the end of the row, reset column search and move down one row
                colSearch = 0;
                rowSearch = rowSearch+1;
            }
        }
        
        int[] gridCords = {cellX,cellY};
        return gridCords;
    }
    
    //Changes the state of the cell at cellX,cellY
    //Turns false if true, and true if false
    public void changeCellState(int cellX, int cellY){
       if(cells[cellX][cellY] == true){//If the cell is activated, then deactivate it
            cells[cellX][cellY] = false;
        }
        else{//Otherwise, activate the cell
            cells[cellX][cellY] = true;
        }        
    }
    
    //Handles Mouse Click
    public void mousePressed(MouseEvent e) { 
        //Gets X and Y cords of the mouse click
        int xCord=e.getX();
        int yCord=e.getY();
        
        int[] activatedCell = getGridCords(xCord,yCord);//Gets cell the mouse was clicked in
        changeCellState(activatedCell[0],activatedCell[1]);//Changse the state of the cell clicked
    }
    
    //Rules for Conway's Game of Life
    public void conwayRules(){
        int livingNeighbours = 0;//Counts how many living neighbours a cell has
        boolean isAlive;//State of the cell
        int i = 0;//Columns
        int j = 0;//Rows
        boolean[][] cellsPrevious = new boolean[cellXLength][cellYLength];//Sets up the array of previous game states
        
        //Copies the current game state array to the previous game state array
        for (int counter = 0; counter < cells.length; counter++) {
            cellsPrevious[counter] = Arrays.copyOf(cells[counter], cells[counter].length);
        }
        
        while(j <= cellYLength-1){//Loops for all rows
            while(i <= cellXLength-1){//Loops for all columns
                isAlive = cellsPrevious[i][j];//Checks state of the cell
                livingNeighbours = 0;//Resets living neighbours counter
                
                //Very very crude method, but it makes handling cells on the edge 
                //easier and its good enough. Should be replaced by a better method 
                //when possible, since this isn't great.
                if(i!=0 && j!=0){//Top Left
                    if(cellsPrevious[i-1][j-1] == true){
                        livingNeighbours = livingNeighbours+1;
                    }
                }
                if(j!=0){//Top
                    if(cellsPrevious[i][j-1] == true){
                        livingNeighbours = livingNeighbours+1;
                    }
                }
                if(i!=cellXLength-1 && j!=0){//Top Right
                    if(cellsPrevious[i+1][j-1] == true){
                        livingNeighbours = livingNeighbours+1;
                    }
                }
                
                if(i!=0){//Left
                    if(cellsPrevious[i-1][j] == true){
                        livingNeighbours = livingNeighbours+1;
                    }
                }
                if(i!=cellXLength-1){//Right
                    if(cellsPrevious[i+1][j] == true){
                        livingNeighbours = livingNeighbours+1;
                    }
                }
                
                if(i!=0 && j!=cellYLength-1){//Bottom Left
                    if(cellsPrevious[i-1][j+1] == true){
                        livingNeighbours = livingNeighbours+1;
                    }
                }
                if(j!=cellYLength-1){//Bottom
                    if(cellsPrevious[i][j+1] == true){
                        livingNeighbours = livingNeighbours+1;
                    }
                }
                if(i!=cellXLength-1 && j!=cellYLength-1){//Bottom Right
                    if(cellsPrevious[i+1][j+1] == true){
                        livingNeighbours = livingNeighbours+1;
                    }
                }
                
                //Underpopulation
                if(isAlive == true && livingNeighbours < 2){
                    cells[i][j] = false;
                }
                
                //Survival
                else if(isAlive == true && (livingNeighbours == 2 || livingNeighbours == 3)){
                    cells[i][j] = true; //Line not necessary (?), included in case the survival rule needs to be editted
                }
                
                //Overpopulation
                else if(isAlive == true && livingNeighbours > 3){
                    cells[i][j] = false;
                }
                
                //Reproduction
                else if(isAlive == false && livingNeighbours == 3){
                    cells[i][j] = true;
                }
                i=i+1;
            }
            //Move to next row, move back to start of the column
            i=0;
            j=j+1;
        }
    }
    
    public void mouseReleased(MouseEvent e) {
        int[] oldCellCords = {-1,-1};//Resets the oldCellCords array
    }
    
    public void mouseEntered(MouseEvent e) { 
    
    }
    
    public void mouseExited(MouseEvent e) { 
    }
    
    public void mouseClicked(MouseEvent e) {
    }
    
    public void mouseDragged(MouseEvent e) {
        //Gets X and Y cords of the mouse loction
        int xCord=e.getX();
        int yCord=e.getY();
        
        //Gets the cords (grid cords, not pixel cords) of the cell the mouse is currently in
        int[] activatedCell = getGridCords(xCord,yCord);
        
        //Checks to ensure the cell who's state will be changed isn't the same tile the mouse was just in
        //This helps to prevent rapid state changing of the same time
        if(activatedCell[0] != oldCellCords[0] || activatedCell[1] != oldCellCords[1]){
            //Saves current cell cords
            oldCellCords[0] = activatedCell[0];
            oldCellCords[1] = activatedCell[1];
            
            changeCellState(activatedCell[0],activatedCell[1]);//Changes the state of the cell
        }
    }
    
    public void mouseMoved(MouseEvent e){
    }
    
    public String getGameBoardState(){
        String boardData = "";//Empty String
        int i = 0;
        int j = 0;
        while(true){
            if(cells[i][j] ==  true){
                boardData = boardData+"1";//Adds a 1 to the board data if the cell is activated
            }
            else{
                boardData = boardData+"0";//Adds a 0 to the board data if the cell is activated
            }
            i = i+1;
            if(i == cellXLength && j == cellYLength-1){//When i==cellXLength and j==cellYLength-1, all cells have been handled, so break the loop
                break;
            }
            else if(i == cellXLength){//When i=cellXLength, you've hit the end of the row, so moves onto the next one
                i = 0;
                j = j+1;
            }      
        }
        return boardData;//Returns the board data
    }
    
    //Saves game from game.save
    public void saveFile(){
        String saveFile = "game.save";//Creates or overwrites game.save in the same directory as CellModel.java

        try {
            File file = new File(saveFile);
            file.createNewFile();
            FileWriter writer = new FileWriter(file);//Allows for writing to the file
            String boardData = getGameBoardState();//Gets the current Board game state
            writer.write(boardData);//Saves board data to game.save
            writer.close();//Close
        } catch (IOException e) {//Handles an IOException Error
            System.out.println("IOException (save)");
            e.printStackTrace();
        }
    }
    
    //Loads game from game.save
    public void load(){
        cells = new boolean[cellXLength][cellYLength];//Empty Cell Grid
        int i = 0;//Horizontal counter
        int j = 0;//Vertical counter
        try {
        String fileName = "game.save";//Read from game.save in the same directory as CellModel.java
        File file = new File(fileName);
        FileReader fileReader = new FileReader(file);//Allows for the reading of the file
        BufferedReader bufferedReader = new BufferedReader(fileReader);//Required for the buffer/reading process
        String line = bufferedReader.readLine();//Retrieves save data
        bufferedReader.close();//Close
        
        int pos = 0;//Current position of the bit being read
        while(true){
            if(line.charAt(pos) == '1'){//If save bit = 1, the cell was activated
                cells[i][j] = true;
            }
            else{//If save bit = 0, the cell was deactivated
                cells[i][j] = false;
            }
            i = i+1;
            pos = pos+1;
            if(i == cellXLength && j == cellYLength-1){//When i==cellXLength and j==cellYLength-1, all cells have been handled, so break the loop
                break;
            }
            else if(i == cellXLength){//When i=cellXLength, you've hit the end of the row, so moves onto the next one
                i = 0;
                j = j + 1;
            }      
        }
        } catch (IOException e) {//Handles IOException errors
                System.out.println("IOException (load)");
                e.printStackTrace();
            }
    }
}
