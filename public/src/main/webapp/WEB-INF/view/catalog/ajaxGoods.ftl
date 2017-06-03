<#include "components/goodItem.ftl">
<#if goods?? && goods?has_content>
    <#list goods as good>
        <@goodItem good=good itemClass=((good_index+1)%3==0)?string("last", "") />
    </#list>
</#if>