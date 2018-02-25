$.getJSON("conf.json",
    (config) => {
        map = loadmap(config);
        addAreas();
        addPlayers();
        listPOI();
   }
);

function loadmap(config) {
  /* 
  * Workaround for 1px lines appearing in some browsers due to fractional transforms
  * and resulting anti-aliasing.
  * https://github.com/Leaflet/Leaflet/issues/3575
  */
  var originalInitTile = L.GridLayer.prototype._initTile
  L.GridLayer.include({
      _initTile: function (tile) {
          originalInitTile.call(this, tile);

          var tileSize = this.getTileSize();

          tile.style.width = tileSize.x + 1 + 'px';
          tile.style.height = tileSize.y + 1 + 'px';
      }
  });
  
	var mapsize = config.mapsize;
	var spawn = config.spawn;
	var zoommin = config.zoommin;
	var xb= 0-spawn.x+mapsize/2;
	var yb= 0-spawn.y-mapsize/2-256;
	var bnd = new L.LatLngBounds();
	bnd.extend(L.latLng([spawn.x-mapsize/2, spawn.y-mapsize/2]));
	bnd.extend(L.latLng([spawn.x+mapsize/2, spawn.y+mapsize/2]));
	var p1 = L.point(-1000, -1000),
    p2 = L.point(1000, 1000),
    bounds = L.bounds(p1, p2);
	var map = L.map('map', {
		maxZoom:26,
		minZoom:zoommin,
		maxNativeZoom:20,
		fullscreenControl: true,
		//maxBounds: bnd, //commented out until it works...
		crs: L.extend ({}, L.CRS.Simple, {
        projection: {
          project: function (latlng) {
            return new L.Point(latlng.lat+xb, latlng.lng+yb);
          },
          
          unproject: function (point) {
            return new L.LatLng(point.x-xb, point.y-yb);
          },
          bounds: bounds
        },
        transformation: new L.Transformation(1, 0, -1, 0),
        scale: function (zoom) {
          return Math.pow(2, zoom-20);
        }
    })
	}).setView([0,0], 19);
	map.setView([spawn.x,spawn.y]);
	L.tileLayer('tiles/{z}/map_{x}_{y}.png', {
		maxZoom: 26,
		maxNativeZoom: 20,
		tileSize: 256,
		noWrap: true,
		continuousWorld: true
	}).addTo(map);
	map.on('mousemove click', function(e) {
		window.mousemove.innerHTML = e.latlng.lat.toFixed(1) + ', ' + e.latlng.lng.toFixed(1);
	});
    
    return map;
}
 
