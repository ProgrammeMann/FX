<#-- @ftlvariable name="cart" type="com.springapp.mvc.common.Cart" -->
<#include "../template/template.ftl">
<@mainTemplate title="Корзина" />
<#macro m_body>
<section class="clearfix" id="content">

    <div id="my-cart">

        <div class="row">
            <div class="span12">
                <h1>Your cart</h1>
                <#if (cart.getCartRows())??>
                    <#assign cartRows = cart.getCartRows()>
                <form id="cartform" action="/cart" method="post">
                <table>
                    <thead>
                    <tr>
                        <th class="cart-image">&nbsp;</th>
                        <th class="item">Item</th>
                        <th class="item-price">Price</th>
                        <th class="qty">Quantity</th>
                        <th class="price">Total</th>
                        <th class="remove">&nbsp;</th>
                    </tr>
                    </thead>
                <tbody>
                    <#list cartRows as cartRow>
                        <#assign good=cartRow.getGood()>
                    <tr>
                        <td class="cart-image" style="max-width: 60px;">
                            <div class="product_image">
                                <a href="">
                                    <img alt="${good.getName()}" src="/resources/img/goods/${good.getImageUrl()}">
                                </a>
                            </div>
                        </td>
                        <td class="item">
                            <a href="/good/${good.getId()}">
                                <strong>${good.getName()}</strong>
                            </a>
                        </td>
                        <td class="item-price">$${good.getPrice()}.00</td>
                        <td class="qty">
                            <#if (cartRow.getCount() > 1)>
                                <a class="countMinus"
                                   href="/cart/update?cart_row_id=${cartRow.getId()}&count=-1">-</a>
                            </#if>
                            <#if (error??)>
                                <p>${cartRow.getCount()}</p>
                            <#else>
                                <p style="color: red">${cartRow.getCount()}</p>
                            </#if>

                            <a class="countPlus" href="/cart/update?cart_row_id=${cartRow.getId()}&count=1">+</a>
                    </form>
                        </td>
                        <td class="price">$${good.getPrice()*cartRow.getCount()}.00</td>
                        <td class="remove"><a class="cart"
                                              href="/cart/remove?cart_row_id=${cartRow.getId()}">Remove</a>
                        </td>

                        </tr>
                    </#list>
                    <tr class="summary" style="height: 50px;">
                        <td class="cart-image" colspan="2">
                        </td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td style="text-align: right; font-size: 14px;">TOTAL</td>
                        <td class="price"><span
                                class="total"><strong>$[${cart.getTotalPrice()}.00]</strong></span></td>
                        <td>&nbsp;</td>
                    </tr>
                    </tbody>
                    </table>

                    <div class="buttons clearfix" style="margin-top: -20px;">
                        <a style="text-align: center" href="/checkout" name="checkout" class="btn" id="checkout"
                           type="submit">Checkout</a>
                        <a id="continue-shopping" href="/catalog/1">Continue Shopping</a>
                    </div>


                    <div class="row" style="display: none;">

                        <div class="extra-checkout-buttons span6">

                            <input name="goto_pp" type="image"
                                   src="https://www.paypalobjects.com/en_US/i/btn/btn_xpressCheckout.gif"
                                   value="paypal_express">

                        </div>
                    </div>
                    </form>
                <#else>
                    Your cart is Empty!
                </#if>
            </div>
        </div>


        <!-- End cart -->

    </div>


</section>
</#macro>