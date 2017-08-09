package com.example.penny.accessibilityyysp.http;

import com.example.penny.accessibilityyysp.model.ELMModel;

import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by penny on 2017/5/16 0016.
 */

public interface ApiService {
    /**
     * 上传数据
     * @return
     */
    @POST(URLConstans.test)
    Observable<BaseResponse<String>> uploadData(@Body ELMModel pModel);


}
