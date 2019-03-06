package eapi

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class UserController {


    def login = {}

    def logout = {
        flash.message = "GoodBye ${session.user.loginName}"
        session.user = null;
        redirect(action:'login')
    }

    def admin(){
        render view:'/admin'
    }

    def authenticate = {
        def user = User.findByLoginNameAndPassword(params.loginName, params.password.encodeAsSHA())

        if (user){
            //判断当前用户角色，user则跳转到接口预览页面，admin则跳转到管理后台页面
            if (user.role == "admin"){
                session.user = user;
                flash.message = "Hello ${user.loginName}!"
                //redirect(controller:'admin', action:'index') //管理后台页面
                redirect(action:'admin')
            }else {//
                session.user = user;
                flash.message = "Hello ${user.loginName}!"
                redirect(controller:'port', action:'list') //预览接口页面
            }
        }else {
            flash.message = "Sorry, ${params.loginName}. Please try again."
            redirect(action:'login')
        }
    }


    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond User.list(params), model:[userCount: User.count()]
    }

    def show(User user) {
        respond user
    }

    def create() {
        respond new User(params)
    }

    @Transactional
    def save(User user) {
        if (user == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (user.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond user.errors, view:'create'
            return
        }

        user.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), user.id])
                redirect user
            }
            '*' { respond user, [status: CREATED] }
        }
    }

    def edit(User user) {
        respond user
    }

    @Transactional
    def update(User user) {
        if (user == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (user.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond user.errors, view:'edit'
            return
        }

        user.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'user.label', default: 'User'), user.id])
                redirect user
            }
            '*'{ respond user, [status: OK] }
        }
    }

    @Transactional
    def delete(User user) {

        if (user == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        user.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'user.label', default: 'User'), user.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
