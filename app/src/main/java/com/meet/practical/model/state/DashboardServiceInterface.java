package com.meet.practical.model.state;

import com.meet.practical.model.entity.response.PracticalResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface DashboardServiceInterface {

    @GET("users?_format=json&access-token=dKVZXNBCMq_RUeyKfmDg8RurKAmWJPL38pUK")
    Observable<PracticalResponse> getPracticalInfo();
//
}
