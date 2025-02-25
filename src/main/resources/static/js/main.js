document.addEventListener("DOMContentLoaded", function () {
    const images = document.querySelectorAll("#image1","#image2","#image3");
    images.forEach(image => {
        image.onerror = function () {
            this.src = "/images/noimage.jpg";
            this.alt = "Not Found Image";
            console.log("Image load failed, fallback to noimage.jpg");
        };
    });
});


document.addEventListener("DOMContentLoaded", function () {
    const images = document.querySelectorAll("#image1-btn","#image2-btn","#image3-btn","#image-main");
    images.forEach(image => {
        image.onerror = function () {
            this.src = "/images/noimage.jpg";
            this.alt = "Not Found Image";
            console.log("Image load failed, fallback to noimage.jpg");
        };
    });
});


var allButtons = document.getElementsByClassName('img-btn');

var img = document.getElementById('image-main');

for (var i = 0; i < allButtons.length; i++) {
    allButtons[i].addEventListener('click', function () {
        changeImage(this.src);

        setActiveButton(this);
    });
}

function changeImage(path) {
    img.src = path;
}

function setActiveButton(activeButton) {
    for (var i = 0; i < allButtons.length; i++) {
        allButtons[i].classList.remove('show');
    }

    activeButton.classList.add('show');
}
