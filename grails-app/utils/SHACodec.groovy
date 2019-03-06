import java.security.MessageDigest

/**
 * Created by Administrator on 2016/11/29.
 */
class SHACodec {
    static encode = {target ->
        MessageDigest md = MessageDigest.getInstance("SHA")
        md.update(target.getBytes('UTF-8'))
        return new String(md.digest()).encodeAsBase64()
    }
}
