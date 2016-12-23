package CodeRaguet.fraser;

import com.google.api.client.auth.oauth2.StoredCredential;
import com.google.api.client.util.store.DataStore;
import com.google.api.client.util.store.DataStoreFactory;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

public class ENVDataStore<V extends Serializable> implements DataStore<V> {

    @Override
    public DataStoreFactory getDataStoreFactory() {
        return null;
    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public int size() throws IOException {
        return 0;
    }

    @Override
    public boolean isEmpty() throws IOException {
        return false;
    }

    @Override
    public boolean containsKey(String key) throws IOException {
        return false;
    }

    @Override
    public boolean containsValue(V value) throws IOException {
        return false;
    }

    @Override
    public Set<String> keySet() throws IOException {
        return null;
    }

    @Override
    public Collection<V> values() throws IOException {
        return null;
    }

    @Override
    public V get(String key) throws IOException {
        return (V) new StoredCredential().setRefreshToken(System.getenv("REFRESH_TOKEN"));
    }

    @Override
    public DataStore<V> set(String key, V value) throws IOException {
        return null;
    }

    @Override
    public DataStore<V> clear() throws IOException {
        return null;
    }

    @Override
    public DataStore<V> delete(String key) throws IOException {
        return null;
    }
}
