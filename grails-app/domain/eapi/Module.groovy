package eapi

class Module {

    String name;
    String description;

    static constraints = {
        name(blank: false)
        description(widget:'textarea')
    }

    static hasMany = [ports:Port]

    static belongsTo = [project:Project]

    @Override
    String toString() {
        name
    }
}

