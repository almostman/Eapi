package eapi

import groovy.json.JsonOutput
import org.grails.web.util.WebUtils

class ParamFormatTagLib {
    //static defaultEncodeAs = [taglib:'html']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]

    def parametersFormat = {attrs ->
        //name=cuijun/姓名/&password=cuijun/密码/&age=23/年龄/
        def arr = attrs.data.split("&")

        def ser = arr.collect{
            it.getAt(0..it.indexOf("=")-1) +"   "+ (it.indexOf("#") > -1 ? it.substring(it.indexOf("#")+1, it.length()-1) : '未解释')
        }

        out << ser
    }

    def backParametersFormat = {attrs ->
        //name=cuijun/姓名/&password=cuijun/密码/&age=23/年龄/
        def arr = attrs.data.split("&")

        def ser = arr.collect{
            it.getAt(0..it.indexOf("#")-1) +"   "+ (it.indexOf("#") > -1 ? it.substring(it.indexOf("#")+1, it.length()-1) : '未解释')
        }

        out << ser
    }

    def parametersRemove = {attrs ->
        def arr = attrs.data.split("&")
        def ser = arr.collect{
            it.substring(0, it.indexOf("#") > -1 ? it.indexOf("#"):it.length())
        }

        out << ser.toString().replaceAll(", ", "&").replaceAll("([|])", "-")
    }
    def imitateLink = {attrs ->
        def arr = attrs.data
        def suffix = arr
        if(arr.contains("http://") || arr.contains("https://")){
            arr = arr.substring(arr.indexOf(":")+3)
        }

        arr = arr.substring(arr.indexOf("/"))
        arr = WebUtils.retrieveGrailsWebRequest().getBaseUrl()+arr

        out << arr
    }

}
