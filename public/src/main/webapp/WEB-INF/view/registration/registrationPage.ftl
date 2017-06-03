<#assign form=JspTaglibs["http://www.springframework.org/tags/form"]>
<#include "../template/template.ftl">
<@mainTemplate title="Регистрация" />
<#macro m_body>
<section style="text-align: left" class="clearfix" id="content">
    <div class="account-in">
        <div style="width: 500px;margin: 0 auto" class="container">
            <@form.form commandName="regForm" action="/reg" acceptCharset="UTF-8" method="post">
                <div class="register-top-grid">
                    <h2>PERSONAL INFORMATION</h2>
                    <@form.errors path="*" cssStyle="color: red;" />
                    <div style="margin: 4px;">
                        <span>First Name<label>*</label></span><br>
                        <@form.input path="firstName" />
                        <@form.errors path="firstName" cssStyle="color: red;" />
                    </div>
                    <div style="margin: 4px;">
                        <span>Email Address<label>*</label></span><br>
                        <@form.input path="email" />
                        <@form.errors path="email" cssStyle="color: red;" />
                    </div>
                    <div style="margin: 4px;">
                        <span>Phone<label>*</label></span><br>
                        <@form.input path="phone" />
                        <@form.errors path="phone" cssStyle="color: red;" />
                    </div>
                    <div class="clearfix"></div>
                    <i class="news-letter">
                        <label class="">
                            <@form.checkbox path="signIn" />
                            <i> </i>Agree
                            <@form.errors path="signIn" cssStyle="color: red;" />
                        </label>
                    </i>
                </div>
                <div class="register-bottom-grid">
                    <h2>LOGIN INFORMATION</h2>

                    <div style="margin: 4px;">
                        <span>Password<label>*</label></span><br>
                        <@form.password path="password" />
                        <@form.errors path="password" cssStyle="color: red;" />
                    </div>
                    <div style="margin: 4px;">
                        <span>Confirm Password<label>*</label></span><br>
                        <@form.password path="confirmPassword" />
                        <@form.errors path="confirmPassword" cssStyle="color: red;" />
                    </div>
                    <div class="clearfix"></div>
                </div>
                <div class="clearfix"></div>
                <div class="register-but">
                    <input type="submit" value="submit" class="btn btn-default">
                    <div class="clearfix"></div>
                </div>
            </@form.form>
        </div>
    </div>
</section>
</#macro>