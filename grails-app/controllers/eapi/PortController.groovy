package eapi

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class PortController {

    def outjson(Integer id){
        println id
        render Port.get(id).replyData
    }

    def list (Integer max){
        params.max = Math.min(max ?: 10, 100)
        respond Port.list(params), model:[portCount: Port.count()]
    }

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Port.list(params), model:[portCount: Port.count()]
    }

    def show(Port port) {
        respond port
    }

    def create() {
        respond new Port(params)
    }

    @Transactional
    def save(Port port) {
        if (port == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (port.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond port.errors, view:'create'
            return
        }

        port.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'port.label', default: 'Port'), port.id])
                redirect port
            }
            '*' { respond port, [status: CREATED] }
        }
    }

    def edit(Port port) {
        respond port
    }

    @Transactional
    def update(Port port) {
        if (port == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (port.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond port.errors, view:'edit'
            return
        }

        port.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'port.label', default: 'Port'), port.id])
                redirect port
            }
            '*'{ respond port, [status: OK] }
        }
    }

    @Transactional
    def delete(Port port) {

        if (port == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        port.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'port.label', default: 'Port'), port.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'port.label', default: 'Port'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
