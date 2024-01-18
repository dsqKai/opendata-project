const LOCATION = {center: [37.64, 55.76], zoom: 10};
let colors = new Map();
async function initMap() {
    await ymaps3.ready;
    const {YMap, YMapDefaultSchemeLayer,YMapListener, YMapControlButton, YMapMarker, YMapControls, YMapFeature, YMapDefaultFeaturesLayer, YMapLayer, YMapTileDataSource} = ymaps3;

    const {YMapZoomControl, YMapGeolocationControl, Y} = await ymaps3.import('@yandex/ymaps3-controls@0.0.1');
    const {YMapDefaultMarker} = await ymaps3.import('@yandex/ymaps3-markers@0.0.1');

    map = new YMap(document.getElementById('app'), {location: LOCATION});

    map.addChild((scheme = new YMapDefaultSchemeLayer()));
    map.addChild(new YMapControls({position: 'right'}).addChild(new YMapZoomControl({})));
    let fe = new YMapDefaultFeaturesLayer({id: 'features', zIndex: 200000})
    // $.ajax({
    //     url: 'http://localhost:8080/getVoronoi',
    //     method: 'get',
    //     dataType: 'json',
    //     success: function(data){
    //
    //         data.forEach((polygon) => {
    //             let coords = [];
    //             polygon.points.forEach((point) => {
    //                 coords.push([point.x, point.y])
    //             })
    //             if(!colors.has(polygon.clusterId)) {
    //                 colors.set(polygon.clusterId, getRandomColor());
    //             }
    //             let r = {id: polygon.uuid,
    //                 geometry: {
    //                     type: 'Polygon',
    //                     coordinates: [coords]
    //                 },
    //                 style: {
    //                     fillRule: 'nonZero',
    //                     fill: colors.get(polygon.clusterId),
    //                     stroke: [{color: 'black', width: 1}]
    //                 }}
    //             fe.addChild(new YMapFeature(r));
    //         })
    //         map.addChild(fe);
    //     }
    // });
    $.ajax({
        url: 'http://localhost:8080/getVoronoi',
        method: 'get',
        dataType: 'json',
        success: function(data){

            const cluster = new Map();
            data.forEach((polygon) => {
                let coords = [];
                polygon.points.forEach((point) => {
                    coords.push([point.x, point.y])
                })
                if(!cluster.has(polygon.clusterId)) {
                    cluster.set(polygon.clusterId, []);
                }
                cluster.get(polygon.clusterId).push([coords])
            })
            cluster.forEach((value, key, map1) => {
                let r = {id: key,
                    geometry: {
                        type: 'Polygon',
                        coordinates: [polygonClipping.union(...value)].flat(2)
                    },
                    style: {
                        fillRule: 'nonZero',
                        fill: getRandomColor(),
                        stroke: [{color: 'black', width: 1}]
                    },
                    onClick: () => {
                        console.log(key)
                    }}

                fe.addChild(new YMapFeature(r));
            })
            map.addChild(fe);
        }
    });
    let flag = true;
    const button = new YMapControlButton({
        text: 'Москва',
        onClick: () => {

        }});
    const controls = new YMapControls({position: 'top left'});
    controls.addChild(new YMapGeolocationControl());
    controls.addChild(button);
    map.addChild(controls);
}
initMap();

function getRandomColor() {
    var letters = '0123456789ABCDEF';
    var color = '#';
    for (var i = 0; i < 6; i++) {
        color += letters[Math.floor(Math.random() * 16)];
    }
    return color;
}

