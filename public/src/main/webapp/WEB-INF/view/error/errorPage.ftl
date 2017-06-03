<#include "../template/template.ftl">
<@mainTemplate title="Error"
scripts=["js/catalog.js"] headerBannerClass= ""/>
<#macro m_body>
<section id="content" class="clearfix">
    <div style="text-align: center" class="span12 expanded-message">
        <h1>${errorMsg}</h1>

        <div>
            <a href="/">На главную</a>
        </div>
    </div>
</section>
</#macro>