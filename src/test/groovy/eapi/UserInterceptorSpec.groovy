package eapi


import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(UserInterceptor)
class UserInterceptorSpec extends Specification {

    def setup() {
    }

    def cleanup() {

    }

    void "Test user interceptor matching"() {
        when:"A request matches the interceptor"
            withRequest(controller:"user")

        then:"The interceptor does match"
            interceptor.doesMatch()
    }
}
