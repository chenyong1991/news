package com.example.news.utils;

/**
 * Created by c8469 on 2016/12/14.
 */

public interface Constant {

    String BASE_URL = "http://v.juhe.cn/toutiao/index?";

    /*String TOP_NEWS_QUERY_STRING = "type=top&key=9398ab53c96408b94f9def1a4a1cc5e6";
    String SHEHUI_NEWS_QUERY_STRING = "type=shehui&key=9398ab53c96408b94f9def1a4a1cc5e6";
    String GUONEI_NEWS_QUERY_STRING = "type=guonei&key=9398ab53c96408b94f9def1a4a1cc5e6";
    String GUOJI_NEWS_QUERY_STRING = "type=guoji&key=9398ab53c96408b94f9def1a4a1cc5e6";
    String YULE_NEWS_QUERY_STRING = "type=yule&key=9398ab53c96408b94f9def1a4a1cc5e6";
    String TIYU_NEWS_QUERY_STRING = "type=tiyu&key=9398ab53c96408b94f9def1a4a1cc5e6";
    String JUNSHI_NEWS_QUERY_STRING = "type=junshi&key=9398ab53c96408b94f9def1a4a1cc5e6";
    String KEJI_NEWS_QUERY_STRING = "type=keji&key=9398ab53c96408b94f9def1a4a1cc5e6";
    String CAIJING_NEWS_QUERY_STRING = "type=caijing&key=9398ab53c96408b94f9def1a4a1cc5e6";
    String SHISHANG_NEWS_QUERY_STRING = "type=shishang&key=9398ab53c96408b94f9def1a4a1cc5e6";*/

    String[] URLS = {"type=top&key=9398ab53c96408b94f9def1a4a1cc5e6","type=shehui&key=9398ab53c96408b94f9def1a4a1cc5e6",
                    "type=guonei&key=9398ab53c96408b94f9def1a4a1cc5e6","type=guoji&key=9398ab53c96408b94f9def1a4a1cc5e6",
                    "type=yule&key=9398ab53c96408b94f9def1a4a1cc5e6","type=tiyu&key=9398ab53c96408b94f9def1a4a1cc5e6",
                    "type=junshi&key=9398ab53c96408b94f9def1a4a1cc5e6","type=keji&key=9398ab53c96408b94f9def1a4a1cc5e6",
                    "type=caijing&key=9398ab53c96408b94f9def1a4a1cc5e6","type=shishang&key=9398ab53c96408b94f9def1a4a1cc5e6"};

    int WHAT_NENS_REQUEST = 0;

    /*
    类型,,top(头条，默认),shehui(头条),guonei(国内),guoji(国际),
    yule(娱乐),tiyu(体育)junshi(军事),keji(科技),caijing(财经),shishang(时尚)
     */
    /**
     * 新闻列表标题
     */
    String[] TITLES = {"头条","社会","国内","国际","娱乐","体育","军事","科技","财经","时尚"};


}
