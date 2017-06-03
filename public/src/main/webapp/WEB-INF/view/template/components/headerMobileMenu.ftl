<#-- @ftlvariable name="listMenu" type="java.util.List<com.springapp.mvc.common.MenuInfo>" -->
<nav class="mobile clearfix">

    <select name="main_navigation" id="main_navigation" class="fl">

    <#list listMenu as menu>
        <option value="${menu.link}" selected="selected">${menu.name}</option>
    </#list>
    </select>
</nav>