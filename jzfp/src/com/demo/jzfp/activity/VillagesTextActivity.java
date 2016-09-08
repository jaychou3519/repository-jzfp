package com.demo.jzfp.activity;

import com.demo.jzfp.R;
import com.demo.jzfp.utils.MyApplication;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.view.View;
import android.widget.TextView;

public class VillagesTextActivity extends BaseActivity{

	@ViewInject(R.id.tv_context)
	private TextView tv_context;
	@ViewInject(R.id.tv_title)
	private TextView tv_title;
	private MyApplication activityList;
	@Override
	protected void setView() {
		View view = View.inflate(this, R.layout.activity_villages_text, null);
		activityList = (MyApplication) getApplicationContext();
		activityList.addActivity(this);
		setContentView(view);
		ViewUtils.inject(this,view);
		setTitleText("乡镇介绍");
		setOnback(this);
	}

	@Override
	protected void initView() {
		
	}

	@Override
	protected void initData() {
		String string = "霞阳镇是地处湘赣边境株洲市炎陵县中北部的一个美丽的城镇，它系中南地区绿化最好、绿色最浓的城镇之一，先后被株洲市授予\"小康乡镇\"、\"绿色城镇\"和\"水果之乡\"称号。这里人文历史深厚，境内有省级森林公园湘山公园和红军革命纪念地、省级文物保护单位湘山宝塔、洣泉书院、全国第一个红军标语博物馆，毛泽东等老一辈无产阶级革命家曾在这里进行了建党建军的创举。\n      霞阳镇为炎陵县县城所在地，2004年2月经国家六部委审定为国家首批全国重点镇之一。2015年11月原三河、霞阳两镇合并组成新霞阳镇。全镇面积211.66平方公里，辖33个村、4个社区、1个居委会，总人口6.98万人。2015年，完成财政总收入1907.62万元。税收收入完成1784.52万元，霞阳完成1464.5万元，其中国税561.3万元，地税903.2万元；原三河完成税收收入320.02万元。完成规模企业总产值69.4亿元，同比增长9.9%。社会固定资产投资16.31亿元,其中霞阳9.8152万元，原三河6.49亿元。\n      霞阳镇境内山清水秀、气候宜人，金、辉绿岩、锑、萤石、石灰石、花岗石、矿泉水，森林、水电、水果和蔬菜等自然资源丰富，森林覆盖率达83.6%。城西有炎陵县九龙工业园，城南有电子工业园。106国道和S321省道贯穿全境，衡茶吉铁路的建设，衡炎高速、炎汝高速、炎睦高速的建成通车，霞阳镇融入了长株潭\"两型\"社会发展圈。全镇有企业31家，其中规模企业9家，形成了电子、材料、酒店、房地产、竹木和农产品加工的格局，年实现税收千万元以上，被称为湘东南、湘赣边境的\"兴业福地\"、\"人居佳地\"和\"旅游胜地\"。";
		tv_context.setText(string);
	}

}
