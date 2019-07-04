## Preamble
Not currently playable. Still on core systems phase.
Planning to be a 2d, turn based, resource control, strategy game. Based of Armies of Gielinor by Jagex, previously hosted at funorb.com before it's retirement. More information on the game can be found at http://funorb.wikia.com/wiki/Armies_of_Gielinor

This is written entirely in java by myself, and uses my RPGEngine as a base.
An estimated 90% of the 'core' is done.

## TODO:
###### May not contain everything
#### High priority (CORE):
- Player turn systems				(done)
- Alignment/god systems     		(mostly done)
- Basic resource system				(done)
- Basic combat (melee/ranged)		(almost done, with intent to improve visuals of)
- Building capture system			(done)
- Get the engine working properly	(never done but pretending it is)

#### Medium priority:
- Placeholder art												(started)
- Multiple map loading
- Complex unit movement systems (movement types/restrictions)	(started - done as new tiles are added)
- Complex combat - attack advantage/disadvantage				(mostly done)
- Complex combat #2 - tiles blocking ranged attacks
- Some simple special abilities

#### Low priority:
- Complex special abilities
- Campaign
- Custom maps dynamically loaded (png?)
- Custom units/summon lists dynamically loaded (json?)
	- This would mean the way units are currently summoned/stored/referenced would likely have to change from using newInstance() on the class, to storing a UnitBuilder
- Multiple tilesets

#### Very Low priority:
- Proper Art
- Multiplayer (will require semi-rewrite into server-client pairs)
- Custom ruleset dynamically loaded (json?)
- Turn timers



##### List of tiles left to include:
   
- All Environment, ie:
   - Swamp
   - Hills
   - Mountains
   - Bridge
   - River/Sea
   - Wall/Standing Stone


## Notes:
- Structure tiles are ones which can be captured for points, environment are anything else. (ie: walls are environment)
- A menu is still a 'level', just a highly specialised one comprising of only GUIs.
- Maybe make a tile builder, so I don't end up with ~10 classes, each with only 5 lines of code? (currently not doing this)