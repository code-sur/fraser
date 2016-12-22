package CodeRaguet.fraser;

import com.google.api.client.util.store.DataStore;
import com.google.api.client.util.store.DataStoreFactory;

import java.io.IOException;
import java.io.Serializable;

public class ENVDataStoreFactory implements DataStoreFactory {
    @Override
    public <V extends Serializable> DataStore<V> getDataStore(String id) throws IOException {
        DataStore<V> dataStore = new ENVDataStore<>();
        return dataStore;
    }
}
