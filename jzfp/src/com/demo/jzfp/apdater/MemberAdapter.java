package com.demo.jzfp.apdater;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import com.demo.jzfp.R;
import com.demo.jzfp.entity.TdataFamily;
import com.demo.jzfp.view.DoubleDatePickerDialog;
import com.demo.jzfp.view.WheelView;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MemberAdapter extends BaseAdapter {
	private Context context;
	private List<TdataFamily> members;
	private LayoutInflater inflater;
	private String[] healths = { "患有大病", "残疾", "长期慢性病", "健康" };
	private String[] relations = { "之配偶", "之子", "之女", "之父", "之母", "之孙子", "之孙女", "之儿媳", "之女婿", "之公公", "之婆婆", "之岳父", "之岳母" };
	private AlertDialog dialog;
	private String relation;
	private String health;

	public MemberAdapter(Context context, List<TdataFamily> members) {
		this.context = context;
		this.members = members;
		this.inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return members == null ? 0 : members.size();
	}

	@Override
	public Object getItem(int position) {
		return members.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Holder holder;
		if (convertView == null) {
			holder = new Holder();
			convertView = inflater.inflate(R.layout.lv_member, null);

			holder.et_name = (EditText) convertView.findViewById(R.id.et_name);

			holder.rl_relation = (RelativeLayout) convertView.findViewById(R.id.rl_relation);
			holder.tv_relation = (TextView) convertView.findViewById(R.id.tv_relation);

			holder.ll_women = (LinearLayout) convertView.findViewById(R.id.ll_women);
			holder.iv_sex_women = (ImageView) convertView.findViewById(R.id.iv_sex_women);
			holder.tv_sex_women = (TextView) convertView.findViewById(R.id.tv_sex_women);
			holder.ll_man = (LinearLayout) convertView.findViewById(R.id.ll_man);
			holder.iv_sex_man = (ImageView) convertView.findViewById(R.id.iv_sex_man);
			holder.tv_sex_man = (TextView) convertView.findViewById(R.id.tv_sex_man);

			holder.card = (EditText) convertView.findViewById(R.id.card);
			
			/*holder.rl_birthday = (RelativeLayout) convertView.findViewById(R.id.rl_birthday);
			holder.tv_birthday = (TextView) convertView.findViewById(R.id.tv_birthday);*/

			holder.rl_health = (RelativeLayout) convertView.findViewById(R.id.rl_health);
			holder.tv_health = (TextView) convertView.findViewById(R.id.tv_health);
			holder.et_work = (EditText) convertView.findViewById(R.id.et_work);

			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}
		holder.et_name.setTag(members.get(position));
		holder.card.setTag(members.get(position));
		holder.et_work.setTag(members.get(position));
		if(position==members.size()-1)
			setListener(holder, position);

		initView(holder, position);


		return convertView;
	}

	class Holder {
		EditText et_name;
		RelativeLayout rl_relation;
		TextView tv_relation;
		LinearLayout ll_women;
		ImageView iv_sex_women;
		TextView tv_sex_women;
		LinearLayout ll_man;
		ImageView iv_sex_man;
		TextView tv_sex_man;
		EditText card;
		/*RelativeLayout rl_birthday;
		TextView tv_birthday;*/
		RelativeLayout rl_health;
		TextView tv_health;
		EditText et_work;
	}

	private void initView(Holder holder, int position) {
		TdataFamily member = members.get(position);
		Log.i("jjy", "MemberName="+member.getFamilyName());
		if (!TextUtils.isEmpty(member.getFamilyName())) {
			holder.et_name.setText(member.getFamilyName());
		} else {
			holder.et_name.setText("");
		}
		if (!TextUtils.isEmpty(member.getCard())) {
			holder.card.setText(member.getCard());
		} else {
			holder.card.setText("");
		}
		if (!TextUtils.isEmpty(member.getYhzgx())) {
			holder.tv_relation.setText(member.getYhzgx());
		} else {
			holder.tv_relation.setText("");
		}

		if ("2".equals(member.getSex())) {
			holder.iv_sex_women.setImageResource(R.drawable.woman_yes);
			holder.tv_sex_women.setTextColor(Color.rgb(155, 89, 182));
			holder.iv_sex_man.setImageResource(R.drawable.man_no);
			holder.tv_sex_man.setTextColor(Color.rgb(220, 220, 200));
		} else if ("1".equals(member.getSex())) {
			holder.iv_sex_women.setImageResource(R.drawable.woman_no);
			holder.tv_sex_women.setTextColor(Color.rgb(220, 220, 220));
			holder.iv_sex_man.setImageResource(R.drawable.man_yes);
			holder.tv_sex_man.setTextColor(Color.rgb(52, 152, 219));
		} else {
			holder.iv_sex_women.setImageResource(R.drawable.woman_no);
			holder.tv_sex_women.setTextColor(Color.rgb(220, 220, 220));
			holder.iv_sex_man.setImageResource(R.drawable.man_no);
			holder.tv_sex_man.setTextColor(Color.rgb(220, 220, 200));
		}
		/*if (!TextUtils.isEmpty(member.getBirthday())) {
			holder.tv_birthday.setText(member.getBirthday());
		} else {
			holder.tv_birthday.setText("");
		}*/
		if (!TextUtils.isEmpty(member.getJkzk())) {
			if("jkzkhydb".equals(member.getJkzk())){
				holder.tv_health.setText("患有大病");
			} else if("jkzkcj".equals(member.getJkzk())){
				holder.tv_health.setText("残疾");
			} else if("jkzkcqmxb".equals(member.getJkzk())){
				holder.tv_health.setText("长期慢性病");
			} else if("jkzkjk".equals(member.getJkzk())){
				holder.tv_health.setText("健康");
			}
		} else {
			holder.tv_health.setText("");
		}
		if (!TextUtils.isEmpty(member.getWorkDesc())) {
			holder.et_work.setText(member.getWorkDesc());
		} else {
			holder.et_work.setText("");
		}
		if (!TextUtils.isEmpty(member.getCard())) {
			holder.card.setText(member.getCard());
		} else {
			holder.card.setText("");
		}
	}

	/**
	 * 设置监听
	 * 
	 */
	private void setListener(final Holder holder, int position) {
		final TdataFamily member = members.get(position);
		holder.card.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				TdataFamily mb = (TdataFamily)holder.card.getTag();
				mb.setCard(s.toString().trim());
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				
			}
		});
		holder.et_name.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				TdataFamily mb = (TdataFamily) holder.et_name.getTag();
				mb.setFamilyName(s.toString().trim());
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				
			}
		});

		holder.rl_relation.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showWheelView(holder, "请选择与户主的关系", relations, 1, member);
			}
		});

		holder.ll_man.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				holder.iv_sex_women.setImageResource(R.drawable.woman_no);
				holder.tv_sex_women.setTextColor(Color.rgb(220, 220, 220));
				holder.iv_sex_man.setImageResource(R.drawable.man_yes);
				holder.tv_sex_man.setTextColor(Color.rgb(52, 152, 219));
				
				member.setSex("1");
			}
		});

		holder.ll_women.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				holder.iv_sex_women.setImageResource(R.drawable.woman_yes);
				holder.tv_sex_women.setTextColor(Color.rgb(155, 89, 182));
				holder.iv_sex_man.setImageResource(R.drawable.man_no);
				holder.tv_sex_man.setTextColor(Color.rgb(220, 220, 200));
				member.setSex("2");
			}
		});

		/*holder.rl_birthday.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Calendar c = Calendar.getInstance();
				// 最后一个false表示不显示日期，如果要显示日期，最后参数可以是true或者不用输入
				new DoubleDatePickerDialog(context, 0, new DoubleDatePickerDialog.OnDateSetListener() {
					@Override
					public void onDateSet(DatePicker startDatePicker, int startYear, int startMonthOfYear, int startDayOfMonth) {
						String textString = startYear + "-" + (startMonthOfYear + 1) + "-" + startDayOfMonth;
						holder.tv_birthday.setText(textString);
						member.setBirthday(textString);
					}
				}, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE), false).show();

			}
		});*/

		holder.rl_health.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showWheelView(holder, "请选择健康状态", healths, 2, member);
			}
		});

		holder.et_work.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				TdataFamily mb = (TdataFamily) holder.et_work.getTag();
				mb.setWorkDesc(s.toString().trim());
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});

	}

	/**
	 * 选择弹出窗
	 */
	private void showWheelView(final Holder holder, String title, String[] strs, final int table, final TdataFamily member) {
		View outerView = inflater.inflate(R.layout.wheel_view, null);
		WheelView wv = (WheelView) outerView.findViewById(R.id.wheel_view_wv);
		wv.setOffset(1);
		wv.setItems(Arrays.asList(strs));
		wv.setSeletion(1);
		wv.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
			@Override
			public void onSelected(int selectedIndex, String item) {
				if (table == 1) {
					relation = item;
				} else if (table == 2) {
					health = item;
				}
			}
		});
		dialog = new AlertDialog.Builder(context).setTitle(title).setView(outerView).show();

		TextView txtSure = (TextView) outerView.findViewById(R.id.txt_sure);
		txtSure.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (table == 1) {
					if (TextUtils.isEmpty(relation))
						relation = relations[1];
					holder.tv_relation.setText(relation);
					member.setYhzgx(relation);
					relation = "";
				} else if (table == 2) {
					if (TextUtils.isEmpty(health))
						health = healths[1];
					holder.tv_health.setText(health);
					if("患有大病".equals(health)){
						member.setJkzk("jkzkhydb");
					} else if("残疾".equals(health)){
						member.setJkzk("jkzkcj");
					} else if("长期慢性病".equals(health)){
						member.setJkzk("jkzkcqmxb");
					} else if("健康".equals(health)){
						member.setJkzk("jkzkjk");
					}
					health = "";
				}
				dialog.dismiss();
			}
		});
		TextView txtCancle = (TextView) outerView.findViewById(R.id.txt_cancel);
		txtCancle.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
	}

}
