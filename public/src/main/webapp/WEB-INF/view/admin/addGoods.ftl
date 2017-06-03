<#-- @ftlvariable name="customers" type="java.util.List<com.springapp.mvc.common.hibernateEntity.Customer>" -->
<#include "../template/template.ftl">
<#assign form=JspTaglibs["http://www.springframework.org/tags/form"]>
<@mainTemplate title="Добавление товара"
scripts=["js/catalog.js"] headerBannerClass= ""/>
<#macro m_body>
<section id="content" class="clearfix">
    <div style="text-align: center" class="span12 expanded-message">
        <h1>Hello, admin</h1>
        <h2>Добавить склад</h2>
        <@form.form commandName="storeAddForm" action="/good/add-store" acceptCharset="UTF-8" method="post">
            <table class="table table-bordered tbl-checkout">
                <tbody>
                <tr>
                    <td>City</td>
                    <td>
                        <@form.input path="city" />
                        <@form.errors path="city" cssStyle="color: red;" />
                    </td>
                    <td>Address</td>
                    <td>
                        <@form.input path="address" />
                        <@form.errors path="address" cssStyle="color: red;" />
                    </td>
                </tr>
                </tbody>
            </table>
            <input type="submit" value="submit" class="btn btn-default">
        </@form.form>
        <h2>Добавить товар</h2>
        <@form.form commandName="goodAddForm" action="/good/add" acceptCharset="UTF-8" method="post">
            <table class="table table-bordered tbl-checkout">
                <tbody>
                <tr>
                    <td>Name</td>
                    <td>
                        <@form.input path="name" />
                        <@form.errors path="name" cssStyle="color: red;" />
                    </td>
                    <td>Description</td>
                    <td>
                        <@form.input path="description" />
                        <@form.errors path="description" cssStyle="color: red;" />
                    </td>
                </tr>
                <tr>
                    <td>Category</td>
                    <td>
                        <@form.input path="category" />
                        <@form.errors path="category" cssStyle="color: red;" />
                    </td>
                    <td>Price</td>
                    <td>
                        <@form.input path="price" />
                        <@form.errors path="price" cssStyle="color: red;" />
                    </td>
                </tr>
                <tr>
                    <td>imageUrl</td>
                    <td>
                        <@form.input path="imageUrl" />
                    <@form.errors path="imageUrl" cssStyle="color: red;" />
                    </td>
                </tr>
                <tr>
                    <td>Store</td>
                    <td>
                        <#if stores??>
                            <#list stores as store>
                                <p><@form.radiobutton path="storeId" value="${store.getId()}" />${store.getCity()}
                                    ,${store.getAddress()}</p>
                            </#list>
                        <#else>
                            Add STORE first
                        </#if>
                        <@form.errors path="storeId" cssStyle="color: red;" />
                    </td>
                    <td>Count</td>
                    <td><@form.input path="count" />
                    <@form.errors path="count" cssStyle="color: red;" /></td>
                </tr>
                </tbody>
            </table>
            <input type="submit" value="submit" class="btn btn-default">
        </@form.form>
    </div>
</section>
</#macro>