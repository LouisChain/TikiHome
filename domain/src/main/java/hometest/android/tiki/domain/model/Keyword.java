package hometest.android.tiki.domain.model;

import java.util.List;

public interface Keyword {
    int getId(); // Provide for saving to local, in this case server just return an array, so we will generate an id

    List<String> getData();// List keywords
}
