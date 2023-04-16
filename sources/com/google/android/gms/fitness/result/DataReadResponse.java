package com.google.android.gms.fitness.result;

import androidx.annotation.RecentlyNonNull;
import com.google.android.gms.common.api.Response;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.data.Bucket;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public class DataReadResponse extends Response<DataReadResult> {
    @RecentlyNonNull
    public DataSet getDataSet(@RecentlyNonNull DataType dataType) {
        return ((DataReadResult) getResult()).getDataSet(dataType);
    }

    @RecentlyNonNull
    public DataSet getDataSet(@RecentlyNonNull DataSource dataSource) {
        return ((DataReadResult) getResult()).getDataSet(dataSource);
    }

    @RecentlyNonNull
    public List<DataSet> getDataSets() {
        return ((DataReadResult) getResult()).getDataSets();
    }

    @RecentlyNonNull
    public List<Bucket> getBuckets() {
        return ((DataReadResult) getResult()).getBuckets();
    }

    @RecentlyNonNull
    public Status getStatus() {
        return ((DataReadResult) getResult()).getStatus();
    }
}
