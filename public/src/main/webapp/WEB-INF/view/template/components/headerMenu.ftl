<#-- @ftlvariable name="listMenu" type="java.util.List<com.springapp.mvc.common.MenuInfo>" -->
<#-- @ftlvariable name="categories" type="java.util.List<com.springapp.mvc.common.hibernateEntity.Categories>" -->
<#-- @ftlvariable name="customer" type="com.springapp.mvc.common.hibernateEntity.Customer" -->
<section id="nav">
    <div class="inner-right">
        <nav class="main">
            <ul class="horizontal unstyled clearfix">
            <#list listMenu as menu>
                <li>
                    <a href="${menu.link}" class="current"><span>${menu.name}</span></a>
                </li>
            </#list>
            <#if customer??>
                <#if customer.isAdmin()>
                    <li>
                        <a href="/admin" class="login"><span>${customer.getName()}</span></a>
                    </li>
                <#else>
                    <li>
                        <a href="/cabinet" class="login"><span>${customer.getName()}</span></a>
                    </li>
                </#if>
            <#else>
                <li>
                    <a href="/login" class="login"><span>log in</span></a>
                </li>
            </#if>
            </ul>

            <section id="secondary-nav">
                <div class="inner-right">
                    <ul class="horizontal unstyled clearfix">
                    <#list categories as category>
                        <li>
                            <a style="color:#000;" href="/catalog/${category}" class=""><span>${category}</span></a>
                            /&nbsp;
                        </li>
                    </#list>
                    </ul>

                </div>
            </section>

        </nav>
    <#include "headerMobileMenu.ftl" />
    </div>
</section>