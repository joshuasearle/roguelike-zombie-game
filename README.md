# Roguelike Zombie Game

This was a university project where we created a text-based, rogue-like zombie game using Java.
The aim of this specific unit was to learn the principles of object orientated design / programming.
For this project, we were provided with a basic game engine, and we had a list of features we need to implement, 
ranging from adding sniper rifles and shotguns, to creating a boss called "Mambo Marie" who would randomly disappear and re-appear.
We were also tasked with implementing our own features for bonus marks, and my group (me and one other person), came up with a Zombie Dog,
that paralyses enemies with its bark, and can only move in chess knight moves.

### Challenges

Some of the challenges we faced were that we weren't allowed to modify the game engine, so we had to figure out ways to add functionality
to the game without altering the game engine, all while maintaining good object orientated design principles such as SOLID and reducing code smells.
Another challenge we faced were merge conflicts on git (we had to host the remote repo on gitlab for marking which is why there is no git log), so my partner
and I had to figure out how to merge code back together, which was a bit difficult as we were both relatively new to using git and repo hosting services.

### Features I Implemented

- Shotguns
  - The player recieved a menu to choose a certain direction to fire
  - Shotgun fired in a cone shape w/ limited range
- Sniper Rifle
  - The player could spend rounds aiming to increase damage / accuracy
  - Could shoot anyone in line of sight
- Zombie Dog
  - Bark that paralyses all enemies
  - Could bite enemies and infect them with the zombie disease
- Zombie bites
  - Had a chance of healing the zombie
  - Had varying hit chance relative to the punch
- Detachable limbs
  - Having the zombies limbs fall off occasionally when they were hit
  - The limbs affected the attacks the zombie did
  - The limbs affected the zombies movement
- Ammo System
  - Random ammo boxes that the player could loot into their inventory
