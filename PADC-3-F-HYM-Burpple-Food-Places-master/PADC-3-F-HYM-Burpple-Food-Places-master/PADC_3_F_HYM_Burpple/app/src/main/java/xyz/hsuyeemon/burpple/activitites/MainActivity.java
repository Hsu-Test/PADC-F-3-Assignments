package xyz.hsuyeemon.burpple.activitites;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.hsuyeemon.burpple.BurppleApp;
import xyz.hsuyeemon.burpple.R;
import xyz.hsuyeemon.burpple.adapters.BurppleGuideAdapter;
import xyz.hsuyeemon.burpple.adapters.FoodHighlightAdapter;
import xyz.hsuyeemon.burpple.adapters.NewlyOpenedShopAdapter;
import xyz.hsuyeemon.burpple.adapters.PromotionAdapter;
import xyz.hsuyeemon.burpple.adapters.TrendingVenuesAdapter;
import xyz.hsuyeemon.burpple.data.models.FeaturedModel;
import xyz.hsuyeemon.burpple.data.models.GuideModel;
import xyz.hsuyeemon.burpple.data.models.PromotionModel;
import xyz.hsuyeemon.burpple.events.LoadedFeaturedEvent;
import xyz.hsuyeemon.burpple.events.LoadedGuideEvent;
import xyz.hsuyeemon.burpple.events.LoadedPromotionEvent;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tool_bar)
    Toolbar toolbar;

    @BindView(R.id.rv_promotions)
    RecyclerView rvPromotions;

    @BindView(R.id.vp_food_highlight)
    ViewPager vpFoodHighlight;

    @BindView(R.id.rv_bupple_guides)
    RecyclerView rvBurppleGuides;

    @BindView(R.id.rv_newly_opened)
    RecyclerView rvNewlyOpened;

    @BindView(R.id.rv_trending_venues)
    RecyclerView rvTrendingVenues;

    private PromotionAdapter mPromotionAdapter;
    private FoodHighlightAdapter mFoodHighlightAdapter;
    private BurppleGuideAdapter mBurppleGuideAdapter;
    private NewlyOpenedShopAdapter mNewlyOpenedShopAdapter;
    private TrendingVenuesAdapter mTrendingVenuesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this,this);

        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        rvPromotions.setLayoutManager(linearLayoutManager);
        mPromotionAdapter=new PromotionAdapter();
        rvPromotions.setAdapter(mPromotionAdapter);

        mFoodHighlightAdapter=new FoodHighlightAdapter();
        vpFoodHighlight.setAdapter(mFoodHighlightAdapter);

        LinearLayoutManager burppleGuideslinearLayoutManager=new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        rvBurppleGuides.setLayoutManager(burppleGuideslinearLayoutManager);
        mBurppleGuideAdapter=new BurppleGuideAdapter();
        rvBurppleGuides.setAdapter(mBurppleGuideAdapter);

        LinearLayoutManager newlyOpenedlinearLayoutManager=new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        rvNewlyOpened.setLayoutManager(newlyOpenedlinearLayoutManager);
        mNewlyOpenedShopAdapter=new NewlyOpenedShopAdapter();
        rvNewlyOpened.setAdapter(mNewlyOpenedShopAdapter);

        LinearLayoutManager trendingVenuesLinearLayoutManager=new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        rvTrendingVenues.setLayoutManager(trendingVenuesLinearLayoutManager);
        mTrendingVenuesAdapter=new TrendingVenuesAdapter();
        rvTrendingVenues.setAdapter(mTrendingVenuesAdapter);

        FeaturedModel.getObjInstance().loadFeatured();
        PromotionModel.getObjInstance().loadPromotion();
        GuideModel.getObjInstance().loadGuides();

    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    /**
     * event listen
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPromotionLoaded(LoadedPromotionEvent event){
        Log.d(BurppleApp.LOG_TAG,"onPromotionLoaded"+event.getPromotionList().size());
        mPromotionAdapter.setPromotion(event.getPromotionList());

    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGuideLoaded(LoadedGuideEvent event){
        Log.d(BurppleApp.LOG_TAG,"onGuidedLoaded"+event.getGuideList().size());
        mBurppleGuideAdapter.setGuides(event.getGuideList());
    }


    @Subscribe(threadMode=ThreadMode.MAIN)
    public void onFeaturedLoaded(LoadedFeaturedEvent event){
        Log.d(BurppleApp.LOG_TAG, "onFeaturedLoaded" + event.getFeaturedList().size());
        mFoodHighlightAdapter.setFeatures(event.getFeaturedList());
    }




}
