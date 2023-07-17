const $ = document.querySelector.bind(document);
const $$ = document.querySelectorAll.bind(document);

const searchShadow = $('#display__search-shadow');
const searchInput = $('#display__search-input');
const navMobile = $('.nav__mobile');
const navOverlay = $('.nav__overlay');

function Show_RemoveNav(){
    $('.nav__bar-item').onclick = () => {
        navMobile.classList.add('nav__mobile-show');
        setTimeout(() => {
            navOverlay.style.display = 'block'
        }, 300);
    }
    $('.nav__mobile-close').onclick = () => {
        navMobile.classList.remove('nav__mobile-show')
        navOverlay.style.display = 'none'
    }
}
Show_RemoveNav()
//======================Search======================
//search
/*var searchSuccess = false;
var searchNumber = 0;
function CheckSearch(){
    if (searchSuccess) {
        searchShadow.style.animation = 'faded_0_1 ease-in 0.3s';
        searchInput.style.animation = 'faded_0_1 ease-in 0.3s';
        searchShadow.classList.remove('hidden');
        searchInput.classList.remove('hidden');
    } else if (searchNumber == 0){
        searchShadow.classList.add('hidden');
        searchInput.classList.add('hidden');
        searchNumber++;
        } else {
            searchShadow.style.animation = 'faded_1_0 linear .5s forwards';
            searchInput.style.animation = 'faded_1_0 linear .5s forwards';
            setTimeout(() => {
                searchShadow.classList.add('hidden');
                searchInput.classList.add('hidden');
            },600);
        }      
    } 
CheckSearch();
$('#searchBtn').addEventListener('click',() => {
    searchSuccess = true;
    CheckSearch();
})
searchShadow.addEventListener('click',() =>{
    searchSuccess = false;
    CheckSearch();
})*/




function initQuantity()
{
    var plus = $$('.plus');
    var minus = $$('.minus');
    var value = $('#quantity_value');

    for(var key=0; key < plus.length; key++){
      if (plus[key]){
          plus[key].addEventListener('click',function(){
            console.log("hello")
            var x = parseInt(value.innerHTML);
            value.innerHTML = x + 1;
          })
      }
    }

    for(var key=0; key < minus.length; key++){
      if (minus[key]){
        minus[key].addEventListener('click',function(){
            console.log("hello")
            var x = parseInt(value.innerHTML);
            if (x > 1){
              value.innerHTML = x - 1;
            }
          })
      }
    }
}


initQuantity();

//======================Swiper======================
var swiper = new Swiper(".slide_Swiper", {
  loop: true,
  effect: "fade",
  pagination: {
    el: ".swiper-pagination",
    clickable: true,
    dynamicBullets: true
  },
  autoplay: {
      delay: 3500,
      disableOnInteraction: false
  }
});

var swiper = new Swiper(".painting__slide",{
  effect: "coverflow",
  grabCursor: true,
  centeredSlides: true,
  slidesPerView: "auto",
  loop: true,
  spaceBetween: 32,
  coverflowEffect: {
    rotate: 0,
  },
  navigation: {
      nextEl: ".swiper-button-next",
      prevEl: ".swiper-button-prev",
  }
})

var swiper = new Swiper(".same__product-slide", {
  slidesPerView: 5,
  spaceBetween: 20,
  slidesPerGroup: 1,
  loop: true,
  loopFillGroupWithBlank: true,
  pagination: {
    el: ".swiper-pagination",
    clickable: true,
  },
  navigation: {
    nextEl: ".swiper-button-next",
    prevEl: ".swiper-button-prev",
  },
});
