<#-- @ftlvariable name="customers" type="java.util.List<com.springapp.mvc.common.hibernateEntity.Customer>" -->
<#include "../template/template.ftl">
<@mainTemplate title="Личный кабинет Админа"
scripts=["js/catalog.js"] headerBannerClass= ""/>
<#macro m_body>
<section id="content" class="clearfix">
    <div style="text-align: center" class="span12 expanded-message">
        <h1>Hello, admin</h1>
        <h2>Редактировать юзеров:</h2>
        <table style="margin-left: 40px;">
            <thead>
            <tr>
                <th class="user">Login</th>
                <th class="email">Email</th>
                <th class="enabled">Enabled</th>
                <th class="qty">Role</th>
                <th class="remove">&nbsp;</th>
            </tr>
            </thead>
            <tbody>
                <#list customers as customer>
                <tr>
                    <td>${customer.getName()}</td>
                    <td>${customer.getEmail()}</td>
                    <td>
                        <form id="cartform" action="/admin/user/update" method="post"><input type="hidden"
                                                                                             name="customer_id"
                                                                                             value="${customer.getId()}"/><input
                                name="enabled" type="checkbox" onclick="this.form.submit();"
                                <#if (customer.getEnabled() = true)>checked="checked"</#if>/></form>
                    </td>
                    <td class="remove"><#if (customer.getRole() != 'ROLE_ADMIN')><a
                            href="/admin/user/remove?customer_id=${customer.getId()}">Remove</#if></a>
                    </td>
                </tr>
                </#list>
            </tbody>
        </table>
        <div>
            <a href="/logout">Log out</a>
            <a href="/">На главную</a>
        </div>
    </div>
</section>
</#macro>