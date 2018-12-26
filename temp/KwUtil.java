package com.negen.kwsp;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;

public class KwUtil {

    private String userName;
    /**
     *  验证酷我歌曲是否失效
     */
    static boolean validateKwMusic(String songName, String singerName){
        boolean isOnline = false;
        songName = songName.contains(" ")?songName.replaceAll(" "," ").trim():songName;
        singerName = singerName.contains(" ")?singerName.replaceAll(" "," ").trim():singerName;
        try {
            String url = String.format("http://sou.kuwo.cn/ws/NSearch?type=music&key=%s+%s",songName,singerName);
            Document document = Jsoup.connect(url).get();
            Element div = document.getElementsByClass("list").get(0);
            Elements lis = div.getElementsByTag("li");
            if(null != lis){
                //有搜索结果
                for (Element li:lis) {
                    String cSongName = li.getElementsByClass("m_name").get(0)
                            .getElementsByTag("a").get(0).attr("title");
                    String cSingerName = li.getElementsByClass("s_name").get(0)
                            .getElementsByTag("a").get(0).attr("title");
                    cSongName = cSongName.contains(" ")?
                            cSongName.replaceAll(" "," ").trim():cSongName;
                    cSingerName = cSingerName.contains(" ")?
                            cSingerName.replaceAll(" "," ").trim():cSingerName;
                    if(cSongName.equals(songName) && cSingerName.equals(singerName)){
                        //存在搜索歌曲  返回true
                        isOnline = true;
                        break;
                    }
                }
            }else{
                //无搜索结果
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return isOnline;
    }
//    public static void main(String[] args) {
//        System.out.println(validateKwMusic("How The Story Goes","Venke Knutson"));
//    }
}
