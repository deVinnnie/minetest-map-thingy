# Minetest Map

[![Build Status](https://travis-ci.org/deVinnnie/minetest-map-thingy.svg?branch=master)](https://travis-ci.org/deVinnnie/minetest-map-thingy)

Show current player positions, protected areas, and travelnets on a 2D map of a minetest world.

![screenshot](screenshot.png)

Build from source:
```
gradle assemble && docker-compose build && docker-compose up --force-recreate
```

Leaflet code adapted from [est31/leaftest](https://github.com/est31/leaftest)