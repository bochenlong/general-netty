import org.bochenlong.general.netty.BizMsgType;
import org.bochenlong.general.netty.NettyHelper;
import org.bochenlong.general.netty.NettyManager;
import org.bochenlong.general.netty.msg.MsgFactory;
import org.bochenlong.general.netty.msg.bean.NettyMsg;

/**
 * Created by bochenlong on 17-1-9.
 */
public class ClientTest {
    public static void main(String[] args) {
        sendAsync();
    }
    
    public static void sendAsync() {
        NettyMsg msg = MsgFactory.newMsgHeader(BizMsgType.AMOUNT);
        msg.setBody("hello , this is ip 9");
        NettyHelper.sendAsync(NettyManager.DEFAULT_HOST, msg);
    }
}
