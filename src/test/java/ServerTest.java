import org.bochenlong.general.netty.BizMsgType;
import org.bochenlong.general.netty.NettyHelper;
import org.bochenlong.general.netty.msg.MsgHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by bochenlong on 17-1-9.
 */
public class ServerTest  {
    private Logger logger = LoggerFactory.getLogger(ServerTest.class);
    
    public static void main(String[] args) {
        MsgHelper.addMsgType(BizMsgType.AMOUNT.getType());
        NettyHelper.startServer();
    }
    
    
    
    
}