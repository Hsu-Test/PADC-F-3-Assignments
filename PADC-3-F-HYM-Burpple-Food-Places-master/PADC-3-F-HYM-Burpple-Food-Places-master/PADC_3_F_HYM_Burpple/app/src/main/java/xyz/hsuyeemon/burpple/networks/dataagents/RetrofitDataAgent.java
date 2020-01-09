package xyz.hsuyeemon.burpple.networks.dataagents;

/**
 * Created by Dell on 1/11/2018.
 */

        import com.google.gson.Gson;
        import org.greenrobot.eventbus.EventBus;

        import java.util.concurrent.TimeUnit;

        import okhttp3.OkHttpClient;
        import retrofit2.Call;
        import retrofit2.Callback;
        import retrofit2.Response;
        import retrofit2.Retrofit;
        import retrofit2.converter.gson.GsonConverterFactory;
        import xyz.hsuyeemon.burpple.events.LoadedFeaturedEvent;
        import xyz.hsuyeemon.burpple.events.LoadedGuideEvent;
        import xyz.hsuyeemon.burpple.events.LoadedPromotionEvent;
        import xyz.hsuyeemon.burpple.networks.BurppleApi;
        import xyz.hsuyeemon.burpple.networks.responses.GetFeaturedResponse;
        import xyz.hsuyeemon.burpple.networks.responses.GetGuideResponse;
        import xyz.hsuyeemon.burpple.networks.responses.GetPromotionResponse;


public class RetrofitDataAgent implements GuideDataAgent,FeaturedDataAgent,PromotionDataAgent {

    private static RetrofitDataAgent sObjInstance;

    private BurppleApi mBurppleApi;

    public static RetrofitDataAgent getsObjInstance() {
        if (sObjInstance==null){
            sObjInstance=new RetrofitDataAgent();
        }
        return sObjInstance;
    }

    private RetrofitDataAgent(){
        OkHttpClient httpClient = new OkHttpClient.Builder() //1.
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://padcmyanmar.com/padc-3/mm-news/apis/v1/")
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .client(httpClient)
                .build();

        mBurppleApi=retrofit.create(BurppleApi.class);


    }






    @Override
    public void loadPromotion() {
        Call<GetPromotionResponse> getPromotionResponseCall=mBurppleApi.loadPromotion(1,"b002c7e1a528b7cb460933fc2875e916");
        getPromotionResponseCall.enqueue(new Callback<GetPromotionResponse>() {
            @Override
            public void onResponse(Call<GetPromotionResponse> call, Response<GetPromotionResponse> response) {
                GetPromotionResponse getPromotionResponse=response.body();
                if (getPromotionResponse.getPromotion()==null) {
                    LoadedPromotionEvent event = new LoadedPromotionEvent(getPromotionResponse.getPromotion());
                    EventBus.getDefault().post(event);
                }
            }

            @Override
            public void onFailure(Call<GetPromotionResponse> call, Throwable t) {

            }

        });
    }

    @Override
    public void loadGuides() {

        Call<GetGuideResponse> getGuideResponseCall=mBurppleApi.loadGuide(1,"b002c7e1a528b7cb460933fc2875e916");
        getGuideResponseCall.enqueue(new Callback<GetGuideResponse>() {
            @Override
            public void onResponse(Call<GetGuideResponse> call, Response<GetGuideResponse> response) {
                GetGuideResponse getGuideResponse=response.body();
                if (getGuideResponse.getGuides()==null) {
                    LoadedGuideEvent event = new LoadedGuideEvent(getGuideResponse.getGuides());
                    EventBus.getDefault().post(event);
                }
            }

            @Override
            public void onFailure(Call<GetGuideResponse> call, Throwable t) {

            }

        });


    }
    @Override
    public void loadFeatured() {
        //Call<GetFeaturedResponse> featuredResponseCall=mBurppleApi.loadFeatured(1,"b002c7e1a528b7cb460933fc2875e916");
        Call<GetFeaturedResponse> featuredResponseCall= mBurppleApi.loadFeatured(1,"b002c7e1a528b7cb460933fc2875e916");
        featuredResponseCall.enqueue(new Callback<GetFeaturedResponse>() {
            @Override
            public void onResponse(Call<GetFeaturedResponse> call, Response<GetFeaturedResponse> response) {
                GetFeaturedResponse getFeaturedResponse=response.body();
                if (getFeaturedResponse.getFeatureds()==null) {
                    LoadedFeaturedEvent event = new LoadedFeaturedEvent(getFeaturedResponse.getFeatureds());
                    EventBus.getDefault().post(event);
                }
            }

            @Override
            public void onFailure(Call<GetFeaturedResponse> call, Throwable t) {

            } });
    }
}

