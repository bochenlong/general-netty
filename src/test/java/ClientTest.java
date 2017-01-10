import org.bochenlong.general.netty.BizMsgType;
import org.bochenlong.general.netty.NettyHelper;
import org.bochenlong.general.netty.NettyManager;
import org.bochenlong.general.netty.common.exception.RemoteException;
import org.bochenlong.general.netty.msg.MsgFactory;
import org.bochenlong.general.netty.msg.bean.NettyMsg;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by bochenlong on 17-1-9.
 */
public class ClientTest {
    public static void main(String[] args) throws Exception {
//        sendAsync();
        sendSync();
    }
    
    public static void sendAsync() {
        NettyMsg msg = MsgFactory.newMsgHeader(BizMsgType.AMOUNT);
        msg.setBody("hello , this is ip 9");
        NettyHelper.sendAsync(NettyManager.DEFAULT_HOST, msg);
    }
    
    public static void sendSync() throws RemoteException, ExecutionException, InterruptedException {
        NettyMsg msg = MsgFactory.newMsgHeader(BizMsgType.AMOUNT);
        msg.setBody("hello , this is ip 9");
        Future<NettyMsg> nettyMsgFuture = NettyHelper.sendSync(NettyManager.DEFAULT_HOST, msg);
        System.out.println(nettyMsgFuture.get());
    }
}
