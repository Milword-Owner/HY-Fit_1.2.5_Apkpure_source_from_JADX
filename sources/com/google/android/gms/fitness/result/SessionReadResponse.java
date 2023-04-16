package com.google.android.gms.fitness.result;

import androidx.annotation.RecentlyNonNull;
import com.google.android.gms.common.api.Response;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Session;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public class SessionReadResponse extends Response<SessionReadResult> {
    @RecentlyNonNull
    public List<Session> getSessions() {
        return ((SessionReadResult) getResult()).getSessions();
    }

    @RecentlyNonNull
    public List<DataSet> getDataSet(@RecentlyNonNull Session session, @RecentlyNonNull DataType dataType) {
        return ((SessionReadResult) getResult()).getDataSet(session, dataType);
    }

    @RecentlyNonNull
    public List<DataSet> getDataSet(@RecentlyNonNull Session session) {
        return ((SessionReadResult) getResult()).getDataSet(session);
    }

    @RecentlyNonNull
    public Status getStatus() {
        return ((SessionReadResult) getResult()).getStatus();
    }
}
