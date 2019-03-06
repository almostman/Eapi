package eapi

import grails.util.Environment

class BootStrap {

    def init = { servletContext ->
        switch (Environment.currentEnvironment){
            case Environment.DEVELOPMENT:

                def project = new Project(name: "恒升医疗管理系统", runName: "Imgive", description: "接口文档系统管理")
                project.save()
                if (project.hasErrors())
                    println project.errors

                def admin = new User(loginName:"admin",
                        password: "admin",
                        role: "admin",
                        project: project
                )
                admin.save();
                if (admin.hasErrors())
                    println admin.errors

                def cuijun = new User(loginName: "cuijun",
                        password: "cuijun",
                        role: "user",
                        project: project
                )
                cuijun.save();
                if (cuijun.hasErrors())
                    println cuijun.errors

                def module = new Module(name: "用户管理", description: "用户的CURD", project:project)
                module.save()
                if (module.hasErrors())
                    println module.errors

                def port1 = new Port(name: "添加用户", description: "添加用户管理", url: "http://localhost:8080/user/add", parameters: "name=cuijun&password=123456&age=23", replyData: "{status:200,msg:{name:23, age:23}}", backParameters: "name#用户名#&age#年龄#", module: module)
                port1.save()
                if (port1.hasErrors())
                    println port1.errors

                def port2 = new Port(name: "删除用户", description: "添加用户管理", url: "http://localhost:8080/user/delete", parameters: "id=23", replyData: "{status:200}", backParameters: "name#用户名#&age#年龄#", module: module)
                port2.save()
                if (port2.hasErrors())
                    println port2.errors

                def port3 = new Port(name: "用户列表", description: "添加用户管理", url: "http://localhost:8080/user/list", parameters: "max=10&sort=true", replyData: "{status:200,msg:{name:23, age:23}}", backParameters: "name#用户名#&age#年龄#", module: module)
                port3.save()
                if (port3.hasErrors())
                    println port3.errors




            break

            case Environment.PRODUCTION:

                break

        }
    }
    def destroy = {
    }
}
