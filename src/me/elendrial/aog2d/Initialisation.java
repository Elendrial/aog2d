package me.elendrial.aog2d;

import java.awt.Color;

import me.elendrial.aog2d.gameSystems.UnitType;
import me.elendrial.aog2d.gameSystems.gods.God;
import me.elendrial.aog2d.gameSystems.players.Player;
import me.elendrial.aog2d.gameSystems.players.builders.StandardPlayerBuilder;
import me.elendrial.aog2d.levels.AoGLevel;
import me.elendrial.aog2d.levels.maps.AoGLevelBuilder;
import me.elendrial.aog2d.objects.tiles.environment.*;
import me.elendrial.aog2d.objects.tiles.structure.*;

import me.elendrial.aog2d.objects.units.guthix.*;
import me.elendrial.aog2d.objects.units.neutral.*;
import me.elendrial.aog2d.objects.units.saradomin.*;
import me.elendrial.aog2d.objects.units.zamorak.*;

import me.hii488.controllers.InitialisationController;
import me.hii488.handlers.LevelHandler;
import me.hii488.interfaces.IInitialiser;
import me.hii488.registries.EntityRegistry;
import me.hii488.registries.TextureRegistry;
import me.hii488.registries.TileRegistry;

public class Initialisation implements IInitialiser{

	public static void setup() {
		InitialisationController.addInitialiser(new Initialisation());
	}
	
	
	
	
	@Override
	public void preInit() {
		registerTiles();
		registerEntities();
		
		alignUnits();
		
		UnitType.init();
	}
	
	public void registerTiles() {
		// 'Environment' Tiles
		TileRegistry.registerTile(new OutsideWallTile());
		TileRegistry.registerTile(new OpenTile());
		TileRegistry.registerTile(new RoadTile());
		TileRegistry.registerTile(new SwampTile());
		TileRegistry.registerTile(new RiverTile());
		TileRegistry.registerTile(new SeaTile());
		TileRegistry.registerTile(new ForestTile());
		TileRegistry.registerTile(new MountainTile());
		TileRegistry.registerTile(new WallTile());
		
		// 'Structure' Tiles
		TileRegistry.registerTile(new PortalTile());
		TileRegistry.registerTile(new VillageTile());
		TileRegistry.registerTile(new TowerTile());
		TileRegistry.registerTile(new TempleTile());
	}
	
	public void registerEntities() {
		EntityRegistry.registerEntity(new BarbarianUnit());
		EntityRegistry.registerEntity(new BarbarianChieftainUnit());
		EntityRegistry.registerEntity(new BarbarianSpearUnit());
	}
	
	public void alignUnits() {
		// Setup God/Unit affiliations
		
		God.NEUTRAL.addUnit(BarbarianUnit.class);
		God.NEUTRAL.addUnit(BarbarianChieftainUnit.class);
		God.NEUTRAL.addUnit(BarbarianSpearUnit.class);
		God.NEUTRAL.addUnit(GorillaUnit.class);
		God.NEUTRAL.addUnit(AviansieUnit.class);
		God.NEUTRAL.addUnit(DagannothUnit.class);
		God.NEUTRAL.addUnit(PortalMageUnit.class);
		God.NEUTRAL.addUnit(TzhaarKetUnit.class);
		God.NEUTRAL.sortUnits();
		
		God.SARADOMIN.addUnit(CentaurUnit.class);
		God.SARADOMIN.addUnit(IcyeneUnit.class);
		God.SARADOMIN.addUnit(LionUnit.class);
		God.SARADOMIN.addUnit(MonkUnit.class);
		God.SARADOMIN.addUnit(PaladinUnit.class);
		God.SARADOMIN.addUnit(PriestUnit.class);
		God.SARADOMIN.addUnit(SaradominMageUnit.class);
		God.SARADOMIN.addUnit(SaradominRangerUnit.class);
		God.SARADOMIN.addUnit(UnicornUnit.class);
		God.SARADOMIN.addUnit(WhiteKnightUnit.class);
		God.SARADOMIN.sortUnits();
		
		God.ZAMORAK.addUnit(BlackKnightUnit.class);
		God.ZAMORAK.addUnit(GargoyleUnit.class);
		God.ZAMORAK.addUnit(GreaterDemonUnit.class);
		God.ZAMORAK.addUnit(HellhoundUnit.class);
		God.ZAMORAK.addUnit(NecromancerUnit.class);
		God.ZAMORAK.addUnit(PyrelordUnit.class);
		God.ZAMORAK.addUnit(SeaSlugUnit.class);
		God.ZAMORAK.addUnit(SkeletonUnit.class);
		God.ZAMORAK.addUnit(WerewolfUnit.class);
		God.ZAMORAK.addUnit(ZamorakMageUnit.class);
		God.ZAMORAK.addUnit(ZamorakRangerUnit.class);
		God.ZAMORAK.sortUnits();
		
		God.GUTHIX.addUnit(BattleTortoiseUnit.class);
		God.GUTHIX.addUnit(BlackGuardDwarfUnit.class);
		God.GUTHIX.addUnit(DruidUnit.class);
		God.GUTHIX.addUnit(DwarfCannonCannonUnit.class);
		God.GUTHIX.addUnit(DwarfCannonUnit.class);
		God.GUTHIX.addUnit(EntUnit.class);
		God.GUTHIX.addUnit(GnomecopterUnit.class);
		God.GUTHIX.addUnit(JadeVineUnit.class);
		God.GUTHIX.addUnit(MossGiantUnit.class);
		God.GUTHIX.addUnit(MountedTerrorbirdUnit.class);
		God.GUTHIX.addUnit(VoidKnightUnit.class);
		God.GUTHIX.addUnit(WolfUnit.class);
		God.GUTHIX.sortUnits();
	}

	@Override
	public void init() {
		initExtraTextures();
		// TODO: Add menus
		
		// Temporary setup, AoGLevel should be more dynamically created from a menu with options etc.
		Player p1 = new StandardPlayerBuilder().setColor(Color.RED).build();
		Player p2 = new StandardPlayerBuilder().setColor(Color.BLUE).build();
		LevelHandler.addLevel(new AoGLevelBuilder().setFileLocation("textures/levels/misthalin_small_1.png").setPlayers(new Player[] {p1,p2}).build(), "aogLevel");
	}
	
	public void initExtraTextures() {
		TextureRegistry.addTexture("textures/gui/tileOverlay/attackOverlay.png", "attackOverlay", 1);
		TextureRegistry.addTexture("textures/gui/tileOverlay/moveOverlay.png", "moveOverlay", 1);
	}

	@Override
	public void postInit() {
		LevelHandler.loadLevel("aogLevel");
	}

}
