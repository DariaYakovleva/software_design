import org.json.JSONObject;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import com.xebialabs.restito.server.StubServer;
import org.glassfish.grizzly.http.Method;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.junit.Assert;
import org.junit.Test;

import java.io.UncheckedIOException;
import java.util.function.Consumer;

import static com.xebialabs.restito.builder.stub.StubHttp.whenHttp;
import static com.xebialabs.restito.semantics.Action.status;
import static com.xebialabs.restito.semantics.Action.stringContent;
import static com.xebialabs.restito.semantics.Condition.method;

/**
 * Created by Daria on 08.10.2016.
 */
public class DownloaderTest {

    String tag = "daria";
    Calendar cal = Calendar.getInstance();
    Date curDate = cal.getTime();
    private final Downloader downloader = new Downloader(curDate, tag);

    @Test
    public void getResponse() throws Exception {
        JSONObject response = downloader.getResponse();
        if (response == null) throw new NullPointerException("Response form downloader is null");
    }

    private static final int PORT = 32453;

    @Test
    public void downloadByUrl() {
        withStubServer(PORT, s -> {
            whenHttp(s)
                    .match(method(Method.GET).startsWithUri("/ping"))
                    .then(stringContent("pong"));
            String result = downloader.downloadByUrl("http://localhost:" + PORT + "/ping");
            Assert.assertEquals("pong\n", result);
        });
    }

    @Test(expected = UncheckedIOException.class)
    public void downloadByUrlWithNotFoundError() {
        withStubServer(PORT + 1, s -> {
            whenHttp(s)
                    .match(method(Method.GET).startsWithUri("/ping"))
                    .then(status(HttpStatus.NOT_FOUND_404));

            downloader.downloadByUrl("http://localhost:" + PORT + "/ping");
        });
    }

    private void withStubServer(int port, Consumer<StubServer> callback) {
        StubServer stubServer = null;
        try {
            stubServer = new StubServer(port).run();
            callback.accept(stubServer);
        } finally {
            if (stubServer != null) {
                stubServer.stop();
            }
        }
    }
}
