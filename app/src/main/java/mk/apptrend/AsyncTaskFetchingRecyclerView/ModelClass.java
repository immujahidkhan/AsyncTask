package mk.apptrend.AsyncTaskFetchingRecyclerView;

/**
 * Created by Mujahid on 2/1/2018.
 */

public class ModelClass {
    String Id,Name;

    public ModelClass(String id, String name) {
        Id = id;
        Name = name;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
