## Preamble
Not currently playable. Still on core systems phase.
Planning to be a 2d, turn based, resource control, strategy game. Based of Armies of Gielinor by Jagex, previously hosted at funorb.com before it's retirement. More information on the game can be found at http://funorb.wikia.com/wiki/Armies_of_Gielinor

This is written entirely in java by myself, and uses my RPGEngine as a base.
An estimated 20% of the 'core' is done.

## TODO:
###### May not contain everything
#### High priority (CORE):
- Player turn systems			(semi done)
- Alignment/god systems     	(semi done)
- Basic unit movement systems
- Unit summoning systems
- Basic resource system			(semi done)
- Basic combat (melee/ranged)
- Building capture system		(started)
- Get the engine working properly (started)

#### Medium priority:
- Placeholder art				(started)
- Multiple map loading
- Complex unit movement systems (movement types/restrictions)
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
- All Structures, ie:
   - Portal (high)
   - Village(high)
   - Tower
   - Temple
   
- All Environment, ie:
   - Swamp
   - Forest
   - Hills
   - Mountains
   - Path/Bridge
   - River/Sea
   - Wall/Standing Stone


## Notes:
####### (for myself as much as others)
- Structure tiles are ones which can be captured for points, environment are anything else. (ie: walls are environment)
- A menu is still a 'level', just a highly specialised one comprising of only GUIs.
- Maybe make a tile builder, so I don't end up with ~10 classes, each with only 5 lines of code?