import com.appunite.websocket.rx.RxWebSockets;
import com.appunite.websocket.rx.messages.RxEventBinaryMessage;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import rx.Subscription;

import java.util.Arrays;

public class Test {

    public static void main(String[] args) throws InterruptedException {
        final Request request = new Request.Builder()
                .get()
                .url("ws://54.171.181.77/feeds/race/")
                .build();
        final Subscription subscription = new RxWebSockets(new OkHttpClient(), request)
                .webSocketObservable()
                .subscribe(rxEvent -> {
                    if (rxEvent instanceof RxEventBinaryMessage) {
                        RxEventBinaryMessage rxEventBinaryMessage = (RxEventBinaryMessage) rxEvent;
                        byte[] message = rxEventBinaryMessage.message();
                        String s1 = Arrays.toString(message);
                        String s2 = new String(message);
                        System.out.println(s1);
                        System.out.println(s2);
                    }
                });

        Thread.sleep(5000);

        subscription.unsubscribe();
    }
}
