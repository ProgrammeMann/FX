<#-- @ftlvariable name="topGoods" type="java.util.List<com.springapp.mvc.common.hibernateEntity.GoodInfo>" -->
<#include "../template/template.ftl">
<@mainTemplate title="Главная" />
<#macro m_body>
<!-- Begin content-->
<section id="content" class="clearfix">

    <!-- Begin slider -->
    <div style="margin-top: -20px;">
        <div class="span12">
            <div class="flexslider-container">
                <div class="flexslider">
                    <ul class="slides">
                        <li>
                            <a href="/catalog/1">
                                <img src="/resources/img/slideshow_1.jpg"
                                     alt="">
                            </a>
                        </li>

                    </ul>
                    <div class="flex-controls"></div>
                </div>
            </div>
        </div>
        <!-- End slider -->

        <!-- Begin promo images -->
        <div class="row" id="fp-images">

            <div class="span4" id="leftImage" style="position: relative; left: 0px; padding-right: 6px;">
                <a href="/catalog/9">
                    <img src="/resources/img/fp_image_1.jpg"
                         alt=""
                         style="width: 309.667px;">
                </a>
            </div>

            <div class="span4" id="centerImage" style="padding-left: 6px; padding-right: 6px;">
                <a href="/catalog/4">
                    <img src="/resources/img/fp_image_2.jpg"
                         alt=""
                         style="width: px;">
                </a>
            </div>

            <div class="span4" id="rightImage" style="position: relative; float: right; padding-left: 6px;">
                <a href="/catalog/9">
                    <img src="/resources/img/fp_image_3.jpg"
                         alt=""
                         style="width: 309.667px;">
                </a>
            </div>
        </div>
    </div>
</section>
</#macro>