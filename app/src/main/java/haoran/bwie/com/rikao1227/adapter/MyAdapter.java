package haoran.bwie.com.rikao1227.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import haoran.bwie.com.rikao1227.R;
import haoran.bwie.com.rikao1227.bean.Bean;

public class MyAdapter extends BaseAdapter {
    private List<Bean.DataBean> list;
    private Context context;

    public MyAdapter(List<Bean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return 2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = View.inflate(context, R.layout.item_style1, null);
            ImageView tupian = convertView.findViewById(R.id.style_image1);
            TextView text = convertView.findViewById(R.id.style_text);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Glide.with(context).load(list.get(position).getImages()).into(viewHolder.vhImage);
        viewHolder.vhText.setText(list.get(position).getTitle());
        return convertView;
    }

    class ViewHolder {
        ImageView vhImage;
        TextView vhText;
    }
}
