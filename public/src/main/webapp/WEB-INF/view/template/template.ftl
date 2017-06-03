<#macro mainTemplate title="" styles=[] scripts=[] headerBannerClass="banner" >
<!DOCTYPE HTML>
<html class="no-js" lang="ru">

<head>
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <link rel="shortcut icon" href="/resources/img/favicon.ico"
          type="image/x-icon">

    <title>${title}</title>

    <meta name="description" content="TheLegendsLeague online store">

    <meta name="author" content="">
    <meta name="viewport" content="width=device-width; initial-scale=1.0; maximum-scale=1.0;">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">

    <link rel="canonical" href="/">

    <meta property="og:image" content="/resources/img/logo.png">

    <link href="/resources/css/style.css" rel="stylesheet"
          type="text/css" media="all">

    <link rel="stylesheet" type="text/css" href="/resources/css/fonts.css">

    <script src="//cdn.shopify.com/s/files/1/0203/1194/t/3/assets/html5shiv.js?6262410844493679645"
            type="text/javascript"></script>

    <script async="" src="https://cdn.shopify.com/s/javascripts/trekkie.storefront.min.js?v=1"></script>

    <script type="text/javascript" src="/resources/js/jquery-1.11.1.min.js"></script>

    <script src="//cdn.shopify.com/s/assets/themes_support/option_selection-04f21dade47c181126ac61b64d757485f84671daadfa90e4956a522cda4c2948.js"
            type="text/javascript"></script>
    <script src="//cdn.shopify.com/s/assets/themes_support/api.jquery-5f52c6aafe08e99b5fd74bc04431f324d961490d08bb70ca69b5e05941aa4323.js"
            type="text/javascript"></script>
    <script src="/resources/js/simpleCart.min.js"></script>

    <script type="text/javascript">
        var addToHomeConfig = {
            autostart: true,
            animationIn: 'bubble',
            animationOut: 'drop',
            lifespan: 5000,
            expire: 0,
            touchIcon: true,
            message: 'Tap the %icon icon, and then <strong>Add to Home Screen</strong> to bookmark The Legends Leauge App.'
        };
    </script>
    <link rel="stylesheet" href="/resources/css/add2home.css">
    <script type="text/javascript"
            src="/resources/js/add2home.js"
            charset="utf-8"></script>
</head>
<body>

<div id="transparency" class="wrapper" style="margin-bottom: 0;">
    <div class="row" style="margin-bottom: 0;">
        <#include "components/header.ftl" />
        <@m_body />
    </div>
    <#include "components/footer.ftl" />
</div>
<script src="/resources/js/jquery.flexslider.js"
        type="text/javascript"></script>

<script src="/resources/js/jquery.zoom.js"
        type="text/javascript"></script>

<script src="/resources/js/jquery.tweet.js?"
        type="text/javascript"></script>
<script src="/resources/js/jquery.fancybox.js"
        type="text/javascript"></script>
<script src="/resources/js/scripts.js"
        type="text/javascript"></script>
</body>
</html>
</#macro>