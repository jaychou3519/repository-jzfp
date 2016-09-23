package com.demo.jzfp.apdater;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import com.demo.jzfp.R;
import com.demo.jzfp.entity.TdataFamily;
import com.demo.jzfp.utils.Tools;
import com.demo.jzfp.view.DoubleDatePickerDialog;
import com.demo.jzfp.view.WheelView;
import android.app.AlertDialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

public class RecordAdapterEdit extends BaseAdapter{

	private Context context;
	private List<TdataFamily> families;
	private String[] healths = { "患有大病", "残疾", "长期慢性病", "健康" };
	private String[] genders = { "男", "女"};
	private String[] relations = { "之配偶", "之子", "之女", "之父", "之母", "之孙子", "之孙女", "之儿媳", "之女婿", "之公公", "之婆婆", "之岳父", "之岳母" };
	private AlertDialog dialog;
	private String relation;
	private String health,gender;

	public RecordAdapterEdit(Context context,List<TdataFamily> families) {
		this.context = context;
		this.families = families;
	}
	@Override
	public int getCount() {
		return families.size();
	}

	@Override
	public Object getItem(int position) {
		return families.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if(convertView == null){
			holder = new ViewHolder();
			convertView = View.inflate(context, R.layout.record_item_edit, null);
			holder.ed_name = (EditText) convertView.findViewById(R.id.ed_name);
			holder.tv_relation = (TextView) convertView.findViewById(R.id.tv_relation);
			holder.tv_gender = (TextView) convertView.findViewById(R.id.tv_gender);
			holder.tv_YearM = (TextView) convertView.findViewById(R.id.tv_YearM);
			holder.tv_health = (TextView) convertView.findViewById(R.id.tv_health);
			holder.tv_other = (TextView) convertView.findViewById(R.id.tv_other);
			holder.vw_lines = convertView.findViewById(R.id.vw_lines);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		holder.ed_name.setText(Tools.parseEmpty(families.get(position).getFamilyName()));
		holder.tv_relation.setText(Tools.parseEmpty(families.get(position).getYhzgx()+""));
		holder.tv_gender.setText(Tools.parseEmpty(families.get(position).getSex()+""));
		holder.tv_YearM.setText(Tools.parseEmpty(families.get(position).getBirthday()+""));
		holder.tv_health.setText(Tools.parseEmpty(families.get(position).getJkzk()+""));
		holder.tv_other.setText(Tools.parseEmpty(families.get(position).getWorkDesc()+""));
		if(getCount()==(position+1)){
			holder.vw_lines.setVisibility(View.GONE);
		}
		setListener(holder, position);
		return convertView;
	}


	/**
	 * 设置监听
	 * 
	 */
	private void setListener(final ViewHolder holder, int position) {
		final TdataFamily member = families.get(position);
		
		holder.tv_relation.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showWheelView(holder, "请选择与户主的关系", relations, 1, member);
			}
		});
		holder.tv_YearM.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Calendar c = Calendar.getInstance();
				// 最后一个false表示不显示日期，如果要显示日期，最后参数可以是true或者不用输入
				new DoubleDatePickerDialog(context, 0, new DoubleDatePickerDialog.OnDateSetListener() {
					@Override
					public void onDateSet(DatePicker startDatePicker, int startYear, int startMonthOfYear, int startDayOfMonth) {
						String textString = startYear + "年" + (startMonthOfYear + 1) + "月";
						holder.tv_YearM.setText(textString);
						member.setBirthday(textString);
					}
				}, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE), false).show();
			}
		});
		holder.tv_gender.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showWheelView(holder, "请选择性别", genders, 3, member);
			}
		});
		holder.tv_health.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showWheelView(holder, "请选择健康状态", healths, 2, member);
			}
		});
	}
	/**
	 * 选择弹出窗
	 */
	private void showWheelView(final ViewHolder holder, String title, String[] strs, final int table, final TdataFamily member) {
		View outerView = View.inflate(context,R.layout.wheel_view, null);
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
				}else if(table == 3){
					if (TextUtils.isEmpty(gender))
						gender = genders[1];
					holder.tv_gender.setText(gender);
					member.setSex(gender);
					gender = "";
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

	class ViewHolder{
		TextView ed_name;
		TextView tv_relation;
		TextView tv_gender;
		TextView tv_YearM;
		TextView tv_health;
		TextView tv_other;
		View vw_lines;
	}
}
