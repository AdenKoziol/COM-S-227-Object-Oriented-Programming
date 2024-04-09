package hw3;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

import api.ScoreUpdateListener;
import api.ShowDialogListener;
import api.Tile;

/**
 * Class that models a game.
 */
public class ConnectGame {
	/** @author Aden Koziol*/
	private ShowDialogListener dialogListener;
	private ScoreUpdateListener scoreListener;
	private Grid gameGrid; /**my grid*/
	private int maxTile; /**max tile*/
	private int minTile; /** min tile */
	private long score = 0; /** score */
	private Random rand1; /**random object */
	private Tile tile1; /**tile used in many methods for making new tiles*/
	private boolean selected = false; /**used to check if try first select already selected a file */
	private int numSelected = 0; /**number of tiles selected so far */
	private ArrayList<Tile> tilesSelected = new ArrayList<Tile>(); /**Array list of the tiles that were selected */
	private Tile[] tileArr; /**array version of array list */
	private boolean alreadySelected; /**used to check if tiles were already selected */
	
	/**
	 * Constructs a new ConnectGame object with given grid dimensions and minimum
	 * and maximum tile levels.
	 * 
	 * @param width  grid width
	 * @param height grid height
	 * @param min    minimum tile level
	 * @param max    maximum tile level
	 * @param rand   random number generator
	 */
	public ConnectGame(int width, int height, int min, int max, Random rand) 
	{
		gameGrid = new Grid(width, height);
		maxTile = max;
		minTile = min;
		rand1 = rand;
	}

	/**
	 * Gets a random tile with level between minimum tile level inclusive and
	 * maximum tile level exclusive. For example, if minimum is 1 and maximum is 4,
	 * the random tile can be either 1, 2, or 3.
	 * <p>
	 * DO NOT RETURN TILES WITH MAXIMUM LEVEL
	 * 
	 * @return a tile with random level between minimum inclusive and maximum
	 *         exclusive
	 */
	public Tile getRandomTile() 
	{
		return new Tile(rand1.nextInt(maxTile - minTile) + minTile);
	}

	/**
	 * Regenerates the grid with all random tiles produced by getRandomTile().
	 */
	public void radomizeTiles() 
	{
		for(int i = 0; i < gameGrid.getWidth(); i++)
		{
			for(int j = 0; j < gameGrid.getHeight(); j++)
			{
				gameGrid.setTile(getRandomTile(), i, j);
			}
		}
	}

	/**
	 * Determines if two tiles are adjacent to each other. The may be next to each
	 * other horizontally, vertically, or diagonally.
	 * 
	 * @param t1 one of the two tiles
	 * @param t2 one of the two tiles
	 * @return true if they are next to each other horizontally, vertically, or
	 *         diagonally on the grid, false otherwise
	 */
	public boolean isAdjacent(Tile t1, Tile t2) 
	{
		if(t1.getX() - 1 == t2.getX())
		{
			if(t1.getY() == t2.getY())
				return true;
			else if(t1.getY() - 1 == t2.getY())
				return true;
			else if(t1.getY()+ 1 == t2.getY())
				return true;
			else
				return false;
		}
		else if(t1.getX() + 1 == t2.getX())
		{
			if(t1.getY() == t2.getY())
				return true;
			else if(t1.getY() - 1 == t2.getY())
				return true;
			else if(t1.getY()+ 1 == t2.getY())
				return true;
			else
				return false;
		}
		else if(t1.getX() == t2.getX())
		{
			if(t1.getY() - 1 == t2.getY())
				return true;
			else if(t1.getY()+ 1 == t2.getY())
				return true;
			else
				return false;
		}
		else
			return false;
	}

	/**
	 * Indicates the user is trying to select (clicked on) a tile to start a new
	 * selection of tiles.
	 * <p>
	 * If a selection of tiles is already in progress, the method should do nothing
	 * and return false.
	 * <p>
	 * If a selection is not already in progress (this is the first tile selected),
	 * then start a new selection of tiles and return true.
	 * 
	 * @param x the column of the tile selected
	 * @param y the row of the tile selected
	 * @return true if this is the first tile selected, otherwise false
	 */
	public boolean tryFirstSelect(int x, int y) 
	{
		if(selected == true)
		{
			return false;
		}
		else
		{
			tilesSelected.add(gameGrid.getTile(x, y));
			gameGrid.getTile(x, y).setSelect(true);
			numSelected = 1;
			selected = true;
			return true;
		}
	}

	/**
	 * Indicates the user is trying to select (mouse over) a tile to add to the
	 * selected sequence of tiles. The rules of a sequence of tiles are:
	 * 
	 * <pre>
	 * 1. The first two tiles must have the same level.
	 * 2. After the first two, each tile must have the same level or one greater than the level of the previous tile.
	 * </pre>
	 * 
	 * For example, given the sequence: 1, 1, 2, 2, 2, 3. The next selected tile
	 * could be a 3 or a 4. If the use tries to select an invalid tile, the method
	 * should do nothing. If the user selects a valid tile, the tile should be added
	 * to the list of selected tiles.
	 * 
	 * @param x the column of the tile selected
	 * @param y the row of the tile selected
	 */
	public void tryContinueSelect(int x, int y) 
	{
		alreadySelected = false;
		int goBack = -1;
		
		for(int i = 0; i < tilesSelected.size(); i++)
		{
			if(tilesSelected.get(i) == gameGrid.getTile(x, y))
			{
				goBack = i;
				alreadySelected = true;
				break;
			}
		}
		if(goBack != -1)
		{
			for(int i = tilesSelected.size() - 1; i > goBack; i--)
			{
				unselect(tilesSelected.get(i).getX(), tilesSelected.get(i).getY());
			}
			for(int i = tilesSelected.size() - 1; i > goBack; i--)
			{
				unselect(tilesSelected.get(i).getX(), tilesSelected.get(i).getY());
				tilesSelected.remove(i);
			}
		}
		if(numSelected == 1 && alreadySelected == false)
		{
			if(isAdjacent(tilesSelected.get(0), gameGrid.getTile(x, y)))
			{
				if(tilesSelected.get(0).getLevel() == gameGrid.getTile(x, y).getLevel())
				{
					gameGrid.getTile(x, y).setSelect(true);
					tilesSelected.add(gameGrid.getTile(x, y));
					numSelected++;
				}
			}
		}
		else if(numSelected > 1 && alreadySelected == false)
		{
			if(isAdjacent(tilesSelected.get(tilesSelected.size() - 1), gameGrid.getTile(x, y)))
			{
				if(tilesSelected.get(tilesSelected.size() - 1).getLevel() == gameGrid.getTile(x, y).getLevel() || tilesSelected.get(tilesSelected.size() - 1).getLevel() == gameGrid.getTile(x, y).getLevel() - 1)
				{
					gameGrid.getTile(x, y).setSelect(true);
					tilesSelected.add(gameGrid.getTile(x, y));
					numSelected++;
				}
			}
		}
	}

	/**
	 * Indicates the user is trying to finish selecting (click on) a sequence of
	 * tiles. If the method is not called for the last selected tile, it should do
	 * nothing and return false. Otherwise it should do the following:
	 * 
	 * <pre>
	 * 1. When the selection contains only 1 tile reset the selection and make sure all tiles selected is set to false.
	 * 2. When the selection contains more than one block:
	 *     a. Upgrade the last selected tiles with upgradeLastSelectedTile().
	 *     b. Drop all other selected tiles with dropSelected().
	 *     c. Reset the selection and make sure all tiles selected is set to false.
	 * </pre>
	 * 
	 * @param x the column of the tile selected
	 * @param y the row of the tile selected
	 * @return return false if the tile was not selected, otherwise return true
	 */
	public boolean tryFinishSelection(int x, int y) 
	{
		if(tilesSelected.size() == 1)
		{
			unselect(x,y);
			return true;
		}
		if(gameGrid.getTile(x, y).getLevel() >= tilesSelected.get(tilesSelected.size() - 1).getLevel())
		{
			for(int i = 0; i < tilesSelected.size(); i++)
				score += Math.pow(2, tilesSelected.get(i).getLevel());
			upgradeLastSelectedTile();
			return true;
		}
		return false;
	}

	/**
	 * Increases the level of the last selected tile by 1 and removes that tile from
	 * the list of selected tiles. The tile itself should be set to unselected.
	 * <p>
	 * If the upgrade results in a tile that is greater than the current maximum
	 * tile level, both the minimum and maximum tile level are increased by 1. A
	 * message dialog should also be displayed with the message "New block 32,
	 * removing blocks 2". Not that the message shows tile values and not levels.
	 * Display a message is performed with dialogListener.showDialog("Hello,
	 * World!");
	 */
	public void upgradeLastSelectedTile() 
	{
		tilesSelected.get(tilesSelected.size() - 1).setLevel(tilesSelected.get(tilesSelected.size() - 1).getLevel() + 1);
		tilesSelected.get(tilesSelected.size() - 1).setSelect(false);
		if(tilesSelected.get(tilesSelected.size() - 1).getLevel() > getMaxTileLevel())
		{
			dialogListener.showDialog("New block " + Math.pow(2, maxTile) + ", removing blocks " + Math.pow(2, minTile - 1));
			maxTile++;
			minTile++;
		}
		tilesSelected.remove(tilesSelected.size() - 1);
		dropSelected();
		dropLevel(minTile);
	}

	/**
	 * Gets the selected tiles in the form of an array. This does not mean selected
	 * tiles must be stored in this class as a array.
	 * 
	 * @return the selected tiles in the form of an array
	 */
	public Tile[] getSelectedAsArray() 
	{
		tileArr = new Tile[tilesSelected.size()];
		for(int i = 0; i < tilesSelected.size(); i++)
			tileArr[i] = tilesSelected.get(i);
		return tileArr;
	}

	/**
	 * Removes all tiles of a particular level from the grid. When a tile is
	 * removed, the tiles above it drop down one spot and a new random tile is
	 * placed at the top of the grid.
	 * 
	 * @param level the level of tile to remove
	 */
	public void dropLevel(int level) 
	{
		for(int i = 0; i < gameGrid.getWidth(); i++)
		{
			for(int j = 0; j < gameGrid.getHeight(); j++)
			{
				if(gameGrid.getTile(i,j).getLevel() == level)
				{
					unselect(i,j);
					for(int k = j; k > 0; k--)
					{
						gameGrid.getTile(i,k).setLevel(gameGrid.getTile(i, k - 1).getLevel());
					}
					gameGrid.setTile(getRandomTile(), i, 0);
					gameGrid.getTile(i, 0).setLocation(i, 0);
				}
			}
		}
	}

	/**
	 * Removes all selected tiles from the grid. When a tile is removed, the tiles
	 * above it drop down one spot and a new random tile is placed at the top of the
	 * grid.
	 */
	public void dropSelected() 
	{
		int x;
		int y;
		for(int i = 0; i < tilesSelected.size(); i++)
		{
			x = tilesSelected.get(i).getX();
			y = tilesSelected.get(i).getY();
			for(int j = y; j > 0; j--)
			{
				gameGrid.getTile(x, j).setLevel(gameGrid.getTile(x, j - 1).getLevel());
			}
			gameGrid.setTile(getRandomTile(), x, 0);
			gameGrid.getTile(x, 0).setLocation(x, 0);
		}
		for(int i = 0; i < tilesSelected.size(); i++)
			tilesSelected.get(i).setSelect(false);
		while(tilesSelected.size() > 0)
			tilesSelected.remove(tilesSelected.size() - 1);
	}

	/**
	 * Remove the tile from the selected tiles.
	 * 
	 * @param x column of the tile
	 * @param y row of the tile
	 */
	public void unselect(int x, int y) 
	{
		for(int i = 0; i < tilesSelected.size(); i++)
		{
			if(tilesSelected.get(i) == gameGrid.getTile(x, y))
			{
				tilesSelected.remove(i);
				break;
			}
		}
		gameGrid.getTile(x, y).setSelect(false);
	}

	/**
	 * Gets the player's score.
	 * 
	 * @return the score
	 */
	public long getScore() {
		// TODO
		return score;
	}

	/**
	 * Gets the game grid.
	 * 
	 * @return the grid
	 */
	public Grid getGrid() {
		// TODO
		return gameGrid;
	}

	/**
	 * Gets the minimum tile level.
	 * 
	 * @return the minimum tile level
	 */
	public int getMinTileLevel() {
		// TODO
		return minTile;
	}

	/**
	 * Gets the maximum tile level.
	 * 
	 * @return the maximum tile level
	 */
	public int getMaxTileLevel() {
		// TODO
		return maxTile;
	}

	/**
	 * Sets the player's score.
	 * 
	 * @param score number of points
	 */
	public void setScore(long score) 
	{
		this.score = score;
	}

	/**
	 * Sets the game's grid.
	 * 
	 * @param grid game's grid
	 */
	public void setGrid(Grid grid) 
	{
		// TODO
		gameGrid = grid;
	}

	/**
	 * Sets the minimum tile level.
	 * 
	 * @param minTileLevel the lowest level tile
	 */
	public void setMinTileLevel(int minTileLevel) 
	{
		minTile = minTileLevel;
	}

	/**
	 * Sets the maximum tile level.
	 * 
	 * @param maxTileLevel the highest level tile
	 */
	public void setMaxTileLevel(int maxTileLevel) 
	{
		maxTile = maxTileLevel;
	}

	/**
	 * Sets callback listeners for game events.
	 * 
	 * @param dialogListener listener for creating a user dialog
	 * @param scoreListener  listener for updating the player's score
	 */
	public void setListeners(ShowDialogListener dialogListener, ScoreUpdateListener scoreListener) 
	{
		this.dialogListener = dialogListener;
		this.scoreListener = scoreListener;
	}

	/**
	 * Save the game to the given file path.
	 * 
	 * @param filePath location of file to save
	 */
	public void save(String filePath) {
		GameFileUtil.save(filePath, this);
	}

	/**
	 * Load the game from the given file path
	 * 
	 * @param filePath location of file to load
	 * @throws FileNotFoundException 
	 */
	public void load(String filePath) throws FileNotFoundException {
		GameFileUtil.load(filePath, this);
	}
}
