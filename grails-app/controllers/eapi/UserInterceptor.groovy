package eapi


class UserInterceptor {

    UserInterceptor(){
        match(controller:"user").excludes(action:'login').excludes(action:'authenticate').excludes(action:'logout')
    }

    boolean before() {
        debug()
        auth()
        true
    }

    def debug(){
        println "DEBUG:${getControllerName()+"/"+getActionName()} called."
        println "DEBUG:${params}"
    }

    def auth(){

        if (!session.user){
            redirect controller: "user", action:"login"
            return false
        }

        /*if (!session.user?.admin){
            flash.message = "Tsk tsk-admins only"
            redirect(controller: "race", action: "index")

            return false
        }*/
    }

    boolean after() { true }

    void afterView() {
        // no-op
    }
}
