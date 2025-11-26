package com.micromerger.ssms.api;

import com.google.common.net.HttpHeaders;
import com.micromerger.ssms.main.SSMS;
import com.micromerger.ssms.utils.BasePreferenceHelper;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/* loaded from: classes2.dex */
public class AuthTokenInterceptor implements Interceptor {
    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        BasePreferenceHelper basePreferenceHelper = new BasePreferenceHelper(SSMS.getAppContext());
        Request request = chain.request();
        if (request.url().toString().contains("/MobileApp/")) {
            request = request.newBuilder().header(HttpHeaders.AUTHORIZATION, "Bearer " + basePreferenceHelper.getAuthToken()).build();
        }
        return chain.proceed(request);
    }
}
