## Preamble
Playable. Core systems now complete, single player, menus, and non local multiplayer are currently incomplete.
A 2d, turn based, resource control, strategy game. Based of Armies of Gielinor by Jagex, previously hosted at funorb.com before it's retirement. More information on the game can be found at http://funorb.wikia.com/wiki/Armies_of_Gielinor

This is written entirely in java by myself, and uses my RPGEngine as a base.

## TODO:
###### May not contain everything
#### High priority (CORE):
- Player turn systems				(done)
- Alignment/god systems     		(done)
- Basic resource system				(done)
- Basic combat (melee/ranged)		(almost done, with intent to improve visuals of)
- Building capture system			(done)
- Get the engine working properly	(never done but pretending it is)

#### Medium priority:
- Placeholder art												(started)
- Multiple map loading											(support for)
- Complex unit movement systems (movement types/restrictions)	(started - done as new tiles are added)
- Complex combat - attack advantage/disadvantage				(mostly done)
- Complex combat #2 - tiles blocking ranged attacks
- Complex unit subtypes - eg: amphibious, air intercepting
- Some simple special abilities									(done afaik)
- KBD and allowing only one of them to be summoned.

#### Low priority:
- Complex special abilities										(needs onAction())
- Campaign
- Custom maps dynamically loaded (png?)							(simple version working)
- Custom units/summon lists dynamically loaded (json?)
	- This would mean the way units are currently summoned/stored/referenced would likely have to change from using newInstance() on the class, to storing a UnitBuilder
- Multiple tilesets												(simple support working)
- Sound
- Tooltips

#### Very Low priority:
- Proper Art
- Multiplayer (will require semi-rewrite into server-client pairs)
- Custom ruleset dynamically loaded (json?)
- Turn timers


## Notes:
- Structure tiles are ones which can be captured for points, environment are anything else. (ie: walls are environment)
- A menu is still a 'level', just a highly specialised one comprising of only GUIs.