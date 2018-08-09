Planning to be a 2d, turn based, resource control, strategy game. Based of Armies of Gielinor by Jagex, previously hosted at funorb.com before it's retirement. More information on the game can be found at http://funorb.wikia.com/wiki/Armies_of_Gielinor .

This is currently written entirely in java by myself, uses my RPGEngine as a base, and has estimated 5% of the 'core' done.
(may find I need to port to Gridbased Engine if RPGEngine is too buggy)

##TODO:

#### High priority (CORE):
- Player turn systems
- Alignment/god systems      (semi done)
- Basic unit movement systems
- Unit summoning systems
- Basic resource system
- Basic combat (melee/ranged)
- Building capture system

#### Medium priority:
- Placeholder art
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



##### List of tiles left to include:
- All Structures, ie:
   - Portal (high)
   - Village(high)
   - Tower
   - Temple
   
- All Environment, ie:
   - Normal Tiles(high)
   - Swamp
   - Forest
   - Hills
   - Mountains
   - Path/Bridge
   - River/Sea
   - Wall/Standing Stone


## Notes:
- Structure tiles are ones which can be captured for points, environment are anything else. (ie: walls are environment)
- A menu is still a 'level', just a highly specialised one comprising of only GUIs.