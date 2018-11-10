package hometest.android.tiki.data.api.model;

import java.util.ArrayList;
import java.util.List;

import hometest.android.tiki.domain.model.Keyword;

public class ApiKeyword implements Keyword {

    ArrayList<String> data;

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public List<String> getData() {
        return this.data;
    }
}
