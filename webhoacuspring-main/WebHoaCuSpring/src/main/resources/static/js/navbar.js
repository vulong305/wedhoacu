


const navbar = $('.nav__');



function ShowNavbar(){
    if (document.documentElement.scrollTop > 200 || document.body.scrollTop > 200){
        navbar.classList.add('show__nav');
        $('#search-input-form').classList.remove('hidden-search');
    } else {
        navbar.classList.remove('show__nav');
        $('#search-input-form').classList.add('hidden-search');
    }
}


window.onscroll = ShowNavbar
