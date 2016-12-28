package CodeRaguet.fraser;

import com.google.api.client.util.store.DataStore;
import com.google.api.client.util.store.DataStoreFactory;

import java.io.IOException;
import java.io.Serializable;

public class ENVDataStoreFactory implements DataStoreFactory {
    private final String refreshToken;

    public ENVDataStoreFactory(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public ENVDataStoreFactory() {
        refreshToken = ENV.REFRESH_TOKEN.value();
    }

    @Override
    public <V extends Serializable> DataStore<V> getDataStore(String id) throws IOException {
        DataStore<V> dataStore = new ENVDataStore<>(refreshToken);
        return dataStore;
    }
}
