package city.genkoku.restricthostname.listener;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.event.PlayerHandshakeEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.List;
import java.util.logging.Logger;

public class PingListener implements Listener {
    static ProxyServer proxy = ProxyServer.getInstance();
    static Logger log = proxy.getLogger();

    private final List<String> hostNames;

    public PingListener(List<String> hostNames) {
        this.hostNames = hostNames;
    }

    @EventHandler
    public void onPlayerHandshake(PlayerHandshakeEvent event) {
        String hostName = event.getHandshake().getHost();
        if (!hostNames.contains(hostName)) {
            log.info("接続許可外のHostNameでの接続試行が確認されました。ホスト名：" + hostName);
            event.getHandshake().setRequestedProtocol(-1);
        }
    }
}
