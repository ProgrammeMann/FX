<#include "../template/template.ftl">
<@mainTemplate title="Авторизация" />
<#macro m_body>
<section class="clearfix" id="content">
    <div style="text-align: center" class="account-in">
        <div class="container">
            <h3>Account</h3>
            <div class="col-md-7">
                <#if error?has_content>
                    <div style="color: red;">Error! Please, check your email and password!</div>
                </#if>
                <form name="authForm" id="authForm" action="j_spring_security_check" method="post">
                    <div style="margin: 3px">
                        <span>Email*</span><br>
                        <input type="text" name="j_username"/>
                    </div>
                    <div style="margin: 3px">
                        <span class="pass">Password*</span><br>
                        <input type="password" name="j_password"/>
                    </div>
                    <div>
                        <input id="remember_me" name="_spring_security_remember_me" type="checkbox"/>
                        <label for="remember_me" class="inline">Remember me</label>
                    </div>
                    <input style="margin: 3px" type="submit" value="Login"/>

                    <div><a href="/reg" style="margin-left: 15px;">Create an account</a></div>
                </form>
            </div>
            <div class="col-md-5 left-account "></div>
            <div class="clearfix"></div>
        </div>
    </div>
</section>
</#macro>