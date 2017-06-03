<#-- @ftlvariable name="cart" type="com.springapp.mvc.common.Cart" -->
<#import "/spring.ftl" as spring />
<#include "../template/template.ftl">
<@mainTemplate title="Checkout" scripts=["cart.js"]/>
<#macro m_body>
<section id="content" class="clearfix">
    <form action="/checkout/add" method="post">
        <div class="container main-container">
            <div class="row">
                <!-- Cart -->
                <div class="col-lg-12 col-md-12 col-sm-12">
                    <div class="col-lg-12 col-sm-12">
                        <span class="title">CHECKOUT</span>
                    </div>
                    <div class="col-lg-12 col-sm-12 hero-feature">
                        <div class="table-responsive">
                            <table class="table table-bordered tbl-checkout">
                                <tbody>
                                <tr>
                                    <td>Address</td>
                                    <td colspan="3">
                                        <@spring.formInput "checkoutForm.address" 'class="form-control"'/>
                                <@spring.showErrors '<br>'/>
                                    </td>
                                </tr>
                                <tr>
                                    <td>City</td>
                                    <td>
                                        <@spring.formInput "checkoutForm.city" 'class="form-control"'/>
                                <@spring.showErrors '<br>'/>
                                    </td>
                                    <td>Post Code</td>
                                    <td>
                                        <@spring.formInput "checkoutForm.postCode" 'class="form-control"'/>
                                <@spring.showErrors '<br>'/>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Comment</td>
                                    <td colspan="3">
                                        <@spring.formTextarea "checkoutForm.comments" 'class="form-control"'/>
                                <@spring.showErrors '<br>'/>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                        <div class="table-responsive">
                            <table class="table table-bordered tbl-cart">
                                <thead>
                                <tr>
                                    <td>Product Name</td>
                                    <td class="td-qty">Quantity</td>
                                    <td>Unit Price</td>
                                    <td>Sub Total</td>
                                    <td>Action</td>
                                </tr>
                                </thead>
                                <tbody>
                                    <#assign cartRows = cart.getCartRows()>
                                    <#list cartRows as cartRow>
                                        <#assign good=cartRow.getGood()>
                                    <tr>
                                        <td>${good.getName()}</td>
                                        <td class="text-center">${cartRow.getCount()}</td>
                                        <td class="price">$ ${good.getPrice()}</td>
                                        <td>$ ${cartRow.getCount()*good.getPrice()}</td>
                                    </tr>
                                    </#list>
                                </tbody>
                            </table>
                        </div>
                        <div class="btn-group btns-cart">
                            <button type="submit" class="btn btn-primary">Continue</button>
                        </div>

                    </div>
                </div>

                <!-- End Cart -->

            </div>
        </div>
    </form>
</section>
</#macro>