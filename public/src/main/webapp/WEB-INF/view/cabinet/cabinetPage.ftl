<#-- @ftlvariable name="orders" type="java.util.List<com.springapp.mvc.common.hibernateEntity.Order>" -->
<#include "../template/template.ftl">
<@mainTemplate title="Личный кабинет"
scripts=["js/catalog.js"] headerBannerClass= ""/>
<#macro m_body>
<section id="content" class="clearfix">
    <div style="text-align: center" class="span12 expanded-message">
        <h2>Hello,${customer.getName()}</h2>

        <table>
            <tr>
                <th>City</th>
                <th>Post Code</th>
                <th>Address</th>
                <th>Comment</th>
                <th>Good</th>
                <th>Count</th>
                <th>Total Price</th>
            </tr>
            <#list orders as order>
                <tr>
                    <td>${order.getCity()}</td>
                    <td>${order.getPostCode()}</td>
                    <td>${order.getAddress()}</td>
                    <td>${order.getComments()}</td>
                    <td>${order.getOrderItem().getGood().getName()}</td>
                    <td>${order.getOrderItem().getCount()}</td>
                    <td>${order.getOrderItem().getCount()*order.getOrderItem().getGood().getPrice()}</td>
                </tr>
            </#list>
        </table>

        <div>
            <a href="/logout">Log out</a>
            <a href="/">На главную</a>
        </div>
    </div>
</section>
</#macro>