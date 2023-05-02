let areaUrl = "/navigator/searchSido";
window.onload = init();

//창이 뜨면 시도 목록 가져온다
function init() {
    fetchAllAreas(areaUrl);
}
//시도 목록 가져와서 옵션값으로 추가한다
function fetchAllAreas(areaUrl) {
    fetch(areaUrl, { method: "GET" })
        .then((response) => response.json())
        .then((data) => makeOptionforSido(data));
}


//바뀐 시도 값을 가지고 구군 목록을 가져온다.
document.getElementById("search-sido").addEventListener("change", function () {
    const gugunSelect = document.getElementById("search-gugun");
    gugunSelect.innerHTML = ""; // 옵션태그 모두 삭제
    const selectedSido = this.options[this.selectedIndex].value;	//sido-code
    // 새로운 option 추가
    const defaultOpt = document.createElement("option");
    defaultOpt.value = "0";
    defaultOpt.text = "구군 선택";
    gugunSelect.add(defaultOpt);
    fetchGugunList(selectedSido); // 함수 실행하기
});

function fetchGugunList(selectedSido) {
    console.log(selectedSido);
    const gugunUrl = "/navigator/searchGugun?sidoCode=" + selectedSido;
    fetch(gugunUrl, { method: "GET" })
        .then((response) => response.json())
        .then((data) => makeOptionforGugun(data));
}

//시도 옵션태그를 추가한다
function makeOptionforSido(data) {
    let areas = data;
    console.log(areas);
    let sel = document.getElementById("search-sido");
    areas.forEach((area) => {
        let opt = document.createElement("option");
        opt.setAttribute("value", area.sidoCode);
        opt.appendChild(document.createTextNode(area.sidoName));
        sel.appendChild(opt);
    });
}

//구군 옵션태그를 추가한다
function makeOptionforGugun(data) {
    let areas = data;
    console.log(areas);
    let sel = document.getElementById("search-gugun");
    areas.forEach((gugun) => {
        let opt = document.createElement("option");
        opt.setAttribute("value", gugun.gugunCode);
        opt.appendChild(document.createTextNode(gugun.gugunName));
        sel.appendChild(opt);
    });
}

//세 항목을 가지고 여행지 리스트를 가져온다 
document.getElementById("search-btn").addEventListener("click", () => {
    let searchUrl = "/navigator/attrList";
    let sidoCode = document.getElementById("search-sido").value;
    let gugunCode = document.getElementById("search-gugun").value;
    let contentTypeId = document.getElementById("search-content-id").value;

    if (parseInt(sidoCode)) searchUrl += "?sidoCode=" + sidoCode;
    if (parseInt(gugunCode)) searchUrl += "&gugunCode=" + gugunCode;
    if (parseInt(contentTypeId)) searchUrl += "&contentTypeId=" + contentTypeId;

    console.log(searchUrl);
    fetch(searchUrl)
        .then((response) => response.json())
        .then((data) => makeAttrList(data));
});

// 
var positions; // marker 배열.
function makeAttrList(data) {
    document.querySelector("table").setAttribute("style", "display: ;");
    let trips = data;
    let tripList = ``;
    //배열에 지역명 저장
    positions = [];

    trips.forEach((area) => {
        tripList += `
    <tr onclick="moveCenter(${area.latitude}, ${area.longitude});">
      <td><img src=${area.imgUrl1} width="100px" height="60px"></td>
      <td>${area.title}</td>
      <td>${area.address1} ${area.address2}</td>
    </tr>
  `;

        let markerInfo = {
            content: `<div class="container-fluid bg-light">
                  <p>${area.title}</p>
                </div>
                <div class="container-fluid row">
                  <div class="col-3 attraction-first-card" style="background-image: url(${area.imgUrl1}); height: 3rem">
                  </div>
                  <div class="col">
                    <p>${area.address1}</p>
                  </div>
                </div>
                `,
            latlng: new kakao.maps.LatLng(area.latitude, area.longitude),
        };
        positions.push(markerInfo);
        console.log(positions);
    });
    document.getElementById("trip-list").innerHTML = tripList;
    displayMarker();
}

// 카카오지도
var mapContainer = document.getElementById("map"), // 지도를 표시할 div
    mapOption = {
        center: new kakao.maps.LatLng(37.500613, 127.036431), // 지도의 중심좌표
        level: 5, // 지도의 확대 레벨
    };

// 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
var map = new kakao.maps.Map(mapContainer, mapOption);

function displayMarker() {
    // 마커 이미지의 이미지 주소입니다
    var imageSrc =
        "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";

    for (var i = 0; i < positions.length; i++) {
        // 마커 이미지의 이미지 크기 입니다
        var imageSize = new kakao.maps.Size(24, 35);

        // 마커 이미지를 생성합니다
        var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

        // 마커를 생성합니다
        var marker = new kakao.maps.Marker({
            map: map, // 마커를 표시할 지도
            position: positions[i].latlng, // 마커를 표시할 위치
            title: positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
            image: markerImage, // 마커 이미지
        });

        // 마커에 표시할 인포윈도우를 생성합니다
        var infoWindow = new kakao.maps.InfoWindow({
            content: positions[i].content,
        });

        // 마커에 mouseover 이벤트와 mouseout 이벤트를 등록합니다
        // 이벤트 리스너로는 클로저를 만들어 등록합니다
        // for문에서 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됩니다
        kakao.maps.event.addListener(
            marker,
            "mouseover",
            makeOverListener(map, marker, infoWindow)
        );
        kakao.maps.event.addListener(
            marker,
            "mouseout",
            makeOutListener(infoWindow)
        );
    }

    // 인포윈도우를 표시하는 클로저를 만드는 함수입니다
    function makeOverListener(map, marker, infoWindow) {
        return function () {
            infoWindow.open(map, marker);
        };
    }

    // 인포윈도우를 닫는 클로저를 만드는 함수입니다
    function makeOutListener(infoWindow) {
        return function () {
            infoWindow.close();
        };
    }

    // 첫번째 검색 정보를 이용하여 지도 중심을 이동 시킵니다
    map.setCenter(positions[0].latlng);
}

function moveCenter(lat, lng) {
    map.setCenter(new kakao.maps.LatLng(lat, lng));
}

