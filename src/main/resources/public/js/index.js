const cookieContainer = document.querySelector(".cookie-container");
const cookieButton = document.querySelector(".cookie-btn");

cookieButton.addEventListener("click", () => {
    cookieContainer.classList.remove("active");
    localStorage.setItem("cookieBannerDisplayed", "true");
});

setTimeout( () => {
    /*if(!localStorage.getItem("cookieBannerDisplayed")){*/
        cookieContainer.classList.add("active");
    /*}*/

}, 2000);

/* BOTON ARRIBA -------------------------------*/
(function ($) {
    "use strict";
    $(document).ready(function(){
        $('.go-up').click(function(){
            $('body, html').animate({
                scrollTop: '0px'
            }, 300);
        });
        $(window).scroll(function(){
            if( $(this).scrollTop() > 0 ){
                $('.go-up').slideDown(300);
            } else {
                $('.go-up').slideUp(300);
            }
        });
    });
})(jQuery);