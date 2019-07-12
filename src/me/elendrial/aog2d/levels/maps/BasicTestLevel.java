package me.elendrial.aog2d.levels.maps;

import me.elendrial.aog2d.gameSystems.players.Player;
import me.elendrial.aog2d.levels.AoGLevel;
import me.elendrial.aog2d.objects.tiles.environment.ForestTile;
import me.elendrial.aog2d.objects.tiles.environment.MountainTile;
import me.elendrial.aog2d.objects.tiles.environment.OpenTile;
import me.elendrial.aog2d.objects.tiles.environment.OutsideWallTile;
import me.elendrial.aog2d.objects.tiles.environment.RiverTile;
import me.elendrial.aog2d.objects.tiles.environment.RoadTile;
import me.elendrial.aog2d.objects.tiles.environment.SeaTile;
import me.elendrial.aog2d.objects.tiles.environment.SwampTile;
import me.elendrial.aog2d.objects.tiles.environment.WallTile;
import me.elendrial.aog2d.objects.tiles.structure.PortalTile;
import me.elendrial.aog2d.objects.tiles.structure.TempleTile;
import me.elendrial.aog2d.objects.tiles.structure.TowerTile;
import me.elendrial.aog2d.objects.tiles.structure.VillageTile;
import me.elendrial.aog2d.objects.units.neutral.BarbarianUnit;

public class BasicTestLevel extends AoGLevel{

	public BasicTestLevel(Player[] p) {
		super(p);
		this.getTileGrid().setDimensions(25);

		this.getTileGrid().fillDimensionsWith(0, 0, getTileGrid().getWidth(), getTileGrid().getHeight(), OutsideWallTile.class); // TODO: Create wallDimensionsWith() in Grid
		this.getTileGrid().fillDimensionsWith(1, 1, getTileGrid().getWidth()-1, getTileGrid().getHeight()-1, OpenTile.class);
		this.getTileGrid().fillDimensionsWith(6, 6, 20, 7, RoadTile.class);
		
		PortalTile pt = new PortalTile();
		pt.capturedBy = p[0];
		this.getTileGrid().setObjectAt(5, 6, pt);
		
		PortalTile pt2= new PortalTile();
		pt2.capturedBy = p[1];
		this.getTileGrid().setObjectAt(20, 20, pt2);
		
		// Testing every tile type so far unused.
		this.getTileGrid().setObjectAt(10, 7, new RiverTile());
		this.getTileGrid().setObjectAt(11, 7, new SeaTile());
		this.getTileGrid().setObjectAt(9, 9, new ForestTile());
		this.getTileGrid().setObjectAt(4, 12, new MountainTile());
		this.getTileGrid().setObjectAt(9, 7, new SwampTile());
		
		this.getTileGrid().setObjectAt(3, 7, new VillageTile());
		this.getTileGrid().setObjectAt(14, 3, new TempleTile());
		this.getTileGrid().setObjectAt(5, 17, new TowerTile());
		

		// Walls
		this.getTileGrid().setObjectAt(15, 18, new WallTile().setDirection(0)); // Horizontal
		this.getTileGrid().setObjectAt(16, 18, new WallTile().setDirection(4)); // L->D
		this.getTileGrid().setObjectAt(16, 19, new WallTile().setDirection(1)); // Vertical
		this.getTileGrid().setObjectAt(16, 20, new WallTile().setDirection(2)); // L->U
		this.getTileGrid().setObjectAt(15, 20, new WallTile().setDirection(5)); // R->D
		this.getTileGrid().setObjectAt(15, 21, new WallTile().setDirection(3)); // U->R
		
		
		
		BarbarianUnit u = new BarbarianUnit();
		u.setGridPosition(8, 6);
		u.setPlayer(p[1]);
		addEntity(u);
		u.onSummon();
	}

}
