package eapi

class Project {

    public static int STATUS_OPEN = 0;
    public static int STATUS_CLOSE = 1;

    String name; //名称
    String runName; //运行名称
    String description; //描述
    Boolean opened = 0; //是否对外开放

    static constraints = {
        name(blank: false)
        runName(blank: false)
        description();
        opened();
    }


    static hasMany = [modules:Module, users:User]


    @Override
    String toString() {
        name
    }
}
