package com.example.shenjack.zhihudailyreader.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ShenJack on 2017/6/6.
 */

public class TodayPosts implements Serializable {
    private String date;


    @SerializedName("stories")
    private List<StoriesBean> storiesX;
    @SerializedName("top_stories")
    private List<TopStoriesBean> top_storiesX;

    public List<StoriesBean> getStoriesX() {
        return storiesX;
    }

    public void setStoriesX(List<StoriesBean> storiesX) {
        this.storiesX = storiesX;
    }

    public List<TopStoriesBean> getTop_storiesX() {
        return top_storiesX;
    }

    public void setTop_storiesX(List<TopStoriesBean> top_storiesX) {
        this.top_storiesX = top_storiesX;
    }


    public static class TopStoriesBean {
        /**
         * image : https://pic3.zhimg.com/v2-912ed77e8effec5588070848cd5a48c2.jpg
         * type : 0
         * id : 9465654
         * ga_prefix : 060907
         * title : 「裸贷」事件很糟糕，把学生贷款全部禁掉是个好主意吗？
         */


        private String image;
        private int type;
        private int id;
        private String ga_prefix;
        private String title;
        /**
         * body : <div class="main-wrap content-wrap">
         <div class="headline">

         <div class="img-place-holder"></div>



         </div>

         <div class="content-inner">




         <div class="question">
         <h2 class="question-title">校园贷存在哪些问题？应如何解决？</h2>

         <div class="answer">

         <div class="meta">
         <img class="avatar" src="http://pic1.zhimg.com/82d2a64137b4301c1f73458edd473a98_is.jpg">
         <span class="author">顾剑，</span><span class="bio">学而不思则罔，思而不学则殆。我既不学也不思，看来安全了。</span>
         </div>

         <div class="content">
         <p><strong>校园贷不仅仅是校园贷，某种意义上是代表着一种未来的方向。</strong>堵不如疏的道理大家都懂。</p>
         <p>在国内监管并不是独立的事情，不是每个受监管的对象都是&ldquo;敌人&rdquo;只能用政策去调控去防范风险，完全可以配合&ldquo;讲政治&rdquo;的国家队所承担的社会责任促进发展。</p>
         <p><strong>1、大环境向消费型社会转型。</strong>学校相对独立于社会，但是必然受到社会影响，何况现在由于移动互联网的广泛普及，校园想封闭也封闭不起来。无论如何都会有消费需求的话，提供低息贷款，不让本来有可能拯救的群体打着滚掉进高息小额贷的坑里是很有意义的，国家队的校园贷可以考虑整体与长远，在这方面只从每笔业务的角度看待学生群体缺乏第一还款来源看待并不合适，望远镜角度下的视角肯定不同于显微镜下。</p>
         <p><strong>2、建立个人征信体系。</strong>八家预备役征信机构没拿到征信牌照的新闻相信都看过，征信管理局万存知局长所提到的未来发展思路和现在征信发展所存在的问题应该也有所了解，我就不多写了。即将踏上社会的大学生有良好的征信记录并不是一件坏事，提高征信记录覆盖面也有利于征信业务的发展与完善，和消费型社会转型是相辅相成的事情。</p>
         <p><strong>3、金融常识进校园。</strong>我国在金融方面对消费者的保护其实是存在很大问题的。政策管理不全面执行力度不足只是一个方面，对消费者的金融知识教育严重缺乏也是问题之一，绝大部分消费者都是在摸索。例如 13 年余额宝爆发，很多人才发现有货币基金这种家庭投资方式。而对借高息贷款的后果了解不足也是之前校园贷问题爆发的原因之一。前面说了，学校相对封闭但又与社会有紧密的联系，由国有商业银行在校园内组织金融常识教育是个不错的选择，在学校防范了声誉风险的同时商业银行也能宣传自身业务塑造自身形象，是个双赢的选择。</p>
         <p><strong>4、银行一类户开户可能伴随一生。</strong>只有付出没有收益在商业社会不是能够持续执行的模式，而银行目前在校园贷方面的收益就是可收获终身性质客户，也因此可以适度放眼长远而不是只看且只能看短期收益。在银行个人账户体系改变之前，银行获客主要依靠新发卡，也因此对客户不会采取长远的运营手段。在账户体系改革之后，大学生作为潜在的高收益群体，在提供适当的承接性产品后是可以稳固毕业后一类户的，在这个角度上对商业银行长远发展也是有利的，特别是正在转型的当下。在这里有个细分市场是本地型高校，学生在学校所在省就业较多，完全具有深耕的潜力。</p>
         <p>当然，商业银行重新进入校园贷市场也必然会遇到各种问题，放款时缺乏数据支撑、只能发放信用贷款且缺乏使用场景只能发放现金贷、缺乏有效的催收手段都是存在的。在这个角度目前有部分银行在这方面的设想是对学校进行信贷投入获取话语权，借助校园相对封闭的特点将学生日常付费行为结算体系拿下，以校园卡配合各类支付产品形成封闭的生态系统并提供给学生非现金贷的使用场景，最终根据数据对校园贷进行一定程度上的风控。</p>
         </div>
         </div>


         <div class="view-more"><a href="http://www.zhihu.com/question/60495516">查看知乎讨论<span class="js-question-holder"></span></a></div>

         </div>


         </div>
         </div>
         * image_source : Yestone.com 版权图片库
         * share_url : http://daily.zhihu.com/story/9465654
         * js : []
         * images : ["https://pic1.zhimg.com/v2-b98f34eccc332df4d745a6711fe58eb8.jpg"]
         * css : ["http://news-at.zhihu.com/css/news_qa.auto.css?v=4b3e3"]
         */


        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }


    }
}
