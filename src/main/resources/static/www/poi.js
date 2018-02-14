var LeafIcon = L.Icon.extend({
    options: {
        shadowUrl: 'images/leaf-shadow.png',
        iconSize:     [14, 14],
        shadowSize:   [0, 0],
        iconAnchor:   [7, 7],
        shadowAnchor: [4, 62],
        popupAnchor:  [-3, -76]
    }
});

var playerIcon = new LeafIcon({iconUrl: 'images/player.png'});

function addMarkers(locations){
    locations.forEach(function(location, index){
            L.marker([location.x, location.z])
             .addTo(map)
             .bindPopup(""+location.name + " (" + location.x + ", " + location.y + ", " + location.z+")");
        }
    );
}

function addAreas(){
      $.getJSON("/areas",
        (data) => {
            data.forEach(function(area, index){
                      var polygon = L.polygon([
                          [area.pos1.x, area.pos1.z],
                          [area.pos1.x, area.pos2.z],
                          [area.pos2.x, area.pos2.z],
                          [area.pos2.x, area.pos1.z],
                      ])
                      .addTo(map)
                      .bindPopup(area.name + " &lt;" + area.owner + "&gt;");
                  }
              );
        }
      );
}

function listPOI(){
    var list = document.getElementById('map-navigation');

    $.getJSON("/travelnets",
            (travelnets) => {
                Object.keys(travelnets["porters"])
                      .forEach((key,index) => {
                              console.log(key)
                              var listElement = document.createElement("li");

                              var porter = travelnets["porters"][key];

                              var link = document.createElement("a");
                              link.setAttribute("data-position", porter.pos.x + "," + porter.pos.z);
                              link.setAttribute("href", "#");
                              var node = document.createTextNode(key);
                              link.appendChild(node);

                              listElement.appendChild(link);

                              list.appendChild(listElement);

                      })



            }
    );
}

function addPlayers(){
  $.getJSON("/players",
    (data) => {
      data.forEach((player) => {
        L.marker([player.position.x, player.position.z], {icon: playerIcon})
               .addTo(map)
               .bindPopup(""+player.name + " (" + player.position.x + ", " + player.position.y + ", " + player.position.z+")");
          }
      );
    }
  );
}

document.getElementById('map-navigation').onclick = function(abc) {
     var pos = abc.target.getAttribute('data-position');
     var zoom = map.getZoom();
     
     if (pos && zoom) {
         var locat = pos.split(',');
         var zoo = parseInt(zoom);
         map.setView(locat, zoo, {animation: true});
         return false;
     }
 }
