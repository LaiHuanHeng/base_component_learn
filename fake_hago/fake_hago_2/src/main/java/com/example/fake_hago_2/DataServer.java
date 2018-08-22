package com.example.fake_hago_2;

import java.util.ArrayList;
import java.util.List;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class DataServer {

    private static final String HTTPS_AVATARS1_GITHUBUSERCONTENT_COM_LINK = "https://avatars1.githubusercontent.com/u/7698209?v=3&s=460";
    private static final String CYM_CHAD = "CymChad";
    private static final String CHAY_CHAN = "ChayChan";

    private DataServer() {
    }

    public static List<String> getStrData() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            String str = HTTPS_AVATARS1_GITHUBUSERCONTENT_COM_LINK;
            if (i % 2 == 0) {
                str = CYM_CHAD;
            }
            list.add(str);
        }
        return list;
    }

    public static List<NormalMultipleEntity> getNormalMultipleEntities() {
        List<NormalMultipleEntity> list = new ArrayList<>();
        for (int i = 0; i <= 4; i++) {
            list.add(new NormalMultipleEntity(NormalMultipleEntity.SINGLE_IMG));
            list.add(new NormalMultipleEntity(NormalMultipleEntity.SINGLE_TEXT,CHAY_CHAN));
            list.add(new NormalMultipleEntity(NormalMultipleEntity.TEXT_IMG,CHAY_CHAN));
            list.add(new NormalMultipleEntity(NormalMultipleEntity.TEXT_IMG,CYM_CHAD));
            list.add(new NormalMultipleEntity(NormalMultipleEntity.TEXT_IMG_BIG,"H5 GAME"));
            list.add(new NormalMultipleEntity(NormalMultipleEntity.TEXT_IMG_BIG,"H5 GAME"));
        }
        return list;
    }

}
