<#-- @ftlvariable name="listMenu" type="java.util.List<com.springapp.mvc.common.MenuInfo>" -->
<#-- @ftlvariable name="cart" type="com.springapp.mvc.common.Cart" -->
<#-- @ftlvariable name="customer" type="com.springapp.mvc.common.hibernateEntity.Customer" -->
<div class="row" id="header" style="margin-bottom: 0px;">
    <div class="span12">
        <div class="inner-left">
            <div class="logo">
                <a href="/"><img src="//cdn.shopify.com/s/files/1/0203/1194/t/3/assets/logo.png?6262410844493679645"
                                 alt="The Legends League"></a>
            </div>
            <div id="social-icons" style="z-index: 999999;">
                <a target="_blank" href="http://www.facebook.com/thelegendsleaguebe"><img
                        src="http://cdn.shopify.com/s/files/1/0203/1194/files/facebook.png?638"></a>
                <a target="_blank" href="http://twitter.com/legendsleague"><img
                        src="http://cdn.shopify.com/s/files/1/0203/1194/files/twitter.png?638"></a>
                <a target="_blank" href="http://instagram.com/legendsleague"><img
                        src="http://cdn.shopify.com/s/files/1/0203/1194/files/instagram.png?638"></a>
                <a target="_blank" href="http://vimeo.com/21580849"><img
                        src="http://cdn.shopify.com/s/files/1/0203/1194/files/vimeo.png?638"></a>
            </div>
        <#if customer??>
            <div id="cart">
                <li><span class="icon-cart"></span>
                    <a href="/cart" class="cart" title="Shopping Cart"><span id="your-cart">Your cart: </span>
                        <span><#if (cart)??>${cart.getTotalCount()}<#else> 0 </#if></span> Item(s)
                        <span><#if (cart)??>$[${cart.getTotalPrice()}.00]<#else> [$0.00] </#if></span></a>
                </li>
            </div>
        </#if>
        </div>

    <#include "headerMenu.ftl" />

    </div>
</div>