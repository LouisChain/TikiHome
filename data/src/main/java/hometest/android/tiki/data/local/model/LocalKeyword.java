package hometest.android.tiki.data.local.model;

import com.raizlabs.android.dbflow.StringUtils;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import hometest.android.tiki.data.local.MyDatabase;
import hometest.android.tiki.domain.model.Keyword;

@Table(database = MyDatabase.class)
public class LocalKeyword extends BaseModel implements Keyword {
    @PrimaryKey
    int id;

    @Column
    String data;


    public LocalKeyword() {

    }

    public LocalKeyword(int id, List<String> data) {
        this.id = id;
        this.data = "";
        for (int i = 0; i < data.size(); i++) {
            this.data += data.get(i) + ",";
        }
        this.data = this.data.substring(0, this.data.length() - 1);
    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public List<String> getData() {
        if (StringUtils.isNotNullOrEmpty(data)) {
            String[] data = this.data.split(",");
            return Arrays.asList(data);
        }
        return new ArrayList<>();
    }
}
