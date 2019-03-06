
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'port.label', default: 'Port')}" />
        <title>接口列表</title>
        <asset:stylesheet src="jsonformat.css"/>
    </head>
    <body>
        <a href="#list-port" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

        <div id="list-port" class="content scaffold-list" role="main">
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>

            <table class="table table-hover">
                <thead>
                <tr>
                    <th>名称</th>
                    <th>Url</th>
                    <th>参数</th>
                    <th>响应数据</th>
                    <th>所属模块</th>
                </tr>
                </thead>
                <tbody>
                <g:each status="i" in="${portList}" var="it">
                    <tr data-toggle="modal" data-target="#myModal-${i}" onclick="Process('${it.replyData}', ${i})">
                        <td>${it.name}</td>
                        <td>${it.url}</td>
                        <td>${it.parameters}</td>
                        <td>${it.replyData}</td>
                        <td>${it.module.name}</td>
                    </tr>

                    <!-- Modal START -->
                    <div class="modal fade" id="myModal-${i}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                        <div class="modal-dialog modal-lg">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                                    <h4 class="modal-title" id="myModalLabel">${it.name}</h4>
                                </div>
                                <div class="modal-body">

                                    <blockquote>
                                        <code><h3>&nbsp;${it.url}</h3></code>
                                        <footer><var>${it.description}</var></footer>
                                    </blockquote>

                                    <pre><var><h4><g:parametersRemove data="${it.parameters}"></g:parametersRemove></h4></var></pre>
                                    <pre><var><h3><g:parametersFormat data="${it.parameters}"></g:parametersFormat></h3></var></pre>
                                    <div id="Canvas-${i}" class="Canvas well resizable" style="overflow:auto;margin-bottom:0px;"></div>
                                    <pre><var><h7>使用模拟数据：</h7><g:link action="outjson" id="${it.id}"><g:imitateLink data="${it.url}"></g:imitateLink></g:link></var></pre>
                                    <pre><var><h3><g:backParametersFormat data="${it.backParameters}"></g:backParametersFormat></h3></var></pre>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- Modal END -->
                </g:each>

                </tbody>
            </table>


            <div class="pagination">
                <g:paginate total="${portCount ?: 0}" />
            </div>
        </div>



    </div>


    </body>
</html>