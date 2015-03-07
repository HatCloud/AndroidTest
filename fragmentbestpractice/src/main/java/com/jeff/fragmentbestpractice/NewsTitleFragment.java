package com.jeff.fragmentbestpractice;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jeff on 15/2/15.
 */
public class NewsTitleFragment extends Fragment implements AdapterView.OnItemClickListener {

    private ListView newsTitleListView;
    private List<News> newsList;
    private NewsAdapter adapter;
    private boolean isTwoPane;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        newsList = getNews(); //初始化新闻信息
        adapter = new NewsAdapter(activity, R.layout.news_item, newsList);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_title_frag, container, false);
        newsTitleListView = (ListView) view.findViewById(R.id.news_title_list_view);
        newsTitleListView.setAdapter(adapter);
        newsTitleListView.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity().findViewById(R.id.news_content) != null) {
            isTwoPane = true;
        } else {
            isTwoPane = false; // 找不到news_content_layout布局时。为单页模式。
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        News news = newsList.get(position);
        if (isTwoPane) {
            NewsContentFragment newsContentFragment = (NewsContentFragment) getFragmentManager().findFragmentById(R.id.news_content_fragment);
            newsContentFragment.refresh(news.getTitle(), news.getContent());
        } else {
            NewsContentActivity.actionStart(getActivity(), news.getTitle(), news.getContent());
        }
    }

    private List<News> getNews() {
        List<News> newsList = new ArrayList<News>();
        News news1 = new News();
        news1.setTitle("Facebook发布系列新广告，除了“连接”还是“连接”");
        news1.setContent("Facebook最近发布了一系列新广告。这些新广告的主题包括两个部分：第一，是“Our Friends”，一如既往强调了Facebook在朋友和社交中起到的连接作用；第二，是Internet.org项目，这个主题的广告主要围绕“发展中国家如果能访问互联网了可以做些什么”这一核心展开拍摄。\n" +
                "\n" +
                "\n" +
                "Facebook并非首次尝试在广告里呼吁用互联网完成人与人之间的连接。早在2013年，#ConnectTheWorld 活动就强调了人类不同文化的差异性，以及人们社交中相似的地方。\n" +
                "\n" +
                "同样是在2013年，Mark Zuckerberg 宣布了 Internet.org 的成立，一个致力于将互联网扩散到全世界所有地方的组织。在成立 Internet.org时，小札曾表示，Internet.org 不是为了商业而建立，更多的是为了人权，为了让世界上没有网络的地区享受到互联网服务。\n" +
                "\n" +
                "尽管看起来很美，但Internet.org也饱受了一些非议。Theverge的报道指出，Internet.org只允许用户免费连接到Facebook和其他一些白名单网站，除非用户愿意花一笔数据费才可以享受其他网络资源。也就是说，Internet.org并非真的要把互联网带到全世界，更多的可能只是为了Facebook自己的传播，同时顺带向发展中国家偏远地区开放一小部分网络而已。\n" +
                "\n" +
                "Facebook这两个广告主题，不论是聚焦于朋友社交的“Our Friend”，还是把网络带到全世界的Internet.org，除了“连接”其实还是“连接”。Facebook一直试图让人们相信，自己和这个世界之间唯一相连的那条线，正是Facebook亲手画上去的。");
        newsList.add(news1);
        News news2 = new News();
        news2.setTitle("来！用VR的方式观看 2015 年的 NBA 全明星赛");
        news2.setContent("今天，NBA 全明星将在纽约麦迪逊花园举行，作为一个中国观众，如果想近距离观看这个一年一度的篮球盛宴，除了CCTV之外，今年可能有了一个新的选择，那就是三星在去年12月刚刚发售的虚拟现实头盔 Gear VR。\n" +
                "\n" +
                "三星去年为它的虚拟现实头盔提供了 Milk VR 的服务，提供360度全景视频，内容涉及体育、音乐。此次三星与 NBA 全明星大赛的合作，将包括三分球大赛，扣篮大赛以及东西部全明星赛的现场直播。据官方透露，目前已经在场内不同的位置安放了多个摄像头，将通过不同的机位保证用户的“身临其境”之感。\n" +
                "\n" +
                "根据 WIRED 的消息，NBA 全明星赛正在与多家 VR 公司进行合作，不过想看现场直播的话，只能够通过三星的虚拟现实头盔 Gear VR。同时，Jeff Marsilio，NBA 的全球媒体运营官也表示用户近期可以免费在三星的 VR 商店里观看比赛回放。\n" +
                "\n" +
                "NBA 一直对 VR 保持着非常开放的态度，他们之前曾经与 NextVR 合作，未来也表示希望在更多的比赛中尝试 VR，以及获得更加多样的合作形式。");
        newsList.add(news2);
        return newsList;
    }
}
