jQuery(document).ready(function (jQuery) {
    if (isMobile()) {

    }
});

function isMobile() {
    var check = false;
    (function (a) {
        if (/(android|bb\d+|meego).+mobile|avantgo|bada\/|blackberry|blazer|compal|elaine|fennec|hiptop|iemobile|ip(hone|od)|iris|kindle|lge |maemo|midp|mmp|mobile.+firefox|netfront|opera m(ob|in)i|palm( os)?|phone|p(ixi|re)\/|plucker|pocket|psp|series(4|6)0|symbian|treo|up\.(browser|link)|vodafone|wap|windows (ce|phone)|xda|xiino/i.test(a) || /1207|6310|6590|3gso|4thp|50[1-6]i|770s|802s|a wa|abac|ac(er|oo|s\-)|ai(ko|rn)|al(av|ca|co)|amoi|an(ex|ny|yw)|aptu|ar(ch|go)|as(te|us)|attw|au(di|\-m|r |s )|avan|be(ck|ll|nq)|bi(lb|rd)|bl(ac|az)|br(e|v)w|bumb|bw\-(n|u)|c55\/|capi|ccwa|cdm\-|cell|chtm|cldc|cmd\-|co(mp|nd)|craw|da(it|ll|ng)|dbte|dc\-s|devi|dica|dmob|do(c|p)o|ds(12|\-d)|el(49|ai)|em(l2|ul)|er(ic|k0)|esl8|ez([4-7]0|os|wa|ze)|fetc|fly(\-|_)|g1 u|g560|gene|gf\-5|g\-mo|go(\.w|od)|gr(ad|un)|haie|hcit|hd\-(m|p|t)|hei\-|hi(pt|ta)|hp( i|ip)|hs\-c|ht(c(\-| |_|a|g|p|s|t)|tp)|hu(aw|tc)|i\-(20|go|ma)|i230|iac( |\-|\/)|ibro|idea|ig01|ikom|im1k|inno|ipaq|iris|ja(t|v)a|jbro|jemu|jigs|kddi|keji|kgt( |\/)|klon|kpt |kwc\-|kyo(c|k)|le(no|xi)|lg( g|\/(k|l|u)|50|54|\-[a-w])|libw|lynx|m1\-w|m3ga|m50\/|ma(te|ui|xo)|mc(01|21|ca)|m\-cr|me(rc|ri)|mi(o8|oa|ts)|mmef|mo(01|02|bi|de|do|t(\-| |o|v)|zz)|mt(50|p1|v )|mwbp|mywa|n10[0-2]|n20[2-3]|n30(0|2)|n50(0|2|5)|n7(0(0|1)|10)|ne((c|m)\-|on|tf|wf|wg|wt)|nok(6|i)|nzph|o2im|op(ti|wv)|oran|owg1|p800|pan(a|d|t)|pdxg|pg(13|\-([1-8]|c))|phil|pire|pl(ay|uc)|pn\-2|po(ck|rt|se)|prox|psio|pt\-g|qa\-a|qc(07|12|21|32|60|\-[2-7]|i\-)|qtek|r380|r600|raks|rim9|ro(ve|zo)|s55\/|sa(ge|ma|mm|ms|ny|va)|sc(01|h\-|oo|p\-)|sdk\/|se(c(\-|0|1)|47|mc|nd|ri)|sgh\-|shar|sie(\-|m)|sk\-0|sl(45|id)|sm(al|ar|b3|it|t5)|so(ft|ny)|sp(01|h\-|v\-|v )|sy(01|mb)|t2(18|50)|t6(00|10|18)|ta(gt|lk)|tcl\-|tdg\-|tel(i|m)|tim\-|t\-mo|to(pl|sh)|ts(70|m\-|m3|m5)|tx\-9|up(\.b|g1|si)|utst|v400|v750|veri|vi(rg|te)|vk(40|5[0-3]|\-v)|vm40|voda|vulc|vx(52|53|60|61|70|80|81|83|85|98)|w3c(\-| )|webc|whit|wi(g |nc|nw)|wmlb|wonu|x700|yas\-|your|zeto|zte\-/i.test(a.substr(0, 4)))check = true
    })(navigator.userAgent || navigator.vendor || window.opera);
    return check;
}

// Window load event used just in case window height is dependant upon images
$(window).bind("load", function () {
    $(".footer-wrapper").hide();
    var footerHeight = 0,
        footerTop = 0,
        $footer = $(".footer-wrapper");

    if (isMobile() == false) {
        positionFooter();
    } else {
        document.title = "LLseason";
    }

    function positionFooter() {

        footerHeight = $footer.height();
        footerTop = ($(window).scrollTop() + $(window).height() - footerHeight) + "px";

        if (($(document.body).height() + footerHeight) < $(window).height()) {
            $footer.css({
                position: "absolute"
            }).animate({
                top: footerTop
            })
        } else {
            $footer.css({
                position: "static"
            })
        }

    }

    $(window)
        .scroll(positionFooter)
        .resize(positionFooter)
    setTimeout($(".footer-wrapper").fadeIn(), 3000); // check again in a second
});

$(window).resize(function () {
    resizeWindow();
});

function resizeWindow() {
    var windowWidth = $(window).width();
    var imageWidth = ((windowWidth - 24) / 3);

    if (windowWidth < 629) {
        $('#fp-images img').width('100%');
        $('#centerImage').css('padding-left', "0");
        $('#centerImage').css('padding-right', "0");
        $('#leftImage').css('padding-right', "0");
        $('#rightImage').css('padding-left', "0");
        $('.remove a').text('X');
    } else {
        var marginLeft = (windowWidth - (imageWidth * 3)) / 2;
        $('#fp-images img').width(imageWidth);
        var halfMarginLeft = marginLeft / 2;
        $('#centerImage').css('padding-left', halfMarginLeft);
        $('#centerImage').css('padding-right', halfMarginLeft);
        $('#leftImage').css('padding-right', halfMarginLeft);
        $('#rightImage').css('padding-left', halfMarginLeft);
        $('.remove a').text('Remove');
    }
}

jQuery(window).load(function () {

    $('.flexslider').flexslider({
        animation: "slide",
        slideshow: true,
        animationDuration: 700,
        slideshowSpeed: 6000,
        animation: "fade",
        controlsContainer: ".flex-controls",
        controlNav: false,
        keyboardNav: true
    }).hover(function () {
        $('.flex-direction-nav').fadeIn();
    }, function () {
        $('.flex-direction-nav').fadeOut();
    });
    resizeWindow();


    $("select.loc_on_change").change(function () {
        if ($(this).attr("value") == "#") return false;
        window.location = $(this).attr("value");
    });


    var IE6 = false /*@cc_on || @_jscript_version < 5.7 @*/;
    if (IE6) {
        if ($("#placeholder img").width() > 360) {
            $("#placeholder img").css('width', '360px');
        } else {
            $("#placeholder img").css('width', $("#placeholder img").width() + 'px'); // restrict to initial width of image
        }
    } else {
        $("#placeholder img").css('maxWidth', $("#placeholder img").width() + 'px'); // restrict to initial width of image
    }

    var pw = $("#placeholder img").width() + 8;
    $("#placeholder img").parents('div.featured').css('maxWidth', pw + 'px'); // force IE to play nice

});

jQuery(document).ready(function ($) {

    $(".tweet").tweet({ // twitter plugin
        username: "shopify",
        join_text: "auto",
        avatar_size: 32,
        count: 2,
        auto_join_text_default: "",
        auto_join_text_ed: "",
        auto_join_text_ing: "",
        auto_join_text_reply: "",
        auto_join_text_url: "",
        loading_text: "Loading Tweets..."
    });

    $("a.zoom").fancybox({
        padding: 0,
        'titleShow': false,
        overlayColor: '#000000',
        overlayOpacity: 0.2
    });

    $("nav.mobile select").change(function () {
        window.location = jQuery(this).val();
    });
    $('#product .thumbs a').click(function () {

        $('#zoom-image').attr('href', $(this).attr('href'));
        return false;
    });

    $('#product .add-to-cart').click(function (e) {
        $('#add-item-to-cart').click();
    });

    $('input[type="submit"], input.btn, button').click(function () { // remove ugly outline on input button click
        $(this).blur();
    })

    $("li.dropdown").hover(function () {
        $(this).children('.dropdown').show();
        $(this).children('.dropdown').stop();
        $(this).children('.dropdown').animate({
            opacity: 1.0
        }, 200);
    }, function () {
        $(this).children('.dropdown').stop();
        $(this).children('.dropdown').animate({
            opacity: 0.0
        }, 400, function () {
            $(this).hide();
        });
    });

    $('a[href^="#"]').bind('click.smoothscroll', function (e) {
        e.preventDefault();

        var target = this.hash,
            $target = $(target);

        $('html, body').stop().animate({
            'scrollTop': $target.offset().top
        }, 500, 'swing', function () {
            window.location.hash = target;
        });
    });

    var tabs = $('ul.tabs > li > a');
    tabs.each(function (i) {
        jQuery(this).unbind('click.smoothscroll')
            .click(function (e) {
                var contentLocation = $(this).attr('href');
                if (contentLocation.charAt(0) == "#") {
                    tabs.removeClass('active');
                    $(this).addClass('active');
                    $(contentLocation).show().addClass('active').siblings().hide().removeClass('active');
                }
                return false;
            });
    });

}); // end document ready
