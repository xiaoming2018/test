var scene;
var camera;
var width;
var height;

function initscene() {
    scene = new THREE.Scene();
}

function initCamera() {
    debugger;
    width = $('#canvas-frame').width();
    height = $('#canvas-frame').height();
    camera = new THREE.PerspectiveCamera(45, width / height, 0.1, 1000);
    camera.position.x = 0;
    camera.position.y = 0;
    camera.position.z = 2.5;
    camera.up.x = 0;
    camera.up.y = 1;
    camera.up.z = 0;
    camera.lookAt(scene.position);
}

var renderer;

function initrenderer() {
    debugger;
    width = $('#canvas-frame').width();
    height = $('#canvas-frame').height();
    renderer = new THREE.WebGLRenderer({
        antialias: true
    });
    renderer.setSize(width, height);
    renderer.setClearColor(0xb9d3ff, 1);//设置背景颜色(淡蓝色)
    //renderer.setClearColor(0xffffff, 1);//设置背景颜色
    renderer.shadowMap.enabled = true;    // 告诉渲染器需要
    renderer.shadowMapSoft = true; // 软阴影
    renderer.shadowMapType = THREE.PCFSoftShadowMap; //边缘柔和
    document.getElementById('canvas-frame').appendChild(renderer.domElement);
}

var onProgress = function (xhr) {
    if (xhr.lengthComputable) {
        var percentComplete = xhr.loaded / xhr.total * 100;
        console.log(Math.round(percentComplete, 2) + '% downloaded');
    }
};
var onError = function (xhr) {
    console.log(xhr);
};

function loadObject() {
    debugger;
    var loader = new THREE.PLYLoader();
    loader.load("webgl_resource/models/ply/surface-clean.ply", function (geometry) {
        geometry.computeVertexNormals();
        //var material = new THREE.MeshStandardMaterial({color: 0x0055ff, flatShading: true});
        var material = new THREE.MeshPhongMaterial( { color: 0xffffff, specular: 0x111111, shininess: 200, vertexColors: THREE.VertexColors} );
        var mesh = new THREE.Mesh(geometry, material);

        mesh.position.set(0, 3, -3);
        mesh.castShadow = true;
        mesh.receiveShadow = true;
        scene.add(mesh);
    });
}

//初始化灯光
var light;
var amlight;

function initLight() {
    amlight = new THREE.AmbientLight(0xFFFFFF, 0.5);
    amlight.position.set(100, 100, 100);
    scene.add(amlight);

    light = new THREE.SpotLight(0xFFFFFF, 1);
    // light.position.set(2, 10, 10);
    light.position.set(20, 200, 200);
    light.castShadow = true;
    light.shadowMapHeight = 2048;
    light.shadowMapWidth = 2048;
    scene.add(light);
}

var plane;

function plane_fun() {
    var planeGeometry = new THREE.PlaneGeometry(20, 20);//平面
    var planeMaterial = new THREE.MeshLambertMaterial({color: 0xffffff});
    plane = new THREE.Mesh(planeGeometry, planeMaterial);
    plane.rotation.x = -0.5 * Math.PI;//将平面沿着x轴进行旋转
    plane.position.x = 0;
    plane.position.y = -0.8;
    plane.position.z = 0;
    plane.receiveShadow = true;//平面进行接受阴影
    scene.add(plane);
}

function animate() {
    renderer.render(scene, camera);
    requestAnimationFrame(animate);
}

//用户交互插件 鼠标左键按住旋转，右键按住平移，滚轮缩放
var controls;

function initControls() {

    controls = new THREE.OrbitControls(camera, renderer.domElement);
    controls.enableZoom = true;
    controls.autoRotate = true;
    controls.minDistance = 2;
    controls.maxDistance = 100;
    controls.enablePan = true;
}

function start() {
    initscene();
    initCamera();
    plane_fun();
    initrenderer();
    loadObject();
    initLight();
    animate();
    initControls();
    document.getElementById('canvas-frame').appendChild(renderer.domElement);
}


