<#-- @ftlvariable name="goods" type="java.util.List<com.springapp.mvc.common.hibernateEntity.GoodInfo>" -->
<#include "../template/template.ftl">
<#if category??>
    <@mainTemplate title="${category}" scripts=["js/catalog.js"] headerBannerClass= ""/>
<#else>
    <@mainTemplate title="Catalog - empty" scripts=["js/catalog.js"] headerBannerClass= ""/>
</#if>
<#macro m_body>
<section id="content" class="clearfix">

    <div id="collection">

        <div class="row">
            <div class="span12">
                <#if category??>
                    <#include "components/catalogBreamcrub.ftl" />
                    <div class="row products">
                        <#if goods?has_content>
                            <ul>
                                <#include "components/goodItem.ftl">
                        <#list goods as good>
                                <@goodItem good=good itemClass=((good_index+1)%3==0)?string("last", "") />
                            </#list>
                            </ul>
                        <#else>
                            <div class="span12 expanded-message">
                                <h2>No products found</h2>
                            </div>

                        </#if>
                    </div>
                <#else>
                    <div class="span12 expanded-message">
                        <h2>No products found</h2>
                    </div>
                </#if>
                <#if customer?? && customer.isAdmin()>
                    <a href="/good/add-page">Add goods</a>
                </#if>
                <script src="/resources/js/cbpViewModeSwitch.js" type="text/javascript"></script>
                <script src="/resources/js/classie.js" type="text/javascript"></script>
            </div>
        </div>
    </div>
</#macro>