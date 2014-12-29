## bad-vibes

### Introduction

Bad Vibes is a mobile app sporting gamified education to teach etiquette and values for Filipinos

> De La Salle University - College of Computer Studies
> Team Name - Umayos Ka Productions
> Members - Darren Sapalo, Michael Ong, Bervyn Co, Marit Escalona, Danielle Consignado

*The repository currently has the old Java version that we made during our MOBICOM class and a new folder called "TrainRush" that uses some game engine and will be released on Android, iOS, and Windows platforms.*

### How to build (on Windows)

First, do a `git clone --recursive https://github.com/darrensapalo/bad-vibes.git` the `--recursive` option is there so that the SnowOwl: submodule will also be included on clone.

After cloning, the contents under `/TrainRush` would be like this:

* Resources
* SnowOwl
* TrainRush
* TrainRush.sln

Open a command prompt under **administrator** mode and `cd` to the location where you cloned TrainRush. After that type this in the command prompt:

`mklink /d "Supplementary" "SnowOwl\Trunk\Supplementary"`
`cd Resources`
`mklink /d "Common" "..\SnowOwl\Trunk\Resources\Common"`
`mklink /d "Engine" "..\SnowOwl\Trunk\Resources\Engine\Windows Desktop"`

To import missing stuff from `Supplementary`, read Dependencies.txt.

Finally, open Visual Studio 2013 and do a Build > Build Solution.
