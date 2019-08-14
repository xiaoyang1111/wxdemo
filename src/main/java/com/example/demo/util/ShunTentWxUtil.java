package com.example.demo.util;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.example.demo.entity.menu.MenuButton;
import com.example.demo.entity.menu.SubButton;
import com.example.demo.entity.menu.ViewChildButton;

import java.util.ArrayList;
import java.util.Map;

/**
 * 顺腾微信工具包
 */
public class ShunTentWxUtil {

    /**
     * 创建微信菜单
     */
    public static void createMenu(){
        MenuButton menuButton = new MenuButton();
        menuButton.setButton(new ArrayList<>());
        /**********************关于我们*****************************/
        SubButton subButton = new SubButton();
        subButton.setName("关于我们");//第一个菜单
        subButton.setSub_button(new ArrayList<>());
        ViewChildButton viewChildButton = new ViewChildButton();
        viewChildButton.setUrl("http://mp.weixin.qq.com/s?__biz=Mzg5NTE1MjEzNg==&mid=2247483665&idx=1&sn=2b3f42736d60c42a223910fdd75694d0&chksm=c015f876f7627160e78761316b182864d40aabacc7b5432eaa61feb727e513bef81556b0066e&scene=18#wechat_redirect");
        viewChildButton.setName("关于我们");
        viewChildButton.setType("view");//二级菜单
        subButton.getSub_button().add(viewChildButton);
        ViewChildButton viewChildButton1 = new ViewChildButton();
        viewChildButton1.setUrl("http://mp.weixin.qq.com/s?__biz=Mzg5NTE1MjEzNg==&mid=2247483665&idx=1&sn=2b3f42736d60c42a223910fdd75694d0&chksm=c015f876f7627160e78761316b182864d40aabacc7b5432eaa61feb727e513bef81556b0066e&scene=18#wechat_redirect");
        viewChildButton1.setName("典型案例");
        viewChildButton1.setType("view");//二级菜单
        subButton.getSub_button().add(viewChildButton1);
        menuButton.getButton().add(subButton);
        /**********************家电买卖*****************************/
        SubButton subButton1 = new SubButton();
        subButton1.setName("典型案例");//第二个菜单
        subButton1.setSub_button(new ArrayList<>());
        ViewChildButton viewChildButton2 = new ViewChildButton();
        viewChildButton2.setUrl("http://mp.weixin.qq.com/s?__biz=Mzg5NTE1MjEzNg==&mid=2247483665&idx=1&sn=2b3f42736d60c42a223910fdd75694d0&chksm=c015f876f7627160e78761316b182864d40aabacc7b5432eaa61feb727e513bef81556b0066e&scene=18#wechat_redirect");
        viewChildButton2.setName("新旧家电");
        viewChildButton2.setType("view");//二级菜单
        subButton1.getSub_button().add(viewChildButton2);
        ViewChildButton viewChildButton3 = new ViewChildButton();
        viewChildButton3.setUrl("http://mp.weixin.qq.com/s?__biz=Mzg5NTE1MjEzNg==&mid=2247483665&idx=1&sn=2b3f42736d60c42a223910fdd75694d0&chksm=c015f876f7627160e78761316b182864d40aabacc7b5432eaa61feb727e513bef81556b0066e&scene=18#wechat_redirect");
        viewChildButton3.setName("典型案例");
        viewChildButton3.setType("view");//二级菜单
        subButton1.getSub_button().add(viewChildButton3);
        menuButton.getButton().add(subButton1);
        /**********************生活小常识*****************************/
        SubButton subButton2 = new SubButton();
        subButton2.setName("生活小常识");//第二个菜单
        subButton2.setSub_button(new ArrayList<>());
        ViewChildButton viewChildButton4 = new ViewChildButton();
        viewChildButton4.setUrl("http://mp.weixin.qq.com/s?__biz=Mzg5NTE1MjEzNg==&mid=2247483665&idx=1&sn=2b3f42736d60c42a223910fdd75694d0&chksm=c015f876f7627160e78761316b182864d40aabacc7b5432eaa61feb727e513bef81556b0066e&scene=18#wechat_redirect");
        viewChildButton4.setName("生活小常识");
        viewChildButton4.setType("view");//二级菜单
        subButton.getSub_button().add(viewChildButton4);
        ViewChildButton viewChildButton5 = new ViewChildButton();
        viewChildButton5.setUrl("http://mp.weixin.qq.com/s?__biz=Mzg5NTE1MjEzNg==&mid=2247483665&idx=1&sn=2b3f42736d60c42a223910fdd75694d0&chksm=c015f876f7627160e78761316b182864d40aabacc7b5432eaa61feb727e513bef81556b0066e&scene=18#wechat_redirect");
        viewChildButton5.setName("系统拍照发图");
        viewChildButton5.setType("pic_sysphoto");//二级菜单
        viewChildButton5.setKey("pic_sysphoto");
        subButton2.getSub_button().add(viewChildButton5);
        ViewChildButton viewChildButton6 = new ViewChildButton();
        viewChildButton6.setUrl("http://mp.weixin.qq.com/s?__biz=Mzg5NTE1MjEzNg==&mid=2247483665&idx=1&sn=2b3f42736d60c42a223910fdd75694d0&chksm=c015f876f7627160e78761316b182864d40aabacc7b5432eaa61feb727e513bef81556b0066e&scene=18#wechat_redirect");
        viewChildButton6.setName("优惠区");
        viewChildButton6.setType("view");//二级菜单
        subButton2.getSub_button().add(viewChildButton6);
        ViewChildButton viewChildButton7 = new ViewChildButton();
        viewChildButton7.setUrl("http://li-19940823.6655.la/jeefast/index.html#modules/platform/user_order.html");
        viewChildButton7.setName("顺腾后台");
        viewChildButton7.setType("view");//二级菜单
        subButton2.getSub_button().add(viewChildButton7);
        ViewChildButton viewChildButton8 = new ViewChildButton();
        viewChildButton8.setUrl("http://li-19940823.6655.la/jeefast/login.html");
        viewChildButton8.setName("服务订单");
        viewChildButton8.setType("view");//二级菜单
        subButton2.getSub_button().add(viewChildButton8);
        menuButton.getButton().add(subButton2);
        System.out.println(JSON.toJSONString(menuButton));
        WxUtil.createMenu(menuButton);
    }

    /**
     * 关注事件处理
     * @return 回复内容
     */
    public static String   event(Map<String,String> map){
        String response = null;
        if(null != map.get("Event") && StringUtils.equals("subscribe",map.get("Event"))){
            //关注事件
            response = "欢迎关注顺腾微信公众号，回复以下数字获取相关服务"
            +"\n1，广告设计安装"
            +"\n2，房屋装修，补漏"
            +"\n3，水电，卫浴，灯具安装"
            +"\n4，家电安装，清洗，维修"
            +"\n5，商用炉具，厨具的安装保养"
            +"\n6，商用冷热水工程的安装及保养";
        }
        if(null != map.get("MsgType") && StringUtils.equals("text",map.get("MsgType"))){
            response = "数字回复有误，请回复相关服务数字";
           //消息回复
           if(StringUtils.equals("1",map.get("Content"))) {
               response = "广告设计安装请联系:\n何师傅，联系电话:18316679357或\n汤师傅，联系电话:18826611541";
           }
            if(StringUtils.equals("2",map.get("Content"))) {
                response = "房屋装修，补漏请联系:\n黄师傅，联系电话:18718553525或\n杜师傅，联系电话:18688141861";
            }
            if(StringUtils.equals("3",map.get("Content"))) {
                response = "水电，卫浴，灯具安装请联系:\n罗师傅，联系电话:14715922523或\n汤师傅，联系电话:18826611541";
            }
            if(StringUtils.equals("4",map.get("Content"))) {
                response = "家电安装，清洗，维修请联系:\n何师傅，联系电话:18316679357或\n汤师傅，联系电话:18826611541";
            }
            if(StringUtils.equals("5",map.get("Content"))) {
                response = "商用炉具，厨具的安装保养请联系:\n张师傅，联系电话:13226566151或\n李师傅，联系电话:18607639812";
            }
            if(StringUtils.equals("6",map.get("Content"))) {
                response = "商用冷热水工程的安装及保养请联系:\n何师傅，联系电话:18316679357或\n李师傅，联系电话:18607639812";
            }
            if(StringUtils.equals("登录",map.get("Content"))) {
                String uri = "http://1983435v4e.imwork.net/wx/wxUrl";
                response = String.format(WxUtil.AUTH_REDIRET,WxUtil.APPID,uri,"code","snsapi_userinfo","1") ;
            }
        }
        return response;
    }
    public static void main(String[] args) {
        ShunTentWxUtil.createMenu();
    }

}
