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
           travelnets.forEach(
              station => {
              var listElement = document.createElement("li");

              var link = document.createElement("a");
              link.setAttribute("data-position", station.pos.x + "," + station.pos.z);
              link.setAttribute("href", "#");
              var node = document.createTextNode(station.name);
              link.appendChild(node);

              listElement.appendChild(link);

              list.appendChild(listElement);

              L.marker([station.pos.x, station.pos.z])
               .addTo(map)
               .bindPopup(""+ station.name + " [ " + station.owner + " -> " + station.network + " ] (" + station.pos.x + ", " + station.pos.y + ", " + station.pos.z+")");
            }
           );
        }
    );
}

function togglePlayerNames(){
  tooltips.forEach(t => t.toggleTooltip())
}

let tooltips = [];



function addPlayers(){
  var togglePlayerNamesButton = L.Control.extend({
    options: {
      position: 'topleft' 
    },
    onAdd: function (map) {
      let container = L.DomUtil.create('div', 'leaflet-bar leaflet-control leaflet-control-custom');
      
      container.style.backgroundColor = 'white';
      container.style.width = '26px';
      container.style.height = '26px';
      
      let button = L.DomUtil.create('button');
      button.id = "toggle-player-names";
      
      let buttonText = document.createTextNode("A");
      button.appendChild(buttonText);
      
      container.appendChild(button);

      container.onclick = function(event){
        togglePlayerNames();
        event.preventDefault();
      }
      return container;
    },
  });
  
  map.addControl(new togglePlayerNamesButton());
  
  $.getJSON("/players",
    (data) => {
      data.forEach((player) => {
                let marker = L.marker([player.position.x, player.position.z],
                    {
                      icon: playerIcon,
                      title: player.name,
                      zIndexOffset: 1000
                    }
                )
               .addTo(map);
               
               let tooltip = marker.bindTooltip(player.name, {
                 "direction" : "top",
               });
               tooltips.push(tooltip);
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
