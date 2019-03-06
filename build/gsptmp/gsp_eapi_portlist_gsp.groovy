import grails.plugins.metadata.GrailsPlugin
import org.grails.gsp.compiler.transform.LineNumber
import org.grails.gsp.GroovyPage
import org.grails.web.taglib.*
import org.grails.taglib.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_eapi_portlist_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/port/list.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',5,['gsp_sm_xmlClosingForEmptyTag':("/"),'name':("layout"),'content':("main")],-1)
printHtmlPart(1)
invokeTag('set','g',6,['var':("entityName"),'value':(message(code: 'port.label', default: 'Port'))],-1)
printHtmlPart(1)
createTagBody(2, {->
createClosureForHtmlPart(2, 3)
invokeTag('captureTitle','sitemesh',7,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',7,[:],2)
printHtmlPart(1)
invokeTag('stylesheet','asset',8,['src':("jsonformat.css")],-1)
printHtmlPart(3)
})
invokeTag('captureHead','sitemesh',9,[:],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
invokeTag('message','g',11,['code':("default.link.skip.label"),'default':("Skip to content&hellip;")],-1)
printHtmlPart(5)
if(true && (flash.message)) {
printHtmlPart(6)
expressionOut.print(flash.message)
printHtmlPart(7)
}
printHtmlPart(8)
loop:{
int i = 0
for( it in (portList) ) {
printHtmlPart(9)
expressionOut.print(i)
printHtmlPart(10)
expressionOut.print(it.replyData)
printHtmlPart(11)
expressionOut.print(i)
printHtmlPart(12)
expressionOut.print(it.name)
printHtmlPart(13)
expressionOut.print(it.url)
printHtmlPart(13)
expressionOut.print(it.parameters)
printHtmlPart(13)
expressionOut.print(it.replyData)
printHtmlPart(13)
expressionOut.print(it.module.name)
printHtmlPart(14)
expressionOut.print(i)
printHtmlPart(15)
expressionOut.print(it.name)
printHtmlPart(16)
expressionOut.print(it.url)
printHtmlPart(17)
expressionOut.print(it.description)
printHtmlPart(18)
invokeTag('parametersRemove','g',53,['data':(it.parameters)],-1)
printHtmlPart(19)
invokeTag('parametersFormat','g',54,['data':(it.parameters)],-1)
printHtmlPart(20)
expressionOut.print(i)
printHtmlPart(21)
createTagBody(3, {->
invokeTag('imitateLink','g',56,['data':(it.url)],-1)
})
invokeTag('link','g',56,['action':("outjson"),'id':(it.id)],3)
printHtmlPart(22)
invokeTag('backParametersFormat','g',57,['data':(it.backParameters)],-1)
printHtmlPart(23)
i++
}
}
printHtmlPart(24)
invokeTag('paginate','g',73,['total':(portCount ?: 0)],-1)
printHtmlPart(25)
})
invokeTag('captureBody','sitemesh',82,[:],1)
printHtmlPart(26)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1480672314730L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
