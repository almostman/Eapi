<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
        <title>Login</title>
    </head>
    <body>
        <a href="#create-user" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

        <div id="create-user" class="content scaffold-create" role="main">
            <h1>Login</h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.user}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.user}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
            <g:form action="authenticate">
                <fieldset class="form">
                    <div class="dialog">
                        <table>
                            <tbody>
                                <tr class="prop">
                                    <td valign="top" class="name">
                                        <label for="login">Login:</label>
                                    </td>
                                    <td valign="top">
                                        <input type="text" id="loginName" name="loginName" />
                                    </td>
                                </tr>
                                <tr class="prop">
                                    <td valign="top" class="name">
                                        <label for="password">Password:</label>
                                    </td>
                                    <td valign="top">
                                        <input type="password" id="password" name="password" />
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </fieldset>
                <fieldset class="buttons">
                    <input type="submit"  />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
