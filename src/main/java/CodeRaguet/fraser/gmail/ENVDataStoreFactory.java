package CodeRaguet.fraser.gmail;

import com.google.api.client.util.store.DataStore;
import com.google.api.client.util.store.DataStoreFactory;

import java.io.IOException;
import java.io.Serializable;

class ENVDataStoreFactory implements DataStoreFactory {
    private final String refreshToken;

    ENVDataStoreFactory(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    @Override
    public <V extends Serializable> DataStore<V> getDataStore(String id) throws IOException {
        DataStore<V> dataStore = new ENVDataStore<>(refreshToken);
        return dataStore;
    }
}
