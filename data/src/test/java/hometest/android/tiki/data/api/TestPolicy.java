package hometest.android.tiki.data.api;

import android.security.NetworkSecurityPolicy;

import org.robolectric.annotation.Implementation;
import org.robolectric.annotation.Implements;

public class TestPolicy {
    @Implements(NetworkSecurityPolicy.class)
    public static class MyNetworkSecurityPolicy {
        @Implementation
        public static NetworkSecurityPolicy getInstance() {
            try {
                Class<?> shadow = MyNetworkSecurityPolicy.class.forName("android.security.NetworkSecurityPolicy");
                return (NetworkSecurityPolicy) shadow.newInstance();
            } catch (Exception e) {
                throw new AssertionError();
            }
        }

        @Implementation
        public boolean isCleartextTrafficPermitted() {
            return true;
        }

        @Implementation
        public boolean isCleartextTrafficPermitted(String hostname) {
            return true;
        }
    }
}
