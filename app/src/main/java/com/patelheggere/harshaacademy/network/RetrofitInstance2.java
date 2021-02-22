package com.patelheggere.harshaacademy.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.security.cert.CertificateException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitInstance2 {
        // private static final String BASE_URL = "https://maps.googleapis.com/maps/";

        //private static final String BASE_URL = "http://patelheggere.epizy.com/KuduchiAPI/";
        //private static final String BASE_URL = "http://www.prajeev.net/API/";
        // private static final String BASE_URL = "http://kusavinibalaga.org/hamsa/endpoints/";
        private static final String BASE_URL = " https://kgis.ksrsac.in:9000/genericwebservices/ws/";


        private static Retrofit retrofit = null;

        public  void setClient() {

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            int cacheSize = 10 * 1024 * 1024; // 10 MB
            Cache cache = new Cache(new File("goldfarm"), cacheSize);

            OkHttpClient client = new OkHttpClient.Builder().cache(cache).addInterceptor(interceptor).build();

            OkHttpClient.Builder builder2 = getUnsafeOkHttpClient();
            client = builder2.cache(cache).addInterceptor(interceptor).build();
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client)
                    .build();
            this.retrofit = retrofit;


        }

        public  Retrofit getClient() {
            return retrofit;
        }
        public static OkHttpClient.Builder getUnsafeOkHttpClient() {

            try {
                // Create a trust manager that does not validate certificate chains
                final TrustManager[] trustAllCerts = new TrustManager[]{
                        new X509TrustManager() {
                            @Override
                            public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                            }

                            @Override
                            public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                            }

                            @Override
                            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                                return new java.security.cert.X509Certificate[]{};
                            }
                        }
                };

                // Install the all-trusting trust manager
                final SSLContext sslContext = SSLContext.getInstance("SSL");
                sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

                // Create an ssl socket factory with our all-trusting manager
                final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

                OkHttpClient.Builder builder = new OkHttpClient.Builder();

                builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
                builder.hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                });
                return builder;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
    }