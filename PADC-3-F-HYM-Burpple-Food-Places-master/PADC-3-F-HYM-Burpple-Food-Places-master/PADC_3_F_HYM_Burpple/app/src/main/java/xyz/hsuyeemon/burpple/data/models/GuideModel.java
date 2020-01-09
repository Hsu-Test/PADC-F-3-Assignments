package xyz.hsuyeemon.burpple.data.models;

import xyz.hsuyeemon.burpple.networks.dataagents.GuideDataAgent;
import xyz.hsuyeemon.burpple.networks.dataagents.GuideOkHttpDataAgent;
import xyz.hsuyeemon.burpple.networks.dataagents.RetrofitDataAgent;

/**
 * Created by Dell on 1/13/2018.
 */

public class GuideModel {

    private static GuideModel sGuideModel;
    private GuideDataAgent mDataAgent;

    private GuideModel(){
        mDataAgent= GuideOkHttpDataAgent.getObjInstance();
        //mDataAgent = RetrofitDataAgent.getsObjInstance();
    }
    public static GuideModel getObjInstance(){
        if(sGuideModel==null){
            sGuideModel= new GuideModel();
        }
        return sGuideModel;
    }
    public void loadGuides(){
        mDataAgent.loadGuides();
    }
}
