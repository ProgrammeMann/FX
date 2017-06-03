<#-- @ftlvariable name="cart" type="com.springapp.mvc.common.Cart" -->
<#macro goodItem good itemClass="">
<#-- @ftlvariable name="good" type="com.springapp.mvc.common.hibernateEntity.GoodInfo" -->
<div class="product span3">

    <div class="circle sale">Sale</div>

    <div class="image">
        <a href="/good/${good.id}">
            <img src="/resources/img/goods/${good.imageUrl}" alt="${good.name}">
        </a>
    </div>


    <div class="details">
        <a href="/good/${good.id}" class="clearfix">
            <h4 class="title">${good.name!}</h4>
            <span class="vendor">The Legends League</span>

            <span class="price">$${good.price?number}.00</span>
            <#if customer??>
                <#if (cart.cartRows)?? && cart.containsGoodId(good.id)>
                    <a class="button item_add cbp-vm-icon cbp-vm-add" style="background: rgb(280, 124, 83)"
                       href="/cart">Go
                        in cart</a>
                <#else>
                    <form id="add-item-form" action="/cart/add?goodId=${good.id}&customerId=${customer.getId()}"
                          method="post">
                        <button class="button item_add cbp-vm-icon cbp-vm-add js_addToCart">
                            Add to cart
                        </button>
                    </form>
                </#if>
            </#if>
        </a>
    </div>

</div>
</#macro>