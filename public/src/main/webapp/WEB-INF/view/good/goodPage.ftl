<#-- @ftlvariable name="good" type="com.springapp.mvc.common.hibernateEntity.GoodInfo" -->
<#-- @ftlvariable name="customer" type="com.springapp.mvc.common.hibernateEntity.Customer" -->
<#include "../template/template.ftl">
<@mainTemplate title="${good.name}" />
<#macro m_body>

<!-- Begin content-->
<div class="clearfix" id="content">

    <div class="${good.name}" id="product">

        <div class="row clearfix">
            <!-- Begin product photos -->
            <div class="span5">

                <!-- Begin featured image -->
                <div class="image featured" style="max-width: 393px;">

                    <div id="wrap" style="top: 0px; position: relative; z-index: 9999;"><a class="cloud-zoom"
                                                                                           id="placeholder"
                                                                                           style="display: block; position: relative;"
                                                                                           href="#"
                                                                                           rel="position: 'inside', showTitle: 'false'">
                        <img style="display: block; max-width: 385px;"
                             alt="${good.name}"
                             src="/resources/img/goods/${good.getImageUrl()}">
                    </a>

                        <div class="mousetrap"
                             style='left: 0px; top: 0px; width: 385px; height: 385px; position: absolute; z-index: 999; cursor: auto; background-image: url(".");'></div>
                    </div>

                </div>
                <!-- End product image -->


                <div id="rollover">Rollover image for detail <img style="top: 5px; position: relative;"
                                                                  src="/resources/img/zoom.png">
                </div>

            </div>
            <!-- End product photos -->

            <!-- Begin description -->
            <div class="span6 productDetails">

                <h1 class="title">${good.name}</h1>

                <div class="purchase">
                    <h2 class="price" id="price-preview">$${good.price?number}.00
                        <del>$${good.price?number*1.3}.00</del>
                    </h2>
                </div>
                <#if good.description??>
                    <div class="description">
                        <p><span>${good.description}</span></p>
                    </div>
                </#if>
                <#if customer??>
                <form class="variants clearfix" id="add-item-form"
                      action="/cart/add?goodId=${good.id}&customerId=${customer.getId()}" method="post">
                <#else>
                <form class="variants clearfix" id="add-item-form" action="/login" method="get">
                </#if>
                <#if (good.getSize())??>
                    <div class="size">${good.getSize().getDescription()}</div></#if>
                <#if (cart.cartRows)?? && cart.containsGoodId(good.id)>
                    <a class="button item_add cbp-vm-icon cbp-vm-add" style="color: rgb(280, 124, 83)"
                       href="/cart">Go in cart</a>
                <#else>
                    <div class="purchase-section multiple">
                        <div class="purchase">
                            <input name="add" class="btn" id="add-to-cart" type="submit" value="Add to cart">
                        </div>
                    </div>
                </#if>
            </div>
            <!-- End product options -->

            </form>
        </div>
        <!-- End description -->

    </div>


</div>
<!-- End content-->

</#macro>