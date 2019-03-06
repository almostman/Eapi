

class LoginTagLib {
    //static defaultEncodeAs = [taglib:'html']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]

    def loginControl = {
        if(request.getSession(false) && session.user){
            out << "Hello ${session.user.loginName}"
            out << """${link(action: "logout", controller: "user"){"Logout"}}"""
        }else {
            out << """${link(action: "login", controller: "user"){"Login"}}"""
        }
    }
}
