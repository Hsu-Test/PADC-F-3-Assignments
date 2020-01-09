package xyz.hsuyeemon.burpple.data.models;


import xyz.hsuyeemon.burpple.networks.dataagents.PromotionDataAgent;
import xyz.hsuyeemon.burpple.networks.dataagents.PromotionOkHttpDataAgent;
import xyz.hsuyeemon.burpple.networks.dataagents.RetrofitDataAgent;

/**
 * Created by Dell on 1/13/2018.
 */

public class PromotionModel {

    private static PromotionModel sPromotionModel;
    private PromotionDataAgent mDataAgent;
    private PromotionModel(){
        mDataAgent= PromotionOkHttpDataAgent.getObjInstance();
        //mDataAgent = RetrofitDataAgent.getsObjInstance();

    }

    public static PromotionModel getObjInstance(){
        if(sPromotionModel==null){
            sPromotionModel= new PromotionModel();
        }
        return sPromotionModel;
    }

    /**
     * load from network
     */
    public void loadPromotion(){
        mDataAgent.loadPromotion();
    }

}
