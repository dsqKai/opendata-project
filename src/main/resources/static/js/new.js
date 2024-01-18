ymaps.ready(function(){
    var map = new ymaps.Map("app", {
        center: [55.76, 37.64],
        zoom: 10
    });
    let clusters = []
    let polygonsObject = new ymaps.ObjectManager()
    let colors = ["#48ff00", "#fff800", "#ff7a00", "#ff0000"];
    let clustersLevel = new Map();
    $.ajax({
        url: 'http://localhost:8080/api/v1/getVoronoiPolygons',
        method: 'get',
        dataType: 'json',
        success: function(data){
            const cluster = new Map();
            data.forEach((polygon) => {
                let coords = [];
                polygon.points.forEach((point) => {
                    coords.push([point.y, point.x])
                })
                if(!cluster.has(polygon.clusterId)) {
                    cluster.set(polygon.clusterId, []);
                    clustersLevel.set(polygon.clusterId, 0)
                }
                cluster.get(polygon.clusterId).push([coords])
                clustersLevel.set(polygon.clusterId, clustersLevel.get(polygon.clusterId)+polygon.level);
                let k = {
                    type: "Feature",
                    id: polygon.uuid,
                    geometry: {
                        type: 'Polygon',
                        coordinates: [coords]
                    },
                    properties: {balloonContentBody: polygon.uuid},
                    options: {
                        fillColor: colors[polygon.level-1],
                        opacity: 0.6,
                        strokeWidth: 1,}
                }
                polygonsObject.add(k);
            })
            cluster.forEach((value, key, map1) => {
                let r = new ymaps.Polygon([polygonClipping.union([...value]).flat(2), []],{},
                    {fillColor: colors[Math.round(clustersLevel.get(key)/value.length)-1],
                        opacity: 0.6,
                        strokeWidth: 1,
                        });
                r.events
                    .add('mouseenter', function (e) {
                        e.get('target').options.set('opacity', '0.7');
                        e.get('target').options.set('strokeWidth', '5');
                    })
                    .add('mouseleave', function (e) {
                        e.get('target').options.set('opacity', '0.5');
                        e.get('target').options.set('strokeWidth', '1');
                    });
                clusters.push(r);
                map.geoObjects.add(r);
            })
        }
    });

    map.events.add('boundschange', function (e) {
        let newZoom = e.get('newZoom');
        let oldZoom = e.get('oldZoom');
        if (newZoom !== oldZoom) {
            if(newZoom >= 13) {
                map.geoObjects.removeAll();
                map.geoObjects.add(polygonsObject);
            } else if(newZoom === 12 && oldZoom === 13) {
                map.geoObjects.removeAll();
                clusters.forEach((cluster) => {map.geoObjects.add(cluster);})
            }
        }
    });
});

function getRandomColor() {
    var letters = '0123456789ABCDEF';
    var color = '#';
    for (var i = 0; i < 6; i++) {
        color += letters[Math.floor(Math.random() * 16)];
    }
    return color;
}