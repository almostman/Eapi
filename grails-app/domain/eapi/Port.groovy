package eapi

class Port {

    String name; //接口名称
    String description; //接口描述
    String url; //当在后台设置使用模式后，接口展示页面将动态显示真实接口和模拟接口
    String parameters; //参数域，接口参数描述，采用字段描述
    String replyData; //响应数据，用于模拟接口使用
    String backParameters; //响应数据，參數說明
    static constraints = {
        name()
        description(widget:'textarea', blank: true)
        url(blank: false)
        parameters(widget:'textarea', blank: true)
        replyData(widget:'textarea', blank: true)
        backParameters(widget:'textarea', blank: true)
    }

    static belongsTo = [module:Module]

}




