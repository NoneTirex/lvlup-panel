package pl.edu.tirex.lvlup.panel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import pl.edu.tirex.lvlup.api.models.service.ServiceInfo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ServicesArrayAdapter extends ArrayAdapter<ServiceInfo>
{
    private final SimpleDateFormat dateFormat;

    public ServicesArrayAdapter(Context context, List<ServiceInfo> objects)
    {
        super(context, 0, objects);
        this.dateFormat = new SimpleDateFormat(context.getResources().getString(R.string.date_format), Locale.ENGLISH);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if (convertView == null)
        {
            convertView = LayoutInflater.from(this.getContext()).inflate(R.layout.service_item, parent, false);
        }
        ServiceInfo serviceInfo = this.getItem(position);
        TextView id = convertView.findViewById(R.id.id);
        id.setText("#" + Integer.toString(serviceInfo.getId()));

        TextView name = convertView.findViewById(R.id.name);
        name.setText(serviceInfo.getName());
        name.setCompoundDrawablesWithIntrinsicBounds(serviceInfo.isActive() ? R.drawable.green_dot : R.drawable.red_dot, 0, 0, 0);

        TextView payedTo = convertView.findViewById(R.id.payed_to);
        payedTo.setText(this.dateFormat.format(new Date(serviceInfo.getPayedTo() * 1000)));
        return convertView;
    }
}