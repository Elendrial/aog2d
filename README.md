## Preamble
Not currently playable. Still on core systems phase.
Planning to be a 2d, turn based, resource control, strategy game. Based of Armies of Gielinor by Jagex, previously hosted at funorb.com before it's retirement. More information on the game can be found at http://funorb.wikia.com/wiki/Armies_of_Gielinor

This is written entirely in java by myself, and uses my RPGEngine as a base.
An estimated 50% of the 'core' is done.

## TODO:
###### May not contain everything
#### High priority (CORE):
- Player turn systems				(semi done)
- Alignment/god systems     		(mostly done)
- Basic resource system				(semi done * will affect summoning)
- Basic combat (melee/ranged)		(semi done)
- Building capture system			(mostly done)
- Get the engine working properly	(mostly done)

#### Medium priority:
- Placeholder art				(started)
- Multiple map loading
- Complex unit movement systems (movement types/restrictions)	(semi done)
- Complex combat (attack advantage/disadvantage)
- Some simple special abilities

#### Low priority:
- Complex special abilities
- Campaign
- Custom maps dynamically loaded (png?)
- Custom units/summon lists dynamically loaded (json?)
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