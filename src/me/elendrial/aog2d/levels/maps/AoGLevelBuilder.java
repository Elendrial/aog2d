package me.elendrial.aog2d.levels.maps;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.util.ArrayList;

import me.elendrial.aog2d.gameSystems.players.Player;
import me.elendrial.aog2d.levels.AoGLevel;
import me.elendrial.aog2d.objects.tiles.AoGTile;
import me.elendrial.aog2d.objects.tiles.environment.ForestTile;
import me.elendrial.aog2d.objects.tiles.environment.MountainTile;
import me.elendrial.aog2d.objects.tiles.environment.OpenTile;
import me.elendrial.aog2d.objects.tiles.environment.OutsideWallTile;
import me.elendrial.aog2d.objects.tiles.environment.RiverTile;
import me.elendrial.aog2d.objects.tiles.environment.RoadTile;
import me.elendrial.aog2d.objects.tiles.environment.SeaTile;
import me.elendrial.aog2d.objects.tiles.environment.SwampTile;
import me.elendrial.aog2d.objects.tiles.structure.PortalTile;
import me.elendrial.aog2d.objects.tiles.structure.TempleTile;
import me.elendrial.aog2d.objects.tiles.structure.TowerTile;
import me.elendrial.aog2d.objects.tiles.structure.VillageTile;
import me.elendrial.aog2d.objects.units.neutral.BarbarianChieftainUnit;
import me.hii488.dataTypes.Grid;
import me.hii488.dataTypes.Vector;
import me.hii488.gameObjects.entities.GridEntity;
import me.hii488.gameObjects.tiles.BaseTile;
import me.hii488.handlers.FileHandler;

// 50/50 on this being a thing - I'm just not sure it's needed at all. It does help separate out the creation code from the level itself, which is nice ig.
public class AoGLevelBuilder {
	
	private String fileLocation;
	private Player[] players;
	
	
	
	public AoGLevel build() {
		AoGLevel level = new AoGLevel(players);
		
		convertImageToMap(level);

		return level;
	}
	
	/**
	 * Code for decomposing the image comes from https://stackoverflow.com/a/9470843/3444121
	 * @param level
	 */
	private void convertImageToMap(AoGLevel level) {
		BufferedImage image = FileHandler.loadImage(fileLocation);
		Grid<BaseTile> tGrid = level.getTileGrid();
		Grid<GridEntity> eGrid = level.getEntityGrid();
		
		byte[] pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
		final int width = image.getWidth();
		final int height = image.getHeight();
		final boolean hasAlphaChannel = image.getAlphaRaster() != null;
		
		tGrid.setDimensions(width+2, height+2);
		tGrid.wallDimensionsWith(0, 0, width+1, height+1, OutsideWallTile.class);
		
		ArrayList<Vector> startingPoints = new ArrayList<Vector>();
		
		int[][] result = new int[height][width];
		int pixelLength = hasAlphaChannel ? 4 : 3;
		int offset = hasAlphaChannel ? 1 : 0;
		
		for (int pixel = 0, row = 0, col = 0; pixel + pixelLength < pixels.length; pixel += pixelLength) {
			
			int blue  = (int) pixels[pixel + offset] & 0xff;
			int green = (int) pixels[pixel + offset + 1] & 0xff;
			int red   = (int) pixels[pixel + offset + 2] & 0xff;
			
			// Green :: Tiles
			// Red   :: Entities + "starting points" (ie: chief barbarian spawns)
			// Blue  :: Misc
			
			AoGTile tile;
			switch(green) {
			//Environment Tiles
			case 30: tile = new SeaTile();		break;
			case 35: tile = new RiverTile();	break;
			case 40: tile = new SwampTile();	break;
			case 50: tile = new ForestTile();	break;
			case 60: tile = new MountainTile();	break;
			case 100: tile = new RoadTile();	break;
			
			//Walls
			//TODO (fuck this rn)
			
			//Structure Tiles
			case 200: tile = new VillageTile();	break;
			case 210: tile = new TowerTile();	break;
			case 220: tile = new TempleTile();	break;
			case 230: tile = new PortalTile();	break;
			
			default: tile = new OpenTile(); 	break;
			}
			
			switch(red) {
			case 255: // Starting point
				startingPoints.add(new Vector(col+1, row+1));
				break;
			}
			
			// for blue: thinking of each group of bits setting something.
			// eg: 0: standard, 1: desert, 2: snow
			tile.setTileSet(blue & 0x03);
			
			
			tGrid.setObjectAt(col+1,  row+1, tile);
			
			col++;
			if (col == width) {
				col = 0;
				row++;
			}
		}
		
		for(int i = 0; i < startingPoints.size() && i < players.length; i++) {
			BarbarianChieftainUnit bc = new BarbarianChieftainUnit();
			bc.setGridPosition(startingPoints.get(i));
			bc.setPlayer(players[i]);
			level.addEntity(bc);
			bc.onSummon();
		}
		
	}

	public String getFileLocation() {
		return fileLocation;
	}

	public AoGLevelBuilder setFileLocation(String fileLocation) {
		this.fileLocation = fileLocation;
		return this;
	}

	public Player[] getPlayers() {
		return players;
	}

	public AoGLevelBuilder setPlayers(Player[] players) {
		this.players = players;
		return this;
	}
	
	
	
}
