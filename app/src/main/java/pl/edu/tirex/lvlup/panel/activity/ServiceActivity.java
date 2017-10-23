package pl.edu.tirex.lvlup.panel.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import pl.edu.tirex.lvlup.api.models.service.ServiceInfo;
import pl.edu.tirex.lvlup.panel.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ServiceActivity extends SecuredActivity
{
    private final SimpleDateFormat dateFormat;
    private ServiceInfo serviceInfo;

    public ServiceActivity()
    {
        this.dateFormat = new SimpleDateFormat(this.getResources().getString(R.string.date_format), Locale.ENGLISH);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.content_service);

        Intent intent = this.getIntent();
        if (intent == null || !intent.hasExtra("id"))
        {
            return;
        }
        int id = intent.getIntExtra("id", 0);
        System.out.println("GET " + id);

        this.setTitle(this.serviceInfo.getName());

        TextView idView = (TextView) this.findViewById(R.id.id);
        idView.setText("#" + Integer.toString(serviceInfo.getId()));

        TextView name = (TextView) this.findViewById(R.id.name);
        name.setText(serviceInfo.getName());
        name.setCompoundDrawablesWithIntrinsicBounds(serviceInfo.isActive() ? R.drawable.green_dot : R.drawable.red_dot, 0, 0, 0);

        TextView payedTo = (TextView) this.findViewById(R.id.payed_to);
        payedTo.setText(this.dateFormat.format(new Date(serviceInfo.getPayedTo() * 1000)));
    }
}
