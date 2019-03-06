package eapi

class User {

    String loginName;
    String password;
    String role = "user"

    static constraints = {
        loginName(blank: false, nullable: false, unique: true)
        password(blank: false, password:true)
        role(inList: ["admin", "user"])
    }

    static belongsTo = [project: Project]

    static transients = ['admin']
    boolean isAdmin(){
        return role == "admin"
    }

    def beforeInsert = {
        password = password.encodeAsSHA()
    }

    @Override
    String toString() {
        loginName
    }
}
