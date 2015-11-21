package me.codeboy.common.base.net;

import me.codeboy.common.base.net.core.CBConnection;

/**
 * http网络操作
 *
 * @author Yuedong Li
 */
public class CBHttp extends CBConnection {

    private static CBHttp httpClient;

    public static CBConnection getInstance() {
        if (httpClient == null) {
            httpClient = new CBHttp();
        }
        httpClient.resetSomeState();
        return httpClient;
    }

    public static CBConnection getNewInstance() {
        return new CBHttp();
    }
}